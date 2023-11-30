package com.lsh.vivo.enumerate;

/**
 * CommonFormEnumParser类
 *
 * @author lsh
 * @version 1.0
 * @since 2023-09-14 16:29
 */
public interface CommonFormEnumParser<T extends Enum<T>> {

    T fromValue(String input);
}
