package com.lsh.vivo.mapper.struct;

import com.lsh.vivo.bean.request.order.OrderSaveVO;
import com.lsh.vivo.bean.response.goods.GoodsSkuVO;
import com.lsh.vivo.bean.response.goods.GoodsVO;
import com.lsh.vivo.bean.response.order.OrderVO;
import com.lsh.vivo.entity.Goods;
import com.lsh.vivo.entity.GoodsSku;
import com.lsh.vivo.entity.Order;
import com.lsh.vivo.util.MapperStructTypeConvert;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 角色mapper
 *
 * @author pjw
 * @version 1.0.0
 * @since 2023-10-03 16:53
 */
@Mapper(uses = {MapperStructTypeConvert.class})
public interface OrderMpp {

    /**
     * 定义实例
     */
    OrderMpp INSTANCE = Mappers.getMapper(OrderMpp.class);

    @Mapping(target = "createTime", source = "createTime", qualifiedByName = "localDateTimeToLong")
    @Mapping(target = "modifierTime", source = "modifierTime", qualifiedByName = "localDateTimeToLong")
    @Mapping(target = "payTime", source = "payTime", qualifiedByName = "localDateTimeToLong")
    @Mapping(target = "deliverTime", source = "deliverTime", qualifiedByName = "localDateTimeToLong")
    @Mapping(target = "finishTime", source = "finishTime", qualifiedByName = "localDateTimeToLong")
    @Mapping(target = "cancelTime", source = "cancelTime", qualifiedByName = "localDateTimeToLong")
    OrderVO toVO(Order order);

    List<OrderVO> toVO(List<Order> orders);

    List<Order> toDO(List<OrderSaveVO> orderSaveVOs);

    @Mapping(target = "payTime", source = "payTime", qualifiedByName = "longToLocalDateTime")
    Order toDO(OrderSaveVO orderSaveVO);

    @Mapping(target = "createTime", source = "createTime", qualifiedByName = "localDateTimeToLong")
    @Mapping(target = "modifierTime", source = "modifierTime", qualifiedByName = "localDateTimeToLong")
    GoodsSkuVO toVO(GoodsSku goodsSku);

    @Mapping(target = "createTime", source = "createTime", qualifiedByName = "localDateTimeToLong")
    @Mapping(target = "modifierTime", source = "modifierTime", qualifiedByName = "localDateTimeToLong")
    GoodsVO toVO(Goods goods);
}
