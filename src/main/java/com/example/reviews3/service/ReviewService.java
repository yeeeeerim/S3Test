package com.example.reviews3.service;

import com.example.reviews3.domain.Image;
import com.example.reviews3.domain.Review;
import com.example.reviews3.dto.ReviewDetailResponseDTO;
import com.example.reviews3.dto.ReviewListResponseDTO;
import com.example.reviews3.dto.ReviewRequestDTO;
import com.example.reviews3.repository.ImageRepository;
import com.example.reviews3.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {
	private final ReviewRepository reviewRepository;
	private final ImageRepository imageRepository;
	private final S3Service s3Service;

	@Transactional
	public void uploadReview(ReviewRequestDTO reviewRequestDTO) {
		Review review = reviewRequestDTO.toEntity();
		reviewRepository.save(review);
		if (reviewRequestDTO.getImage() != null && !reviewRequestDTO.getImage().isEmpty()) {
			// 이미지가 존재하는 경우
			List<String> imgList = s3Service.uploadFile(reviewRequestDTO.getImage());
			for (String imgUrl : imgList) {
				Image image = Image.builder().imgUrl(imgUrl).review(review).build();
				imageRepository.save(image);
			}
		}
	}

	public List<ReviewListResponseDTO> getAllReview(){
		List<Review> reviewsWithImages = reviewRepository.getAllReviewsWithImages();
		List<ReviewListResponseDTO> reviewListResponseDTOS = new ArrayList<>();
		for (Review review : reviewsWithImages) {
			List<Image> images = review.getReviewImage();
			ReviewListResponseDTO reviewResponseDTO = new ReviewListResponseDTO(review);
			if (images != null && !images.isEmpty()) { //이미지가 있다면
				reviewResponseDTO.setImgUrl(review.getReviewImage().get(0).getImgUrl());
			}
			reviewListResponseDTOS.add(reviewResponseDTO);
		}
		return reviewListResponseDTOS;
	}

	public ReviewDetailResponseDTO getReview(Long reviewId){
		Review review = reviewRepository.getReviewWithImages(reviewId);
		ReviewDetailResponseDTO reviewDetailResponseDTO = new ReviewDetailResponseDTO(review);

		List<Image> images = review.getReviewImage();
		if (images != null && !images.isEmpty()) {
			List<String> reviewImages = new ArrayList<>();
			for (Image image : images) {
				reviewImages.add(image.getImgUrl());
			}
			reviewDetailResponseDTO.setImage(reviewImages);
		}
		return reviewDetailResponseDTO;
	}
}
