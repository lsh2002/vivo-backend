package com.lsh.vivo.mapper.struct;

import com.lsh.vivo.bean.request.goods.seckill.GoodsSeckillSaveVO;
import com.lsh.vivo.bean.response.goods.GoodsSkuVO;
import com.lsh.vivo.bean.response.goods.GoodsVO;
import com.lsh.vivo.bean.response.goods.seckill.GoodsSeckillVO;
import com.lsh.vivo.bean.response.system.PageVO;
import com.lsh.vivo.entity.Goods;
import com.lsh.vivo.entity.GoodsSeckill;
import com.lsh.vivo.entity.GoodsSku;
import com.lsh.vivo.util.MapperStructTypeConvert;
import com.mybatisflex.core.paginate.Page;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * @author AdolphLv
 * @version 1.0.0
 * 2023/9/20 22:15
 */
@Mapper(uses = {MapperStructTypeConvert.class})
public interface GoodsSeckillMpp {

    /**
     * 定义实例
     */
    GoodsSeckillMpp INSTANCE = Mappers.getMapper(GoodsSeckillMpp.class);

    /**
     * 转前端交互商品Sku
     *
     * @param GoodsSeckill 商品Sku
     * @return 返回前端交互商品Sku
     */
    @Mapping(target = "createTime", source = "createTime", qualifiedByName = "localDateTimeToLong")
    @Mapping(target = "modifierTime", source = "modifierTime", qualifiedByName = "localDateTimeToLong")
    @Mapping(target = "startTime", source = "startTime", qualifiedByName = "localDateTimeToLong")
    @Mapping(target = "endTime", source = "endTime", qualifiedByName = "localDateTimeToLong")
    GoodsSeckillVO toVO(GoodsSeckill GoodsSeckill);

    @Mapping(target = "createTime", source = "createTime", qualifiedByName = "localDateTimeToLong")
    @Mapping(target = "modifierTime", source = "modifierTime", qualifiedByName = "localDateTimeToLong")
    GoodsSkuVO toVO(GoodsSku goodsSku);

    @Mapping(target = "createTime", source = "createTime", qualifiedByName = "localDateTimeToLong")
    @Mapping(target = "modifierTime", source = "modifierTime", qualifiedByName = "localDateTimeToLong")
    GoodsVO toVO(Goods goods);

    /**
     * 转前端交互商品Sku集
     *
     * @param goodsPage 分页信息
     * @return 返回前端交互商品Sku
     */
    @Mapping(target = "page.total", source = "totalRow")
    @Mapping(target = "page.size", source = "pageSize")
    @Mapping(target = "page.current", source = "pageNumber")
    @Mapping(target = "results", source = "records")
    PageVO<GoodsSeckillVO> toPageVO(Page<GoodsSeckill> goodsPage);

    @Mapping(target = "startTime", source = "startTime", qualifiedByName = "longToLocalDateTime")
    @Mapping(target = "endTime", source = "endTime", qualifiedByName = "longToLocalDateTime")
    GoodsSeckill toDO(GoodsSeckillSaveVO saveVO);
}
