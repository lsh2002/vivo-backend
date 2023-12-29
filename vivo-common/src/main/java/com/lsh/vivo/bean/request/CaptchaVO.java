package com.lsh.vivo.bean.request;

import com.lsh.vivo.bean.request.system.BaseRequest;
import com.lsh.vivo.enumerate.CaptchaGeneratorEnum;
import com.lsh.vivo.enumerate.CaptchaStyleEnum;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.validation.annotation.Validated;

/**
 * 验证码请求参数
 *
 * @author AdolphLv
 * @version 1.0.0
 * 2023/9/21 11:24
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Validated
public class CaptchaVO extends BaseRequest {

    @NotNull
    @Min(10)
    @Max(768)
    private Integer width;

    @NotNull
    @Min(10)
    @Max(100)
    private Integer height;

    @NotNull
    @Min(1)
    @Max(10)
    private Integer count;

    @NotNull
    private CaptchaStyleEnum style;

    private CaptchaGeneratorEnum generator;
}
