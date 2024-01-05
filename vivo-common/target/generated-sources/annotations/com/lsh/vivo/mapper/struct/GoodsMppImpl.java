package com.lsh.vivo.mapper.struct;

import com.lsh.vivo.bean.dto.goods.GoodsConditionDTO;
import com.lsh.vivo.bean.request.goods.GoodsConditionVO;
import com.lsh.vivo.bean.request.goods.GoodsSaveVO;
import com.lsh.vivo.bean.request.goods.GoodsStatusVO;
import com.lsh.vivo.bean.request.goods.GoodsUpdateVO;
import com.lsh.vivo.bean.response.goods.GoodsVO;
import com.lsh.vivo.bean.response.goods.cat.GoodsCategorySelectVO;
import com.lsh.vivo.bean.response.system.PageResult;
import com.lsh.vivo.bean.response.system.PageVO;
import com.lsh.vivo.entity.Goods;
import com.lsh.vivo.entity.GoodsCategory;
import com.lsh.vivo.enumerate.GoodsCatLevelEnum;
import com.lsh.vivo.enumerate.GoodsStatusEnum;
import com.lsh.vivo.util.MapperStructTypeConvert;
import com.mybatisflex.core.paginate.Page;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-01-05T10:33:32+0800",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.2 (Red Hat, Inc.)"
)
public class GoodsMppImpl implements GoodsMpp {

    private final MapperStructTypeConvert mapperStructTypeConvert = new MapperStructTypeConvert();

    @Override
    public GoodsVO toVO(Goods goods) {
        if ( goods == null ) {
            return null;
        }

        GoodsVO goodsVO = new GoodsVO();

        goodsVO.setCreateTime( mapperStructTypeConvert.localDateTimeToLong( goods.getCreateTime() ) );
        goodsVO.setModifiedTime( mapperStructTypeConvert.localDateTimeToLong( goods.getModifiedTime() ) );
        goodsVO.setId( goods.getId() );
        goodsVO.setRevision( goods.getRevision() );
        goodsVO.setCreatorId( goods.getCreatorId() );
        goodsVO.setCreator( goods.getCreator() );
        goodsVO.setModifierId( goods.getModifierId() );
        goodsVO.setModifier( goods.getModifier() );
        goodsVO.setName( goods.getName() );
        goodsVO.setCategoryId( goods.getCategoryId() );
        goodsVO.setCategory( goods.getCategory() );
        goodsVO.setDescription( goods.getDescription() );
        if ( goods.getStatus() != null ) {
            goodsVO.setStatus( Enum.valueOf( GoodsStatusEnum.class, goods.getStatus() ) );
        }

        return goodsVO;
    }

    @Override
    public PageVO<GoodsVO> toPageVO(Page<Goods> goodsPage) {
        if ( goodsPage == null ) {
            return null;
        }

        PageVO<GoodsVO> pageVO = new PageVO<GoodsVO>();

        pageVO.setPage( goodsPageToPageResult( goodsPage ) );
        if ( goodsPage.hasRecords() ) {
            pageVO.setResults( goodsListToGoodsVOList( goodsPage.getRecords() ) );
        }

        return pageVO;
    }

    @Override
    public Goods toDO(GoodsSaveVO goodsSaveVO) {
        if ( goodsSaveVO == null ) {
            return null;
        }

        Goods goods = new Goods();

        goods.setName( goodsSaveVO.getName() );
        goods.setCategoryId( goodsSaveVO.getCategoryId() );
        goods.setCategory( goodsSaveVO.getCategory() );
        goods.setDescription( goodsSaveVO.getDescription() );

        return goods;
    }

    @Override
    public Goods toDO(GoodsUpdateVO goodsUpdateVO) {
        if ( goodsUpdateVO == null ) {
            return null;
        }

        Goods goods = new Goods();

        goods.setId( goodsUpdateVO.getId() );
        goods.setRevision( goodsUpdateVO.getRevision() );
        goods.setName( goodsUpdateVO.getName() );
        goods.setCategoryId( goodsUpdateVO.getCategoryId() );
        goods.setCategory( goodsUpdateVO.getCategory() );
        goods.setDescription( goodsUpdateVO.getDescription() );

        return goods;
    }

    @Override
    public Goods toDO(GoodsStatusVO goodsStatusVO) {
        if ( goodsStatusVO == null ) {
            return null;
        }

        Goods goods = new Goods();

        goods.setId( goodsStatusVO.getId() );
        if ( goodsStatusVO.getStatus() != null ) {
            goods.setStatus( goodsStatusVO.getStatus().name() );
        }
        goods.setRevision( goodsStatusVO.getRevision() );

        return goods;
    }

    @Override
    public List<GoodsCategorySelectVO> toSelectVO(List<GoodsCategory> goodsCats) {
        if ( goodsCats == null ) {
            return null;
        }

        List<GoodsCategorySelectVO> list = new ArrayList<GoodsCategorySelectVO>( goodsCats.size() );
        for ( GoodsCategory goodsCategory : goodsCats ) {
            list.add( goodsCategoryToGoodsCategorySelectVO( goodsCategory ) );
        }

        return list;
    }

    @Override
    public GoodsConditionDTO toDTO(GoodsConditionVO condition) {
        if ( condition == null ) {
            return null;
        }

        GoodsConditionDTO goodsConditionDTO = new GoodsConditionDTO();

        goodsConditionDTO.setPage( condition.getPage() );
        goodsConditionDTO.setSize( condition.getSize() );
        goodsConditionDTO.setLastId( condition.getLastId() );
        goodsConditionDTO.setName( condition.getName() );
        goodsConditionDTO.setCategoryId( condition.getCategoryId() );
        goodsConditionDTO.setDescription( condition.getDescription() );
        if ( condition.getStatus() != null ) {
            goodsConditionDTO.setStatus( condition.getStatus().name() );
        }

        return goodsConditionDTO;
    }

    protected PageResult goodsPageToPageResult(Page<Goods> page) {
        if ( page == null ) {
            return null;
        }

        PageResult pageResult = new PageResult();

        pageResult.setTotal( page.getTotalRow() );
        pageResult.setSize( page.getPageSize() );
        pageResult.setCurrent( page.getPageNumber() );

        return pageResult;
    }

    protected List<GoodsVO> goodsListToGoodsVOList(List<Goods> list) {
        if ( list == null ) {
            return null;
        }

        List<GoodsVO> list1 = new ArrayList<GoodsVO>( list.size() );
        for ( Goods goods : list ) {
            list1.add( toVO( goods ) );
        }

        return list1;
    }

    protected GoodsCategorySelectVO goodsCategoryToGoodsCategorySelectVO(GoodsCategory goodsCategory) {
        if ( goodsCategory == null ) {
            return null;
        }

        GoodsCategorySelectVO goodsCategorySelectVO = new GoodsCategorySelectVO();

        goodsCategorySelectVO.setId( goodsCategory.getId() );
        goodsCategorySelectVO.setName( goodsCategory.getName() );
        if ( goodsCategory.getLevel() != null ) {
            goodsCategorySelectVO.setLevel( Enum.valueOf( GoodsCatLevelEnum.class, goodsCategory.getLevel() ) );
        }

        return goodsCategorySelectVO;
    }
}
