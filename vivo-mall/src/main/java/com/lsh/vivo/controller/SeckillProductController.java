//package com.lsh.vivo.controller;
//
//import com.lsh.vivo.service.SeckillProductService;
//import com.lsh.vivo.pojo.SeckillProduct;
//import com.lsh.vivo.pojo.SeckillTime;
//import com.lsh.vivo.vo.SeckillProductVO;
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
//@RequestMapping("/seckill/product")
//@RequiredArgsConstructor
//public class SeckillProductController {
//
//    private final BaseResponse resultMessage;
//
//    private final SeckillProductService seckillProductService;
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
//    public BaseResponse getProduct(@PathVariable String timeId) {
//        List<SeckillProductVO> seckillProductVOs = seckillProductService.listSeckillProduct(timeId);
//        resultMessage.success("001", seckillProductVOs);
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
//        SeckillProductVO seckillProductVo = seckillProductService.getSeckill(seckillId);
//        resultMessage.success("001", seckillProductVo);
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
//        List<SeckillTime> seckillTimes = seckillProductService.listTime();
//        resultMessage.success("001", seckillTimes);
//        return resultMessage;
//    }
//
//    /**
//     * 添加秒杀商品
//     *
//     * @param seckillProduct
//     * @return
//     */
//    @PostMapping("/")
//    public BaseResponse addSeckillProduct(@RequestBody SeckillProduct seckillProduct) {
//        seckillProductService.saveSeckillProduct(seckillProduct);
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
//    public BaseResponse seckillProduct(@PathVariable String seckillId, @CookieValue("XM_TOKEN") String cookie)
//            throws Exception {
//        // 先判断cookie是否存在，和redis校验
//        Integer userId = (Integer) redisTemplate.opsForHash().get(cookie, "userId");
//        seckillProductService.seckillProduct(seckillId, userId);
//        resultMessage.success("001", "排队中");
//        return resultMessage;
//    }
//}
