package com.lsh.vivo.bean.response.goods.cat;

import com.lsh.vivo.bean.response.system.BaseEntityVO;
import com.lsh.vivo.enumerate.CommonStatusEnum;
import com.lsh.vivo.enumerate.GoodsCatLevelEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 商品类别VO
 *
 * @author lsh
 * @version 1.0.0
 * @since 2024-01-02 10:45
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class GoodsCategoryVO extends BaseEntityVO {

    /**
     * 商品类别名称
     */
    private String name;

    /**
     * 类目级别
     */
    private GoodsCatLevelEnum level;
    
    /**
     * 父类名称
     */
    private String parentName;

    /**
     * 状态
     */
    private CommonStatusEnum status;
}
