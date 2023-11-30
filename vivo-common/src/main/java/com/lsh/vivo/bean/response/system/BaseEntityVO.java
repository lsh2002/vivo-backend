package com.lsh.vivo.bean.response.system;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 基础返回对象属性
 *
 * @author AdolphLv
 * @version 1.0.0
 * 2023/9/1 22:57
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class BaseEntityVO extends BaseEntityIdVO {

    /**
     * 乐观锁
     */
    @NotBlank()
    private Integer revision;

    /**
     * 创建人id
     */
    private String creatorId;

    /**
     * 创建人
     */
    private String creator;

    /**
     * 创建时间
     */
    private Long createTime;

    /**
     * 更新人id
     */
    private String modifierId;

    /**
     * 更新人
     */
    private String modifier;

    /**
     * 更新时间
     */
    private Long modifiedTime;
}
