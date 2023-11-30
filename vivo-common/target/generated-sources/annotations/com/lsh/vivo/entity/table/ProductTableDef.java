package com.lsh.vivo.entity.table;

import com.mybatisflex.core.query.QueryColumn;
import com.mybatisflex.core.table.TableDef;

// Auto generate by mybatis-flex, do not modify it.
public class ProductTableDef extends TableDef {

    /**
     * 商品
 @author ASUS
     */
    public static final ProductTableDef PRODUCT = new ProductTableDef();

    /**
     * 主键
     */
    public final QueryColumn ID = new QueryColumn(this, "id");

    /**
     * 颜色
     */
    public final QueryColumn COLOR = new QueryColumn(this, "color");

    /**
     * 状态
     */
    public final QueryColumn STATUS = new QueryColumn(this, "status");

    /**
     * 创建人
     */
    public final QueryColumn CREATOR = new QueryColumn(this, "creator");

    /**
     * 更新人
     */
    public final QueryColumn MODIFIER = new QueryColumn(this, "modifier");

    /**
     * 乐观锁
     */
    public final QueryColumn REVISION = new QueryColumn(this, "revision");

    /**
     * 商品版本
     */
    public final QueryColumn VERISION = new QueryColumn(this, "verision");

    /**
     * 创建人id
     */
    public final QueryColumn CREATOR_ID = new QueryColumn(this, "creator_id");

    /**
     * 商品类别id
     */
    public final QueryColumn CATEGORY_ID = new QueryColumn(this, "category_id");

    /**
     * 创建时间
     */
    public final QueryColumn CREATE_TIME = new QueryColumn(this, "create_time");

    /**
     * 更新人id
     */
    public final QueryColumn MODIFIER_ID = new QueryColumn(this, "modifier_id");

    /**
     * 库存数
     */
    public final QueryColumn PRODUCT_NUM = new QueryColumn(this, "product_num");

    /**
     * 商品名称
     */
    public final QueryColumn PRODUCT_NAME = new QueryColumn(this, "product_name");

    /**
     * 更新时间
     */
    public final QueryColumn MODIFIED_TIME = new QueryColumn(this, "modified_time");

    /**
     * 介绍
     */
    public final QueryColumn PRODUCT_INTRO = new QueryColumn(this, "product_intro");

    /**
     * 定价
     */
    public final QueryColumn PRODUCT_PRICE = new QueryColumn(this, "product_price");

    /**
     * 售出数
     */
    public final QueryColumn PRODUCT_SALES = new QueryColumn(this, "product_sales");

    /**
     * 商品图id
     */
    public final QueryColumn PRODUCT_PICTURE_ID = new QueryColumn(this, "product_picture_id");

    /**
     * 售价
     */
    public final QueryColumn PRODUCT_SELLING_PRICE = new QueryColumn(this, "product_selling_price");

    /**
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{ID, COLOR, STATUS, CREATOR, MODIFIER, REVISION, VERISION, CREATOR_ID, CATEGORY_ID, CREATE_TIME, MODIFIER_ID, PRODUCT_NUM, PRODUCT_NAME, MODIFIED_TIME, PRODUCT_INTRO, PRODUCT_PRICE, PRODUCT_SALES, PRODUCT_PICTURE_ID, PRODUCT_SELLING_PRICE};

    public ProductTableDef() {
        super("", "product");
    }

}
