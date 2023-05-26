package com.example.reviews3.service;

import com.example.reviews3.domain.Image;
import com.example.reviews3.domain.Review;
import com.example.reviews3.dto.ReviewRequestDTO;
import com.example.reviews3.repository.ImageRepository;
import com.example.reviews3.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
