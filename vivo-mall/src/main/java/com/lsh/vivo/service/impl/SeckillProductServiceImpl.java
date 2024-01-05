//package com.lsh.vivo.service.impl;
//
//import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
//import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
//import com.lsh.vivo.service.SeckillGoodsService;
//import com.lsh.vivo.constant.RedisKey;
//import com.lsh.vivo.pojo.SeckillGoods;
//import com.lsh.vivo.pojo.SeckillTime;
//import com.lsh.vivo.enumerate.ExceptionEnum;
//import com.lsh.vivo.util.BeanUtil;
//import com.lsh.vivo.vo.SeckillGoodsVO;
//import lombok.RequiredArgsConstructor;
//import org.springframework.amqp.AmqpException;
//import org.springframework.amqp.rabbit.connection.CorrelationData;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.core.StringRedisTemplate;
//import org.springframework.stereotype.Service;
//
//import java.util.*;
//import java.util.concurrent.TimeUnit;
//
///**
// * @author ASUS
// * @description 针对表【goods_seckill】的数据库操作Service实现
// * @createLocalDateTime 2023-08-11 14:40:59
// */
//@Service
//@RequiredArgsConstructor
//public class SeckillGoodsServiceImpl extends ServiceImpl<SeckillGoodsMapper, SeckillGoods>
//        implements SeckillGoodsService {
//
//    private final RedisTemplate redisTemplate;
//
//    private final SeckillTimeMapper seckillTimeMapper;
//
//    private final StringRedisTemplate stringRedisTemplate;
//
//    private final RabbitTemplate rabbitTemplate;
//
//    private final HashMap<String, Boolean> localOverMap = new HashMap<>();
//
//    @Override
//    public List<SeckillGoodsVO> listSeckillGoods(String timeId) {
//        // 先查缓存中是否有列表
//        List<SeckillGoodsVO> seckillGoodsVOS = redisTemplate.opsForList().range(RedisKey.SECKILL_PRODUCT_LIST + timeId, 0, -1);
//        if (seckillGoodsVOS != null) {
//            return seckillGoodsVOS;
//        }
//        // 如果缓存中没有，再从数据库中获取，并添加到缓存
//        seckillGoodsVOS = baseMapper.listSeckillGoodsVos(timeId, new LocalDateTime().getTime());
//        if (seckillGoodsVOS == null) {
//            // 秒杀商品过期或不存在
//            throw new XmException(ExceptionEnum.GET_SECKILL_NOT_FOUND);
//        }
//        redisTemplate.opsForList().leftPushAll(RedisKey.SECKILL_PRODUCT_LIST + timeId, seckillGoodsVOS);
//        // 设置过期时间
//        long expireTime = seckillGoodsVOS.get(0).getEndTime() - new LocalDateTime().getTime();
//        redisTemplate.expire(RedisKey.SECKILL_PRODUCT_LIST + timeId, expireTime, TimeUnit.MILLISECONDS);
//        return seckillGoodsVOS;
//    }
//
//    /**
//     * 获取当前时间的整点
//     *
//     * @return 时间
//     */
//    private LocalDateTime getLocalDateTime() {
//        Calendar calendar = Calendar.getInstance();
//        calendar.set(Calendar.MINUTE, 0);
//        calendar.set(Calendar.SECOND, 0);
//        return calendar.getTime();
//    }
//
//    @Override
//    public void saveSeckillGoods(SeckillGoods seckillGoods) {
//        LocalDateTime time = getLocalDateTime();
//        long startTime = time.getTime() / 1000 * 1000 + 1000 * 60 * 60;
//        long endTime = startTime + 1000 * 60 * 60;
//
//        // 先查看是否有该时间段
//        LambdaQueryWrapper<SeckillTime> queryWrapper = new LambdaQueryWrapper<>();
//        SeckillTime one = seckillTimeMapper.selectOne(queryWrapper
//                .eq(SeckillTime::getStartTime, startTime)
//                .eq(SeckillTime::getEndTime, endTime));
//        if (one == null) {
//            // 将秒杀时间段写入数据库
//            SeckillTime seckillTime = new SeckillTime();
//            seckillTime.setStartTime(startTime);
//            seckillTime.setEndTime(endTime);
//            seckillTimeMapper.insert(seckillTime);
//            seckillGoods.setTimeId(seckillTime.getTimeId());
//        } else {
//            seckillGoods.setTimeId(one.getTimeId());
//        }
//        save(seckillGoods);
//    }
//
//    @Override
//    public List<SeckillTime> listTime() {
//        LocalDateTime time = getLocalDateTime();
//        return seckillTimeMapper.listSeckillTime(time.getTime() / 1000 * 1000);
//    }
//
//    @Override
//    public SeckillGoodsVO getSeckill(String seckillId) throws Exception {
//        // 从缓存中查询
//        Map map = redisTemplate.opsForHash().entries(RedisKey.SECKILL_PRODUCT + seckillId);
//        if (!map.isEmpty()) {
//            return BeanUtil.map2bean(map, SeckillGoodsVO.class);
//        }
//        // 缓存中没有再从数据库中查询
//        SeckillGoodsVO seckillGoodsVo = baseMapper.getSeckill(seckillId);
//        if (seckillGoodsVo == null) {
//            return null;
//        }
//        // 查询到数据再写入缓存
//        try {
//            redisTemplate.opsForHash().putAll(RedisKey.SECKILL_PRODUCT + seckillId, BeanUtil.bean2map(seckillGoodsVo));
//            redisTemplate.expire(RedisKey.SECKILL_PRODUCT + seckillId, seckillGoodsVo.getEndTime() - new LocalDateTime().getTime(), TimeUnit.MILLISECONDS);
//            // 将库存单独存入一个key中
//            String key = stringRedisTemplate.opsForValue().get(RedisKey.SECKILL_PRODUCT_STOCK + seckillId);
//            if (key == null) {
//                stringRedisTemplate.opsForValue().set(RedisKey.SECKILL_PRODUCT_STOCK + seckillId, seckillGoodsVo.getSeckillStock() + "", seckillGoodsVo.getEndTime() - new LocalDateTime().getTime(), TimeUnit.MILLISECONDS);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return seckillGoodsVo;
//    }
//
//    @Override
//    public void seckillGoods(String seckillId, Integer userId) throws Exception {
//        if (localOverMap.get(seckillId) != null) {
//            // 秒杀商品已售空
//            throw new XmException(ExceptionEnum.GET_SECKILL_IS_OVER);
//        }
//        // 判断秒杀是否开始, 防止路径暴露被刷
//        Map map = redisTemplate.opsForHash().entries(RedisKey.SECKILL_PRODUCT + seckillId);
//        if (!map.isEmpty()) {
//            SeckillGoodsVO seckillGoodsVo = BeanUtil.map2bean(map, SeckillGoodsVO.class);
//            // 秒杀开始时间
//            Long startTime = seckillGoodsVo.getStartTime();
//            if (startTime > new LocalDateTime().getTime()) {
//                throw new XmException(ExceptionEnum.GET_SECKILL_IS_NOT_START);
//            }
//        }
//        // 判断是否已经秒杀到了，避免一个账户秒杀多个商品
//        List list = redisTemplate.opsForList().range(RedisKey.SECKILL_PRODUCT_USER_LIST + seckillId, 0, -1);
//        if (list.contains(String.valueOf(userId))) {
//            throw new XmException(ExceptionEnum.GET_SECKILL_IS_REUSE);
//        }
//        // 预减库存：从缓存中减去库存
//        // 利用redis中的方法，减去库存，返回值为减去1之后的值
//        if (stringRedisTemplate.opsForValue().decrement(RedisKey.SECKILL_PRODUCT_STOCK + seckillId) < 0) {
//            // 设置内存标记
//            localOverMap.put(seckillId, true);
//            // 秒杀完成，库存为空
//            throw new XmException(ExceptionEnum.GET_SECKILL_IS_OVER);
//        }
//        // 使用RabbitMQ异步传输
//        mqSend(seckillId, userId);
//    }
//
//    private void mqSend(String seckillId, Integer userId) {
//        HashMap<String, String> map = new HashMap<>();
//        map.put("seckillId", seckillId);
//        map.put("userId", userId.toString());
//        // 设置ID，保证消息队列幂等性
//        CorrelationData correlationData = new CorrelationData();
//        correlationData.setId(seckillId + ":" + userId);
//        try {
//            rabbitTemplate.convertAndSend("seckill_order", map, correlationData);
//        } catch (AmqpException e) {
//            // 发送消息失败
//            log.error("发送消息失败");
//            stringRedisTemplate.opsForValue().increment(RedisKey.SECKILL_PRODUCT_STOCK + seckillId);
//        }
//    }
//
//    public Long getEndTime(String seckillId) {
//        SeckillGoodsVO seckill = baseMapper.getSeckill(seckillId);
//        LambdaQueryWrapper<SeckillTime> queryWrapper = new LambdaQueryWrapper<>();
//        return seckillTimeMapper.selectOne(queryWrapper
//                .eq(SeckillTime::getTimeId, seckill.getTimeId()))
//                .getEndTime();
//    }
//}
//
//
//
//
