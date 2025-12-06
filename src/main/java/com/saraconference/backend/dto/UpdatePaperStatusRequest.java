package com.saraconference.backend.dto;

import com.saraconference.backend.enums.PaperStatus;

public class UpdatePaperStatusRequest {
    private String paperId;
    private PaperStatus status;

    public String getPaperId() {
        return paperId;
    }

    public void setPaperId(String paperId) {
        this.paperId = paperId;
    }

    public PaperStatus getStatus() {
        return status;
    }

    public void setStatus(PaperStatus status) {
        this.status = status;
    }
}
