package com.ss9.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.ss9.config.CloudinaryProperties;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CloudinaryService {

    private final CloudinaryProperties properties;
    private static final Logger logger = LoggerFactory.getLogger(CloudinaryService.class);

    public String uploadImage(MultipartFile file) {
        logger.info("Bắt đầu upload ảnh: {}", file.getOriginalFilename());

        Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", properties.getCloudName(),
                "api_key", properties.getApiKey(),
                "api_secret", properties.getApiSecret()
        ));

        try {
            Map uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());

            String imageUrl = uploadResult.get("secure_url").toString();
            logger.info("Upload thành công. URL ảnh: {}", imageUrl);

            return imageUrl;
        } catch (IOException e) {
            logger.error("Lỗi khi upload ảnh lên Cloudinary", e);
            throw new RuntimeException("Không thể upload ảnh");
        }
    }
}
