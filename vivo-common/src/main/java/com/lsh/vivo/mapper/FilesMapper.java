package com.lsh.vivo.mapper;

import com.lsh.vivo.entity.Files;
import com.lsh.vivo.mapper.system.CommonMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * (Files)表数据库访问层
 *
 * @author lsh
 * 2023-10-10 13:51:34
 */
@Mapper
public interface FilesMapper extends CommonMapper<Files> {

}
