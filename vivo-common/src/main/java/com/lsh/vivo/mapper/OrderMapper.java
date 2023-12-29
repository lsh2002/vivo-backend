package com.lsh.vivo.mapper;

import com.lsh.vivo.entity.Order;
import com.lsh.vivo.mapper.system.CommonMapper;
import com.mybatisflex.core.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author ASUS
 * @description 针对表【order(订单)】的数据库操作Mapper
 * @createLocalDateTime 2023-10-29 21:02:52
 * @Entity com.lsh.vivo.pojo.entity.Order
 */
@Mapper
public interface OrderMapper extends CommonMapper<Order> {

}




