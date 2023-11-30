//package com.lsh.vivo.task;
//
//import com.lsh.vivo.pojo.Product;
//import com.lsh.vivo.pojo.SeckillProduct;
//import com.lsh.vivo.pojo.SeckillTime;
//import lombok.RequiredArgsConstructor;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//
//import java.util.*;
//import java.util.stream.Collectors;
//
///**
// * @author lsh
// */
//@Component
//@RequiredArgsConstructor
//public class SeckillTask {
//
//    private final SeckillTimeMapper seckillTimeMapper;
//
//    private final ProductMapper productMapper;
//
//    private final SeckillProductMapper seckillProductMapper;
//
//    @Scheduled(cron = "0 0 15 * * ?")
//    public void execute() {
//        // 获取商品设为秒杀商品
//        List<Integer> productIds = productMapper.selectList(null).stream()
//                .map(Product::getProductId)
//                .collect(Collectors.toList());
//        LocalDateTime time = getLocalDateTime();
//        seckillTimeMapper.delete(null);
//        seckillProductMapper.delete(null);
//        for (int i = 1; i < 24; i = i + 2) {
//            // 插入时间
//            long startTime = time.getTime() / 1000 * 1000 + 1000L * 60 * 60 * i;
//            long endTime = startTime + 1000 * 60 * 60;
//            SeckillTime seckillTime = new SeckillTime();
//            seckillTime.setStartTime(startTime);
//            seckillTime.setEndTime(endTime);
//            seckillTimeMapper.insert(seckillTime);
//
//            // 随机选15个商品id
//            ArrayList<SeckillProduct> seckillProducts = getSeckillProducts(productIds, seckillTime);
//            seckillProducts.forEach(seckillProductMapper::insert);
//            // System.out.println(Arrays.toString(seckillProducts.toArray()));
//            try {
//                Thread.sleep(1_000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            System.out.println("完成---------------------");
//
//        }
//        System.out.println("一次添加ok-------------------------------------------");
//        // try {
//        //     Thread.sleep(100_000);
//        // } catch (InterruptedException e) {
//        //     e.printStackTrace();
//        // }
//    }
//
//    private static ArrayList<SeckillProduct> getSeckillProducts(List<Integer> productIds, SeckillTime seckillTime) {
//        HashSet<Integer> set = new HashSet<>();
//        while (set.size() < 15) {
//            Random random = new Random();
//            int nextInt = random.nextInt(productIds.size());
//            set.add(productIds.get(nextInt));
//        }
//        ArrayList<Integer> integers = new ArrayList<>(set);
//        // System.out.println(Arrays.toString(integers.toArray()));
//
//        // 添加秒杀商品
//        ArrayList<SeckillProduct> seckillProducts = new ArrayList<>();
//        for (int j = 0; j < 15; j++) {
//            SeckillProduct seckillProduct = new SeckillProduct();
//            seckillProduct.setSeckillPrice(1000.0);
//            seckillProduct.setSeckillStock(100);
//            seckillProduct.setProductId(integers.get(j));
//            seckillProduct.setTimeId(seckillTime.getTimeId());
//            seckillProducts.add(seckillProduct);
//        }
//        return seckillProducts;
//    }
//
//    private LocalDateTime getLocalDateTime() {
//        Calendar ca = Calendar.getInstance();
//        ca.set(Calendar.MINUTE, 0);
//        ca.set(Calendar.SECOND, 0);
//        return ca.getTime();
//    }
//}
