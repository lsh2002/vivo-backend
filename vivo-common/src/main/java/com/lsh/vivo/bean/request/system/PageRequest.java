package com.lsh.vivo.bean.request.system;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;


/**
 * PageRequestDTO类
 *
 * @author AdolphLv
 * @version 1.0.0
 * 2023/9/21 11:24
 */
@EqualsAndHashCode(callSuper = true)
@Slf4j
@Data
@Validated
public class PageRequest extends BaseRequest {

    /**
     * 分页页码
     */
    @NotNull
    @Min(value = 1)
    private Integer page;

    /**
     * 每页显示数
     */
    @NotNull
    @Min(value = 10)
    @Max(value = 50)
    private Integer size;

    /**
     * 上一次查到最后大ID号
     */
    private String lastId;
}
