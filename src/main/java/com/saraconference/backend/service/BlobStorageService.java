package com.saraconference.backend.service;

import org.springframework.web.multipart.MultipartFile;

public interface BlobStorageService {
    /**
     * Upload file to Azure Blob Storage
     */
    String uploadFile(MultipartFile file) throws Exception;

    /**
     * Download file from Azure Blob Storage
     */
    byte[] downloadFile(String fileName) throws Exception;

    /**
     * Delete file from Azure Blob Storage
     */
    void deleteFile(String fileName) throws Exception;
}

