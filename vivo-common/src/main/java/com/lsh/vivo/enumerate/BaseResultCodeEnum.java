package com.lsh.vivo.enumerate;

import lombok.Getter;

/**
 * BaseResultCode类
 *
 * @author lsh
 * @version 1.0.0
 * @since 2023-11-22 18:45
 */
@Getter
public enum BaseResultCodeEnum {

    /**
     * 成功
     */
    SUCCESS("S00000", "成功！"),
    /**
     * 未知异常
     */
    ERROR("E00001", "未知异常！"),
    /**
     * 账户或者密码错误
     */
    ERROR_USER_OR_PASSWORD("E00002", "账户或者密码错误！"),
    /**
     * 账户或者密码错误
     */
    ERROR_ACCOUNT("E00003", "对不起，你当前账户因异常访问已被锁定，请联系管理员进行申诉！"),
    /**
     * 账户或者密码错误
     */
    ERROR_NOT_LOGIN("E00004", "请先进行登录！"),
    /**
     * 非法访问
     */
    ERROR_ILLEGAL_ACCESS("E00005", "非法访问！"),
    /**
     * 登录失效，请重新登录
     */
    ERROR_LOGIN_EXPIRED("E00006", "登录失效，请重新登录！"),
    /**
     * 另一台设备登录了当前用户
     */
    ERROR_SECOND_DEVICE_ONLINE("E00007", "另一台设备登录了当前用户！"),

    /**
     * 对不起，您暂未授权访问此项目
     */
    ERROR_NOR_PERMISSION("E00009", "对不起，您暂未授权访问此项目！"),
    /**
     * 参数错误
     */
    ERROR_ARGUMENT("E00010", "参数错误！"),
    /**
     * 验证码错误
     */
    ERROR_VERIFY_TIMEOUT("E00011", "验证码错误或已失效！"),
    /**
     * 输入用户名不存在
     */
    ERROR_UNKNOWN_ACCOUNT("E00012", "输入用户名不存在！"),
    /**
     * 数据发生变化，请刷新后再试
     */
    ERROR_DATA_CHANGED("E00013", "数据发生变化，请刷新后再试！"),
    /**
     * token无效
     */
    ERROR_TOKEN("E00014", "token无效！"),
    /**
     * 账号已存在
     */
    ERROR_EXISTED_ACCOUNT("E00015", "账号已存在！"),
    /**
     * 手机号已存在
     */
    ERROR_EXISTED_MOBILE("E00016", "手机号已存在！"),
    /**
     * 无效密码
     */
    ERROR_INVALID_PASSWORD("E00017", "无效密码！"),
    /**
     * 数据不存在
     */
    ERROR_UNKNOWN_DATA("E00018", "数据不存在！"),
    /**
     * 不允许绑定超级管理员
     */
    ERROR_ROLE_BINDING("E00019", "不可绑定超级管理员"),
    /**
     * 用户昵称已存在
     */
    ERROR_USER_NICKNAME_EXIST("E00020", "用户昵称已存在！"),
    /**
     * 用户手机号已存在
     */
    ERROR_USER_PHONE_EXIST("E00021", "用户手机号已存在！"),
    /**
     * 无权修改当前用户绑定角色的权限
     */
    ERROR_CHANGE_BINDING_ROLE_PERM("E00022", "不可修改自身绑定角色权限"),
    /**
     * 非超级管理员用户不可修改超级管理员信息
     */
    ERROR_IDENTITY_OPERATION("E00023", "无权修改超级管理员信息"),
    /**
     * 角色已存在
     */
    ERROR_ROLE_NAME_EXIST("E00024", "角色已存在！"),
    /**
     * 商品类别已存在
     */
    ERROR_GOODS_CATEGORY_EXIST("E00025", "商品类别已存在！"),
    /**
     * 商品已存在
     */
    ERROR_GOODS_EXIST("E00026", "该商品已存在！"),
    /**
     * 账户已停用/注销
     */
    ERROR_ACCOUNT_HALT("E00038", "此账户已停用/注销，无法登录！"),
    /**
     * 用户所绑定角色权限发生变化
     */
    ERROR_USER_BINDING_ROLE_PERM_CHANGE("E00053", "所属权限发生变更，请重新登录！"),
    /**
     * 商品已存在
     */
    ERROR_EXISTED_GOODS_SKU("E00054", "商品已存在！"),

    ERROR_STOCK_COUNT("E00054", "库存数据错误，请重新输入！"),

    ERROR_REGISTER_CHECKPASS("E00055", "重复密码错误，请重新输入！"),

    ERROR_USER_USERNAME_EXIST("E00056", "用户名已存在"),

    ERROR_USER_PASSWORD_ERROR("E00057", "旧密码输入错误");

    /**
     * 代码
     */
    private final String code;
    /**
     * 描述
     */
    private final String message;

    BaseResultCodeEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

}
