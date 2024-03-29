package com.lsh.vivo.service.impl;


import cn.hutool.core.io.FileUtil;
import com.lsh.vivo.entity.Files;
import com.lsh.vivo.mapper.FilesMapper;
import com.lsh.vivo.service.FilesService;
import com.lsh.vivo.service.system.impl.CommonServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * (Files)表服务实现类
 *
 * @author lsh
 * @since 2023-10-10 13:51:34
 */
@Service("filesService")
public class FilesServiceImpl extends CommonServiceImpl<FilesMapper, Files> implements FilesService {

    @Value("${files.upload.path}")
    private String fileUploadPath;

    @Override
    public String upload(MultipartFile file) {
        String originalFilename = file.getOriginalFilename();
        String type = FileUtil.extName(originalFilename);
        long size = file.getSize();
        if (size > 2048 * 2048) {
            throw new RuntimeException("文件过大");
        }
        File uploadParentFiles = new File(fileUploadPath);
        if (!uploadParentFiles.exists()) {
            if (!uploadParentFiles.mkdirs()) {
                throw new RuntimeException("未知错误");
            }
        }
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        File currentFile = new File(new File(fileUploadPath).getAbsolutePath() + "/" + uuid + "." + type);
        try {
            file.transferTo(currentFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Files files = new Files();
        files.setType(type);
        files.setUrl(uuid + "." + type);
        mapper.insert(files);
        return uuid + "." + type;
    }
}
