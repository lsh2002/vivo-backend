package com.lsh.vivo.util;

import com.lsh.vivo.bean.constant.GlobalConstant;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Objects;

/**
 * MapperStructTypeConvert类
 *
 * @author AdolphLv
 * @version 1.0.0
 * 2023/9/22 21:49
 */
@Slf4j
@Component
@Named("MapperStructTypeConvert")
public class MapperStructTypeConvert {

    /**
     * 拼接字符串数组
     */
    @Named("appendContent")
    public String appendContent(List<String> contents) {
        if (CollectionUtils.isEmpty(contents)) {
            return "";
        }
        StringBuilder buffer = new StringBuilder();
        contents.forEach(buffer::append);
        return buffer.toString();
    }

    /**
     * 填充空
     *
     * @param value 任意值
     * @return 字符串空值""
     */
    @Named("toEmpty")
    public String toEmpty(String value) {
        return GlobalConstant.STRING_EMPTY;
    }

    /**
     * LocalDate转时间戳
     *
     * @param date 日期
     * @return 时间戳
     */
    @Named("localDateToLong")
    public Long localDateToLong(LocalDate date) {
        if (Objects.isNull(date)) {
            return null;
        }
        return Timestamp.valueOf(date.atStartOfDay()).getTime();
    }

    /**
     * LocalDate转时间戳
     *
     * @param millisecond 日期毫秒值
     * @return 时间戳
     */
    @Named("longToLocalDate")
    public LocalDate longToLocalDate(Long millisecond) {
        if (Objects.isNull(millisecond)) {
            return null;
        }
        return LocalDate.ofInstant(Instant.ofEpochMilli(millisecond), ZoneId.systemDefault());
    }

    /**
     * LocalDateTime转时间戳
     *
     * @param date 日期
     * @return 时间戳
     */
    @Named("localDateTimeToLong")
    public Long localDateTimeToLong(LocalDateTime date) {
        if (Objects.isNull(date)) {
            return null;
        }
        return Timestamp.valueOf(date).getTime();
    }

    /**
     * LocalDate转时间戳
     *
     * @param millisecond 日期毫秒值
     * @return 时间戳
     */
    @Named("longToLocalDateTime")
    public LocalDateTime longToLocalDateTime(Long millisecond) {
        if (Objects.isNull(millisecond)) {
            return null;
        }
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(millisecond), ZoneId.systemDefault());
    }
}
