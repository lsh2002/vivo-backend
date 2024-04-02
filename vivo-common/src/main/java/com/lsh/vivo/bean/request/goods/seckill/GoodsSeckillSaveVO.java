package com.lsh.vivo.bean.request.goods.seckill;

import com.lsh.vivo.bean.request.system.BaseRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 * @author pjw
 * @version 1.0.0
 * @since 2023-10-03 17:01
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class GoodsSeckillSaveVO extends BaseRequest {

    private String skuId;

    private String skuName;

    private Double seckillPrice;

    private Integer seckillNum;

    private Long startTime;

    private Long endTime;
}
