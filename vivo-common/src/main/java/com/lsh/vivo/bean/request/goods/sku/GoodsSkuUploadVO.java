package com.lsh.vivo.bean.request.goods.sku;

import com.lsh.vivo.bean.response.system.BaseVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author lsh
 * @version 1.0.0
 * @since 2024-01-02 11:25
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class GoodsSkuUploadVO extends BaseVO {

    private List<String> goodsPictures;
}
