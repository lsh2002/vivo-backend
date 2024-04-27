package com.lsh.vivo.entity.table;

import com.mybatisflex.core.query.QueryColumn;
import com.mybatisflex.core.table.TableDef;

// Auto generate by mybatis-flex, do not modify it.
public class GoodsSeckillTableDef extends TableDef {

    /**
     * 商品秒杀

 @author ASUS
 @TableName goods_seckill
     */
    public static final GoodsSeckillTableDef GOODS_SECKILL = new GoodsSeckillTableDef();

    /**
     * 主键
     */
    public final QueryColumn ID = new QueryColumn(this, "id");

    /**
     * 商品id
     */
    public final QueryColumn SKU_ID = new QueryColumn(this, "sku_id");

    /**
     * 状态
     */
    public final QueryColumn STATUS = new QueryColumn(this, "status");

    /**
     * 创建人
     */
    public final QueryColumn CREATOR = new QueryColumn(this, "creator");

    /**
     * 结束时间
     */
    public final QueryColumn END_TIME = new QueryColumn(this, "end_time");

    public final QueryColumn SKU_NAME = new QueryColumn(this, "sku_name");

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
     * 开始时间
     */
    public final QueryColumn START_TIME = new QueryColumn(this, "start_time");

    /**
     * 创建时间
     */
    public final QueryColumn CREATE_TIME = new QueryColumn(this, "create_time");

    /**
     * 更新人id
     */
    public final QueryColumn MODIFIER_ID = new QueryColumn(this, "modifier_id");

    /**
     * 数量
     */
    public final QueryColumn SECKILL_NUM = new QueryColumn(this, "seckill_num");

    /**
     * 更新时间
     */
    public final QueryColumn MODIFIER_TIME = new QueryColumn(this, "modifier_time");

    /**
     * 秒杀价
     */
    public final QueryColumn SECKILL_PRICE = new QueryColumn(this, "seckill_price");

    /**
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{ID, SKU_ID, STATUS, CREATOR, END_TIME, SKU_NAME, MODIFIER, REVISION, CREATOR_ID, START_TIME, CREATE_TIME, MODIFIER_ID, SECKILL_NUM, MODIFIER_TIME, SECKILL_PRICE};

    public GoodsSeckillTableDef() {
        super("", "goods_seckill");
    }

}
