package com.lsh.vivo.configuration;

import com.lsh.vivo.entity.User;
import com.lsh.vivo.handler.CustomSnowflakeIdentifierGenerator;
import com.lsh.vivo.listener.CustomSecurityListener;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.core.FlexGlobalConfig;
import com.mybatisflex.core.audit.AuditManager;
import com.mybatisflex.core.dialect.DbType;
import com.mybatisflex.core.keygen.KeyGeneratorFactory;
import com.mybatisflex.spring.boot.MyBatisFlexCustomizer;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

/**
 * MybatisFlexConfiguration类
 *
 * @author AdolphLv
 * @version 1.0.0
 * 2023/10/2 10:18
 */
@Slf4j
@Configuration
public class MybatisFlexConfiguration implements MyBatisFlexCustomizer {

    @Resource
    private CustomSnowflakeIdentifierGenerator customSnowflakeIdentifierGenerator;

    @Resource
    private CustomSecurityListener customSecurityListener;

    @Override
    public void customize(FlexGlobalConfig flexGlobalConfig) {
        flexGlobalConfig.setPrintBanner(false);
        flexGlobalConfig.setDbType(DbType.MYSQL);

        //自定义主键策略
        KeyGeneratorFactory.register("customId", customSnowflakeIdentifierGenerator);

        FlexGlobalConfig.KeyConfig keyConfig = new FlexGlobalConfig.KeyConfig();
        keyConfig.setKeyType(KeyType.Generator);
        keyConfig.setValue("customId");
        keyConfig.setBefore(true);

        flexGlobalConfig.setKeyConfig(keyConfig);
        flexGlobalConfig.setVersionColumn("revision");

        flexGlobalConfig.registerSetListener(customSecurityListener, User.class);
        // 开启审计功能
        AuditManager.setAuditEnable(true);

        // 设置 SQL 审计收集器
        AuditManager.setMessageCollector(auditMessage -> log.info("{},return {} 条,{}ms", auditMessage.getFullSql(), auditMessage.getQueryCount(), auditMessage.getElapsedTime()));
    }
}
