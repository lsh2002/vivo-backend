package com.lsh.vivo.bean.response.goods.cat;

import com.lsh.vivo.bean.response.system.BaseEntityIdVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 商品类别下拉菜单
 *
 * @author lsh
 * @version 1.0.0
 * @since 2024-01-02 10:45
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class GoodsCategoryNavVO extends BaseEntityIdVO {

    /**
     * 商品类别名称
     */
    private String name;

    /**
     * 子类
     */
    private List<GoodsCategorySelectVO> children;
}
