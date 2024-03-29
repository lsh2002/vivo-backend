package com.lsh.vivo.controller;

import com.lsh.vivo.service.FilesService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author lsh
 * @since 2023/10/10 13:47
 */
@RestController
@RequestMapping("/file")
@RequiredArgsConstructor
public class FilesController {

    private final FilesService filesService;

    @PostMapping("/upload")
    public String upload(@RequestParam MultipartFile file) {
        return filesService.upload(file);
    }
}
