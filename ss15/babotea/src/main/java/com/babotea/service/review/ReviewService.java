package com.babotea.service.review;

import com.babotea.model.dto.request.ReviewDTO;
import com.babotea.model.dto.response.ReviewResponse;

import java.util.List;

public interface ReviewService {
    void createReview(ReviewDTO dto, Long userId);
    List<ReviewResponse> getReviewsByProductId(Long productId);
}
