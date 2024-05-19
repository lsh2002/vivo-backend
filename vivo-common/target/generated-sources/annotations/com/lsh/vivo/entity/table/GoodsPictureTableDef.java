package com.lsh.vivo.entity.table;

import com.mybatisflex.core.query.QueryColumn;
import com.mybatisflex.core.table.TableDef;

// Auto generate by mybatis-flex, do not modify it.
public class GoodsPictureTableDef extends TableDef {

    /**
     * 商品详情图

 @author ASUS
     */
    public static final GoodsPictureTableDef GOODS_PICTURE = new GoodsPictureTableDef();

    /**
     * 主键
     */
    public final QueryColumn ID = new QueryColumn(this, "id");

    /**
     * 图片地址
     */
    public final QueryColumn URL = new QueryColumn(this, "url");

    /**
     * 是否主图
     */
    public final QueryColumn MASTER = new QueryColumn(this, "master");

    /**
     * 状态
     */
    public final QueryColumn STATUS = new QueryColumn(this, "status");

    /**
     * 创建人
     */
    public final QueryColumn CREATOR = new QueryColumn(this, "creator");

    public final QueryColumn DELETED = new QueryColumn(this, "deleted");

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
     * 商品Skuid
     */
    public final QueryColumn GOODS_SKU_ID = new QueryColumn(this, "goods_sku_id");

    /**
     * 更新人id
     */
    public final QueryColumn MODIFIER_ID = new QueryColumn(this, "modifier_id");

    /**
     * 描述
     */
    public final QueryColumn DESCRIPTION = new QueryColumn(this, "description");

    /**
     * 更新时间
     */
    public final QueryColumn MODIFIER_TIME = new QueryColumn(this, "modifier_time");

    /**
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{ID, URL, MASTER, STATUS, CREATOR, MODIFIER, REVISION, CREATOR_ID, CREATE_TIME, GOODS_SKU_ID, MODIFIER_ID, DESCRIPTION, MODIFIER_TIME};

    public GoodsPictureTableDef() {
        super("", "goods_picture");
    }

}
