package com.lsh.vivo.service;


import com.lsh.vivo.entity.Files;
import com.lsh.vivo.service.system.CommonService;
import org.springframework.web.multipart.MultipartFile;


/**
 * (Files)表服务接口
 *
 * @author Silvery
 * @since 2023-10-10 13:51:34
 */
public interface FilesService extends CommonService<Files> {

    /**
     * 上传文件
     *
     * @param file 文件
     * @return 文件路径
     */
    String upload(MultipartFile file);
}
