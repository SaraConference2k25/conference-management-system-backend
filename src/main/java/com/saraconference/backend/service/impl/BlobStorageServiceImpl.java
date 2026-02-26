package com.saraconference.backend.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.saraconference.backend.service.BlobStorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@Service
public class BlobStorageServiceImpl implements BlobStorageService {

    private static final Logger logger = LoggerFactory.getLogger(BlobStorageServiceImpl.class);

    @Value("${cloudinary.cloud-name}")
    private String cloudName;

    @Value("${cloudinary.api-key}")
    private String apiKey;

    @Value("${cloudinary.api-secret}")
    private String apiSecret;

    private Cloudinary getCloudinary() {
        return new Cloudinary(ObjectUtils.asMap(
                "cloud_name", cloudName,
                "api_key", apiKey,
                "api_secret", apiSecret,
                "secure", true
        ));
    }

    @Override
    public String uploadFile(MultipartFile file) throws Exception {
        try {
            Cloudinary cloudinary = getCloudinary();
            Map<?, ?> uploadResult = cloudinary.uploader().upload(
                    file.getBytes(),
                    ObjectUtils.asMap(
                            "resource_type", "auto",
                            "folder", "saraconference"
                    )
            );
            String fileUrl = (String) uploadResult.get("secure_url");
            logger.info("File uploaded successfully to Cloudinary: {}", fileUrl);
            return fileUrl;
        } catch (Exception e) {
            logger.error("Error uploading file to Cloudinary: {}", e.getMessage());
            throw new Exception("Error uploading file: " + e.getMessage(), e);
        }
    }

    @Override
    public byte[] downloadFile(String fileName) throws Exception {
        try {
            Cloudinary cloudinary = getCloudinary();
            // fileName here should be the public_id from Cloudinary
            String url = cloudinary.url().generate(fileName);
            logger.info("Generated download URL for: {}", fileName);
            // Fetch the bytes from the URL
            return new java.net.URL(url).openStream().readAllBytes();
        } catch (Exception e) {
            logger.error("Error downloading file from Cloudinary: {}", e.getMessage());
            throw new Exception("Error downloading file: " + e.getMessage(), e);
        }
    }

    @Override
    public void deleteFile(String fileName) throws Exception {
        try {
            Cloudinary cloudinary = getCloudinary();
            // fileName should be the public_id stored in DB
            Map<?, ?> result = cloudinary.uploader().destroy(
                    fileName,
                    ObjectUtils.asMap("resource_type", "raw")
            );
            logger.info("File deleted from Cloudinary: {}, result: {}", fileName, result.get("result"));
        } catch (Exception e) {
            logger.error("Error deleting file from Cloudinary: {}", e.getMessage());
            throw new Exception("Error deleting file: " + e.getMessage(), e);
        }
    }
}
