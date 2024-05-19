package com.lsh.vivo.mapper.struct;

import com.lsh.vivo.bean.dto.goods.GoodsConditionDTO;
import com.lsh.vivo.bean.request.goods.GoodsConditionVO;
import com.lsh.vivo.bean.request.goods.GoodsSaveVO;
import com.lsh.vivo.bean.request.goods.GoodsStatusVO;
import com.lsh.vivo.bean.request.goods.GoodsUpdateVO;
import com.lsh.vivo.bean.response.goods.GoodsSelectVO;
import com.lsh.vivo.bean.response.goods.GoodsVO;
import com.lsh.vivo.bean.response.goods.cat.GoodsCategorySelectVO;
import com.lsh.vivo.bean.response.system.PageVO;
import com.lsh.vivo.entity.Goods;
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
public interface GoodsMpp {

    /**
     * 定义实例
     */
    GoodsMpp INSTANCE = Mappers.getMapper(GoodsMpp.class);

    /**
     * 转前端交互商品
     *
     * @param goods 商品
     * @return 返回前端交互商品
     */
    GoodsVO toVO(Goods goods);

    /**
     * 转前端交互商品
     *
     * @param goods 商品
     * @return 返回前端交互商品
     */
    @Mapping(target = "createTime", source = "createTime", qualifiedByName = "localDateTimeToLong")
    @Mapping(target = "modifierTime", source = "modifierTime", qualifiedByName = "localDateTimeToLong")
    List<GoodsSelectVO> toVO(List<Goods> goods);

    /**
     * 转前端交互商品集
     *
     * @param goodsPage 分页信息
     * @return 返回前端交互商品
     */
    @Mapping(target = "page.total", source = "totalRow")
    @Mapping(target = "page.size", source = "pageSize")
    @Mapping(target = "page.current", source = "pageNumber")
    @Mapping(target = "results", source = "records")
    PageVO<GoodsVO> toPageVO(Page<Goods> goodsPage);

    /**
     * 转实体
     *
     * @param goodsSaveVO 前端交互用户
     * @return
     */
    Goods toDO(GoodsSaveVO goodsSaveVO);

    /**
     * 修改转实体
     */
    Goods toDO(GoodsUpdateVO goodsUpdateVO);

    /**
     * 修改状态转实体
     */
    Goods toDO(GoodsStatusVO goodsStatusVO);

    /**
     * 转前端交互商品下拉框数据
     */
    List<GoodsCategorySelectVO> toSelectVO(List<GoodsCategory> goodsCats);

    /**
     * 转dto
     */
    GoodsConditionDTO toDTO(GoodsConditionVO condition);
}
