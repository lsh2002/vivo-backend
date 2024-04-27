package com.lsh.vivo.bean.response.goods.picture;

import com.lsh.vivo.bean.response.system.BaseEntityIdVO;
import com.lsh.vivo.enumerate.GoodsPictureEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 * @author ASUS
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class GoodsPictureVO extends BaseEntityIdVO {

    /**
     * 商品状态
     */
    private GoodsPictureEnum master;

    /**
     * 商品状态
     */
    private String url;
}