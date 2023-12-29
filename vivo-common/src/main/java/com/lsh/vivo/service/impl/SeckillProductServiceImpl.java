package com.lsh.vivo.service.impl;

import com.lsh.vivo.service.SeckillProductService;
import com.lsh.vivo.entity.SeckillProduct;
import com.lsh.vivo.mapper.SeckillProductMapper;
import com.lsh.vivo.service.system.impl.CommonServiceImpl;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author ASUS
 * @description 针对表【seckill_product(商品秒杀)】的数据库操作Service实现
 * @createLocalDateTime 2023-10-28 22:51:58
 */
@Service
public class SeckillProductServiceImpl extends CommonServiceImpl<SeckillProductMapper, SeckillProduct>
        implements SeckillProductService {

}




