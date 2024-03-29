package com.lsh.vivo.entity;


import com.lsh.vivo.annotation.TableIdPrefix;
import com.lsh.vivo.entity.system.BaseEntity;
import com.lsh.vivo.listener.CustomFlexListener;
import com.mybatisflex.annotation.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * (File)表实体类
 *
 * @author makejava
 * @since 2023-05-03 00:36:12
 */
@EqualsAndHashCode(callSuper = true)
@Table(value = "files",
        onInsert = CustomFlexListener.class,
        onUpdate = CustomFlexListener.class
)
@TableIdPrefix("FIL")
@Data
public class Files extends BaseEntity implements Serializable {

    /**
     * 文件类型
     */
    private String type;
    /**
     * 链接
     */
    private String url;

    @Override
    public Files clone() {
        Files cloneEntity = null;
        try {
            cloneEntity = (Files) super.clone();
        } catch (CloneNotSupportedException ignore) {

        }
        return cloneEntity;
    }
}
