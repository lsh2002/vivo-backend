package com.lsh.vivo.entity.table;

import com.mybatisflex.core.query.QueryColumn;
import com.mybatisflex.core.table.TableDef;

// Auto generate by mybatis-flex, do not modify it.
public class GoodsCategoryTableDef extends TableDef {

    /**
     * 商品类别

 @author ASUS
     */
    public static final GoodsCategoryTableDef GOODS_CATEGORY = new GoodsCategoryTableDef();

    /**
     * 主键
     */
    public final QueryColumn ID = new QueryColumn(this, "id");

    /**
     * 商品类别名称
     */
    public final QueryColumn NAME = new QueryColumn(this, "name");

    /**
     * 类目级别
     */
    public final QueryColumn LEVEL = new QueryColumn(this, "level");

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
     * 父类id
     */
    public final QueryColumn PARENT_ID = new QueryColumn(this, "parent_id");

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
     * 父类名称
     */
    public final QueryColumn PARENT_NAME = new QueryColumn(this, "parent_name");

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
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{ID, NAME, LEVEL, STATUS, CREATOR, MODIFIER, PARENT_ID, REVISION, CREATOR_ID, CREATE_TIME, MODIFIER_ID, PARENT_NAME, MODIFIED_TIME};

    public GoodsCategoryTableDef() {
        super("", "goods_category");
    }

}
