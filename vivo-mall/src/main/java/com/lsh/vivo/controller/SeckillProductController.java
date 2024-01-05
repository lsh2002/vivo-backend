//package com.lsh.vivo.controller;
//
//import com.lsh.vivo.service.SeckillGoodsService;
//import com.lsh.vivo.pojo.SeckillGoods;
//import com.lsh.vivo.pojo.SeckillTime;
//import com.lsh.vivo.vo.SeckillGoodsVO;
//import lombok.RequiredArgsConstructor;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
///**
// * @author lsh
// * @since 2023-08-28 19:58
// */
//@RestController
//@RequestMapping("/seckill/goods")
//@RequiredArgsConstructor
//public class SeckillGoodsController {
//
//    private final BaseResponse resultMessage;
//
//    private final SeckillGoodsService seckillGoodsService;
//
//    private final RedisTemplate redisTemplate;
//
//    /**
//     * 根据时间id获取对应时间的秒杀商品列表
//     *
//     * @param timeId
//     * @return
//     */
//    @GetMapping("/time/{timeId}")
//    public BaseResponse getGoods(@PathVariable String timeId) {
//        List<SeckillGoodsVO> seckillGoodsVOs = seckillGoodsService.listSeckillGoods(timeId);
//        resultMessage.success("001", seckillGoodsVOs);
//        return resultMessage;
//    }
//
//    /**
//     * 获取秒杀商品
//     *
//     * @param seckillId
//     * @return
//     */
//    @GetMapping("/{seckillId}")
//    public BaseResponse getSeckill(@PathVariable String seckillId) throws Exception {
//        SeckillGoodsVO seckillGoodsVo = seckillGoodsService.getSeckill(seckillId);
//        resultMessage.success("001", seckillGoodsVo);
//        return resultMessage;
//    }
//
//    /**
//     * 获取时间段
//     *
//     * @return
//     */
//    @GetMapping("/time")
//    public BaseResponse getTime() {
//        List<SeckillTime> seckillTimes = seckillGoodsService.listTime();
//        resultMessage.success("001", seckillTimes);
//        return resultMessage;
//    }
//
//    /**
//     * 添加秒杀商品
//     *
//     * @param seckillGoods
//     * @return
//     */
//    @PostMapping("/")
//    public BaseResponse addSeckillGoods(@RequestBody SeckillGoods seckillGoods) {
//        seckillGoodsService.saveSeckillGoods(seckillGoods);
//        resultMessage.success("001", "添加成功");
//        return resultMessage;
//    }
//
//    /**
//     * 开始秒杀
//     *
//     * @param seckillId
//     * @return
//     */
//    @PostMapping("/seckill/{seckillId}")
//    public BaseResponse seckillGoods(@PathVariable String seckillId, @CookieValue("XM_TOKEN") String cookie)
//            throws Exception {
//        // 先判断cookie是否存在，和redis校验
//        Integer userId = (Integer) redisTemplate.opsForHash().get(cookie, "userId");
//        seckillGoodsService.seckillGoods(seckillId, userId);
//        resultMessage.success("001", "排队中");
//        return resultMessage;
//    }
//}
