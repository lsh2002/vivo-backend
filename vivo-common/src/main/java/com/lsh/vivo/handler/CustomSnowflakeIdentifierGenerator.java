package com.lsh.vivo.handler;

import com.lsh.vivo.annotation.TableIdPrefix;
import com.lsh.vivo.entity.system.BaseEntity;
import com.lsh.vivo.util.Snowflake;
import com.mybatisflex.core.keygen.IKeyGenerator;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 自定义id生成器
 *
 * @author AdolphLv
 * @version 1.0.0
 * 2023/9/21 11:21
 */
@Component
@Slf4j
public class CustomSnowflakeIdentifierGenerator implements IKeyGenerator {

    private Snowflake snowflake;

    @Autowired
    public void setSnowflake(Snowflake snowflake) {
        this.snowflake = snowflake;
    }

    @Override
    public Object generate(Object entity, String keyColumn) {
        BaseEntity baseEntity = (BaseEntity) entity;
        if (StringUtils.isNotBlank(baseEntity.getId())) {
            return baseEntity.getId();
        }
        TableIdPrefix tableIdPrefix = null;
        Class<?> clazz = entity.getClass();
        do {
            tableIdPrefix = clazz.getAnnotation(TableIdPrefix.class);
            clazz = clazz.getSuperclass();
        } while (tableIdPrefix == null && clazz != Object.class);
        String prefix = tableIdPrefix == null ? "" : tableIdPrefix.value();
        return String.format("%s%s", prefix, snowflake.nextIdStr());
    }
}
