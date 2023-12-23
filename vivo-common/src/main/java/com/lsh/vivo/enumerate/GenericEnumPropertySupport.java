package com.lsh.vivo.enumerate;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.beans.PropertyEditorSupport;

/**
 * GenericEnumPropertySupport类
 *
 * @author AdolphLv
 * @version 1.0.0
 * 2023/9/21 22:57
 */
@Slf4j
@AllArgsConstructor
public class GenericEnumPropertySupport<T extends Enum<T>> extends PropertyEditorSupport {
    final Class<T> enumClass;

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        if (StringUtils.isBlank(text)) {
            setValue(null);
            return;
        }
        int specialTagIndex = text.indexOf(":");
        if (specialTagIndex > 0) {
            text = text.substring(0, specialTagIndex);
        }
        if (enumClass.isEnum()) {
            //必须是枚举
            if (CommonFormEnumParser.class.isAssignableFrom(enumClass)) {
                T[] values = enumClass.getEnumConstants();
                if (values != null && values.length > 0) {
                    // 因为都实现了CommonFormEnumParser接口，随便取一个枚举元素都行
                    CommonFormEnumParser<T> enumParser = (CommonFormEnumParser<T>) values[0];
                    setValue(enumParser.fromValue(text));
                }
            }
        }
    }
}

