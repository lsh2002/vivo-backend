package com.lsh.vivo.entity.table;

import com.mybatisflex.core.query.QueryColumn;
import com.mybatisflex.core.table.TableDef;

// Auto generate by mybatis-flex, do not modify it.
public class GoodsSkuTableDef extends TableDef {

    /**
     * @author ASUS
 @TableName goods_sku
     */
    public static final GoodsSkuTableDef GOODS_SKU = new GoodsSkuTableDef();

    /**
     * 主键
     */
    public final QueryColumn ID = new QueryColumn(this, "id");

    /**
     * 定价
     */
    public final QueryColumn PRICE = new QueryColumn(this, "price");

    /**
     * 销售量
     */
    public final QueryColumn SALES = new QueryColumn(this, "sales");

    /**
     * 库存
     */
    public final QueryColumn STOCK = new QueryColumn(this, "stock");

    /**
     * 备注
     */
    public final QueryColumn REMARK = new QueryColumn(this, "remark");

    /**
     * 状态
     */
    public final QueryColumn STATUS = new QueryColumn(this, "status");

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
     * 商品名称
     */
    public final QueryColumn GOODS_NAME = new QueryColumn(this, "goods_name");

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
     * 售价
     */
    public final QueryColumn SELLING_PRICE = new QueryColumn(this, "selling_price");

    /**
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{ID, PRICE, SALES, STOCK, REMARK, STATUS, CREATOR, GOODS_ID, MODIFIER, REVISION, CREATOR_ID, GOODS_NAME, CREATE_TIME, MODIFIER_ID, MODIFIED_TIME, SELLING_PRICE};

    public GoodsSkuTableDef() {
        super("", "goods_sku");
    }

}
