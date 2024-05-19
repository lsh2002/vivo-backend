package com.lsh.vivo.scheduled;

import com.lsh.vivo.entity.GoodsSeckill;
import com.lsh.vivo.entity.GoodsSku;
import com.lsh.vivo.enumerate.GoodsStatusEnum;
import com.lsh.vivo.service.GoodsSeckillService;
import com.lsh.vivo.service.GoodsSkuService;
import com.lsh.vivo.util.RedisUtil;
import com.mybatisflex.core.query.QueryWrapper;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

import static com.lsh.vivo.entity.table.GoodsSeckillTableDef.GOODS_SECKILL;
import static com.lsh.vivo.entity.table.GoodsSkuTableDef.GOODS_SKU;
import static com.mybatisflex.core.query.QueryMethods.select;

/**
 * @author lsh
 */
@Component
@Slf4j
public class SeckillJob {

    @Resource
    private GoodsSeckillService goodsSeckillService;
    @Resource
    private GoodsSkuService goodsSkuService;
    @Resource
    private RedisUtil redisUtil;

    @Scheduled(cron = "0 0/30 * * * ? ")
    public void updateSeckillStatus() {
        // 查询已结束的秒杀商品
        List<String> skuIds = goodsSeckillService.listFinished();
        if (CollectionUtils.isEmpty(skuIds)) {
            log.info("没有需要更新状态的商品");
            return;
        }
        // 更新商品秒杀状态
        goodsSkuService.updateSeckillStatus(skuIds);
        log.info("更新成功");
    }

    @Scheduled(cron = "0 0/30 * * * ? ")
    public void listSeckill() {
        // 查询所有秒杀商品
        QueryWrapper queryWrapper = select()
                .where(GOODS_SECKILL.START_TIME.lt(LocalDateTime.now()))
                .and(GOODS_SECKILL.END_TIME.gt(LocalDateTime.now()))
                .and(GOODS_SECKILL.STATUS.eq(GoodsStatusEnum.U.name()))
                .and(GOODS_SECKILL.SECKILL_NUM.le(0));
        List<GoodsSeckill> goodsSeckills = goodsSeckillService.list(queryWrapper);
        if (CollectionUtils.isEmpty(goodsSeckills)) {
            for (GoodsSeckill goodsSeckill : goodsSeckills) {
                if (!redisUtil.hasKey("seckill:" + goodsSeckill.getSkuId())) {
                    redisUtil.set("seckill:" + goodsSeckill.getSkuId(), goodsSeckill);
                }
            }
            List<String> skuIds = goodsSeckills.stream().map(GoodsSeckill::getSkuId).toList();
            QueryWrapper queryWrapper1 = select()
                    .where(GOODS_SKU.ID.in(skuIds))
                    .and(GOODS_SKU.STATUS.eq(GoodsStatusEnum.U.name()));
            List<GoodsSku> goodsSkus = goodsSkuService.list(queryWrapper1);
            for (GoodsSku goodsSku : goodsSkus) {
                if (!redisUtil.hasKey("goodsSku" + goodsSku.getId() + ":")) {
                    redisUtil.set("goodsSku" + goodsSku.getId() + ":", goodsSku);
                }
            }
            log.info("秒杀商品缓存成功");
        }
    }
}