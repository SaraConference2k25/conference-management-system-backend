package com.saraconference.backend.dto;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class SaveReviewCommentsRequest {
    String paperId;
    String evaluatorComments;
    String toggleStatus;
}
