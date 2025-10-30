package com.saraconference.backend.service.impl;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import com.saraconference.backend.service.BlobStorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
public class BlobStorageServiceImpl implements BlobStorageService {

    private static final Logger logger = LoggerFactory.getLogger(BlobStorageServiceImpl.class);

    @Value("${azure.storage.connection-string}")
    private String connectionString;

    @Value("${azure.storage.container-name}")
    private String containerName;

    private BlobContainerClient getContainerClient() {
        BlobServiceClient blobServiceClient = new BlobServiceClientBuilder()
                .connectionString(connectionString)
                .buildClient();
        return blobServiceClient.getBlobContainerClient(containerName);
    }

    @Override
    public String uploadFile(MultipartFile file) throws Exception {
        try {
            String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
            BlobContainerClient containerClient = getContainerClient();
            BlobClient blobClient = containerClient.getBlobClient(fileName);

            blobClient.upload(file.getInputStream(), file.getSize(), true);
            logger.info("File uploaded successfully: {}", fileName);

            return blobClient.getBlobUrl();
        } catch (IOException e) {
            logger.error("Error uploading file: {}", e.getMessage());
            throw new Exception("Error uploading file: " + e.getMessage(), e);
        }
    }

    @Override
    public byte[] downloadFile(String fileName) throws Exception {
        try {
            BlobContainerClient containerClient = getContainerClient();
            BlobClient blobClient = containerClient.getBlobClient(fileName);

            return blobClient.downloadContent().toBytes();
        } catch (Exception e) {
            logger.error("Error downloading file: {}", e.getMessage());
            throw new Exception("Error downloading file: " + e.getMessage(), e);
        }
    }

    @Override
    public void deleteFile(String fileName) throws Exception {
        try {
            BlobContainerClient containerClient = getContainerClient();
            BlobClient blobClient = containerClient.getBlobClient(fileName);

            blobClient.delete();
            logger.info("File deleted successfully: {}", fileName);
        } catch (Exception e) {
            logger.error("Error deleting file: {}", e.getMessage());
            throw new Exception("Error deleting file: " + e.getMessage(), e);
        }
    }
}
