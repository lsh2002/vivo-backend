package com.lsh.vivo.mapper.struct;

import com.lsh.vivo.bean.request.goods.cat.GoodsCategorySaveVO;
import com.lsh.vivo.bean.request.goods.cat.GoodsCategoryStatusVO;
import com.lsh.vivo.bean.request.goods.cat.GoodsCategoryUpdateVO;
import com.lsh.vivo.bean.response.goods.cat.GoodsCategorySelectVO;
import com.lsh.vivo.bean.response.goods.cat.GoodsCategoryVO;
import com.lsh.vivo.bean.response.system.PageVO;
import com.lsh.vivo.entity.GoodsCategory;
import com.lsh.vivo.util.MapperStructTypeConvert;
import com.mybatisflex.core.paginate.Page;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author AdolphLv
 * @version 1.0.0
 * 2023/9/20 22:15
 */
@Mapper(uses = {MapperStructTypeConvert.class})
public interface GoodsCategoryMpp {

    /**
     * 定义实例
     */
    GoodsCategoryMpp INSTANCE = Mappers.getMapper(GoodsCategoryMpp.class);

    /**
     * 转前端交互商品类别
     *
     * @param goodsCategory 商品类别
     * @return 返回前端交互商品类别
     */
    @Mapping(target = "createTime", source = "createTime", qualifiedByName = "localDateTimeToLong")
    @Mapping(target = "modifiedTime", source = "modifiedTime", qualifiedByName = "localDateTimeToLong")
    GoodsCategoryVO toVO(GoodsCategory goodsCategory);

    /**
     * 转前端交互商品类别集
     *
     * @param goodsCategoryPage 分页信息
     * @return 返回前端交互商品类别
     */
    @Mapping(target = "page.total", source = "totalRow")
    @Mapping(target = "page.size", source = "pageSize")
    @Mapping(target = "page.current", source = "pageNumber")
    @Mapping(target = "results", source = "records")
    PageVO<GoodsCategoryVO> toPageVO(Page<GoodsCategory> goodsCategoryPage);

    /**
     * 转实体
     *
     * @param goodsCatSaveVO 前端交互用户
     * @return
     */
    GoodsCategory toDO(GoodsCategorySaveVO goodsCatSaveVO);

    /**
     * 修改转实体
     */
    GoodsCategory toDO(GoodsCategoryUpdateVO goodsCatUpdateVO);

    /**
     * 修改状态转实体
     */
    GoodsCategory toDO(GoodsCategoryStatusVO goodsCatStatusVO);

    /**
     * 转前端交互商品类别下拉框数据
     */
    List<GoodsCategorySelectVO> toSelectVO(List<GoodsCategory> goodsCats);
}
