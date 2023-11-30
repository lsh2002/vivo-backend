package com.lsh.vivo.entity;

import com.lsh.vivo.annotation.TableIdPrefix;
import com.lsh.vivo.entity.system.BaseEntity;
import com.lsh.vivo.listener.CustomFlexListener;
import com.mybatisflex.annotation.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author lsh
 */
@EqualsAndHashCode(callSuper = true)
@Table(value = "carousel",
        onInsert = CustomFlexListener.class,
        onUpdate = CustomFlexListener.class
)
@TableIdPrefix("CAR")
@Data
public class Carousel extends BaseEntity implements Serializable, Cloneable {

    /**
     * 图片地址
     */
    private String imgPath;

    /**
     * 描述
     */
    private String describes;

    @Override
    public Carousel clone() {
        Carousel cloneEntity = null;
        try {
            cloneEntity = (Carousel) super.clone();
        } catch (CloneNotSupportedException ignore) {

        }
        return cloneEntity;
    }
}