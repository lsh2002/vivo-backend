package com.lsh.vivo.mapper.struct;

import com.lsh.vivo.bean.request.goods.cat.GoodsCategorySaveVO;
import com.lsh.vivo.bean.request.goods.cat.GoodsCategoryStatusVO;
import com.lsh.vivo.bean.request.goods.cat.GoodsCategoryUpdateVO;
import com.lsh.vivo.bean.response.goods.cat.GoodsCategorySelectVO;
import com.lsh.vivo.bean.response.goods.cat.GoodsCategoryVO;
import com.lsh.vivo.bean.response.system.PageResult;
import com.lsh.vivo.bean.response.system.PageVO;
import com.lsh.vivo.entity.GoodsCategory;
import com.lsh.vivo.enumerate.CommonStatusEnum;
import com.lsh.vivo.enumerate.GoodsCatLevelEnum;
import com.lsh.vivo.util.MapperStructTypeConvert;
import com.mybatisflex.core.paginate.Page;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-02-19T15:43:10+0800",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17 (Oracle Corporation)"
)
public class GoodsCategoryMppImpl implements GoodsCategoryMpp {

    private final MapperStructTypeConvert mapperStructTypeConvert = new MapperStructTypeConvert();

    @Override
    public GoodsCategoryVO toVO(GoodsCategory goodsCategory) {
        if ( goodsCategory == null ) {
            return null;
        }

        GoodsCategoryVO goodsCategoryVO = new GoodsCategoryVO();

        goodsCategoryVO.setCreateTime( mapperStructTypeConvert.localDateTimeToLong( goodsCategory.getCreateTime() ) );
        goodsCategoryVO.setModifiedTime( mapperStructTypeConvert.localDateTimeToLong( goodsCategory.getModifiedTime() ) );
        goodsCategoryVO.setId( goodsCategory.getId() );
        goodsCategoryVO.setRevision( goodsCategory.getRevision() );
        goodsCategoryVO.setCreatorId( goodsCategory.getCreatorId() );
        goodsCategoryVO.setCreator( goodsCategory.getCreator() );
        goodsCategoryVO.setModifierId( goodsCategory.getModifierId() );
        goodsCategoryVO.setModifier( goodsCategory.getModifier() );
        goodsCategoryVO.setName( goodsCategory.getName() );
        if ( goodsCategory.getLevel() != null ) {
            goodsCategoryVO.setLevel( Enum.valueOf( GoodsCatLevelEnum.class, goodsCategory.getLevel() ) );
        }
        goodsCategoryVO.setParentName( goodsCategory.getParentName() );
        if ( goodsCategory.getStatus() != null ) {
            goodsCategoryVO.setStatus( Enum.valueOf( CommonStatusEnum.class, goodsCategory.getStatus() ) );
        }

        return goodsCategoryVO;
    }

    @Override
    public PageVO<GoodsCategoryVO> toPageVO(Page<GoodsCategory> goodsCategoryPage) {
        if ( goodsCategoryPage == null ) {
            return null;
        }

        PageVO<GoodsCategoryVO> pageVO = new PageVO<GoodsCategoryVO>();

        pageVO.setPage( goodsCategoryPageToPageResult( goodsCategoryPage ) );
        if ( goodsCategoryPage.hasRecords() ) {
            pageVO.setResults( goodsCategoryListToGoodsCategoryVOList( goodsCategoryPage.getRecords() ) );
        }

        return pageVO;
    }

    @Override
    public GoodsCategory toDO(GoodsCategorySaveVO goodsCatSaveVO) {
        if ( goodsCatSaveVO == null ) {
            return null;
        }

        GoodsCategory goodsCategory = new GoodsCategory();

        goodsCategory.setName( goodsCatSaveVO.getName() );
        goodsCategory.setParentId( goodsCatSaveVO.getParentId() );
        goodsCategory.setParentName( goodsCatSaveVO.getParentName() );
        if ( goodsCatSaveVO.getLevel() != null ) {
            goodsCategory.setLevel( goodsCatSaveVO.getLevel().name() );
        }

        return goodsCategory;
    }

    @Override
    public GoodsCategory toDO(GoodsCategoryUpdateVO goodsCatUpdateVO) {
        if ( goodsCatUpdateVO == null ) {
            return null;
        }

        GoodsCategory goodsCategory = new GoodsCategory();

        goodsCategory.setId( goodsCatUpdateVO.getId() );
        goodsCategory.setRevision( goodsCatUpdateVO.getRevision() );
        goodsCategory.setName( goodsCatUpdateVO.getName() );
        goodsCategory.setParentId( goodsCatUpdateVO.getParentId() );
        goodsCategory.setParentName( goodsCatUpdateVO.getParentName() );
        if ( goodsCatUpdateVO.getLevel() != null ) {
            goodsCategory.setLevel( goodsCatUpdateVO.getLevel().name() );
        }

        return goodsCategory;
    }

    @Override
    public GoodsCategory toDO(GoodsCategoryStatusVO goodsCatStatusVO) {
        if ( goodsCatStatusVO == null ) {
            return null;
        }

        GoodsCategory goodsCategory = new GoodsCategory();

        goodsCategory.setId( goodsCatStatusVO.getId() );
        if ( goodsCatStatusVO.getStatus() != null ) {
            goodsCategory.setStatus( goodsCatStatusVO.getStatus().name() );
        }
        goodsCategory.setRevision( goodsCatStatusVO.getRevision() );

        return goodsCategory;
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

    protected PageResult goodsCategoryPageToPageResult(Page<GoodsCategory> page) {
        if ( page == null ) {
            return null;
        }

        PageResult pageResult = new PageResult();

        pageResult.setTotal( page.getTotalRow() );
        pageResult.setSize( page.getPageSize() );
        pageResult.setCurrent( page.getPageNumber() );

        return pageResult;
    }

    protected List<GoodsCategoryVO> goodsCategoryListToGoodsCategoryVOList(List<GoodsCategory> list) {
        if ( list == null ) {
            return null;
        }

        List<GoodsCategoryVO> list1 = new ArrayList<GoodsCategoryVO>( list.size() );
        for ( GoodsCategory goodsCategory : list ) {
            list1.add( toVO( goodsCategory ) );
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
