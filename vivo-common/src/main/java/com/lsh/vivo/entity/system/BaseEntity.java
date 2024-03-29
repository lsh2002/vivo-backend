package com.lsh.vivo.entity.system;

import com.mybatisflex.annotation.Id;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 实体基类
 *
 * @author lsh
 * @version 1.0.0
 * 2023/9/17 10:52
 **/
@Data
public class BaseEntity implements Serializable {

    /**
     * 主键
     */
    @Id
    private String id;

    /**
     * 状态
     */
    private String status;

    /**
     * 乐观锁
     */
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
    private LocalDateTime createTime;

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
    private LocalDateTime modifierTime;

    @Override
    protected BaseEntity clone() throws CloneNotSupportedException {
        return (BaseEntity) super.clone();
    }
}
