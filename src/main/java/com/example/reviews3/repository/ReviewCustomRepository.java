package com.example.reviews3.repository;

import com.example.reviews3.domain.Review;

import java.util.List;

public interface ReviewCustomRepository {
	List<Review> getAllReviewsWithImages();

	Review getReviewWithImages(Long id);
}
