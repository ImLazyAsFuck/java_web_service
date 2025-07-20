package com.ss9.controller;

import com.ss9.model.dto.request.UploadImage;
import com.ss9.service.CloudinaryService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/upload")
public class ImageUploadController {

    private final CloudinaryService cloudinaryService;
    private static final Logger logger = LoggerFactory.getLogger(ImageUploadController.class);

    @PostMapping
    public String uploadImage(@ModelAttribute("image") UploadImage uploadImage) {
        logger.info("Nhận yêu cầu upload ảnh với tên: {}", uploadImage.getImage().getOriginalFilename());

        if (uploadImage.getImage().isEmpty()) {
            logger.warn("Ảnh không tồn tại hoặc rỗng.");
            return "Vui lòng chọn ảnh để upload.";
        }

        String imageUrl = cloudinaryService.uploadImage(uploadImage.getImage());
        return "Upload thành công. Ảnh của bạn: " + imageUrl;
    }
}
