package com.lsh.vivo.entity.table;

import com.mybatisflex.core.query.QueryColumn;
import com.mybatisflex.core.table.TableDef;

// Auto generate by mybatis-flex, do not modify it.
public class ShoppingCartTableDef extends TableDef {

    /**
     * 购物车

 @author ASUS
     */
    public static final ShoppingCartTableDef SHOPPING_CART = new ShoppingCartTableDef();

    /**
     * 主键
     */
    public final QueryColumn ID = new QueryColumn(this, "id");

    /**
     * 商品数量
     */
    public final QueryColumn NUM = new QueryColumn(this, "num");

    /**
     * 状态
     */
    public final QueryColumn STATUS = new QueryColumn(this, "status");

    /**
     * 用户id
     */
    public final QueryColumn USER_ID = new QueryColumn(this, "user_id");

    /**
     * 创建人
     */
    public final QueryColumn CREATOR = new QueryColumn(this, "creator");

    /**
     * 商品id
     */
    public final QueryColumn GOODS_ID = new QueryColumn(this, "goods_id");

    /**
     * 更新人
     */
    public final QueryColumn MODIFIER = new QueryColumn(this, "modifier");

    /**
     * 乐观锁
     */
    public final QueryColumn REVISION = new QueryColumn(this, "revision");

    /**
     * 创建人id
     */
    public final QueryColumn CREATOR_ID = new QueryColumn(this, "creator_id");

    /**
     * 创建时间
     */
    public final QueryColumn CREATE_TIME = new QueryColumn(this, "create_time");

    /**
     * 更新人id
     */
    public final QueryColumn MODIFIER_ID = new QueryColumn(this, "modifier_id");

    /**
     * 更新时间
     */
    public final QueryColumn MODIFIED_TIME = new QueryColumn(this, "modified_time");

    /**
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{ID, NUM, STATUS, USER_ID, CREATOR, GOODS_ID, MODIFIER, REVISION, CREATOR_ID, CREATE_TIME, MODIFIER_ID, MODIFIED_TIME};

    public ShoppingCartTableDef() {
        super("", "shopping_cart");
    }

}
