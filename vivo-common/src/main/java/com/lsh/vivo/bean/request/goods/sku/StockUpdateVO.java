package com.lsh.vivo.bean.request.goods.sku;

import com.lsh.vivo.bean.response.system.BaseUpdateVO;
import com.lsh.vivo.enumerate.StockStatusEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author ASUS
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class StockUpdateVO extends BaseUpdateVO {

    /**
     * 库存
     */
    private Integer stockChange;

    /**
     * 库存
     */
    private StockStatusEnum stockStatus;
}