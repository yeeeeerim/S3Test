package com.example.reviews3.controller;

import com.example.reviews3.dto.ReviewDetailResponseDTO;
import com.example.reviews3.dto.ReviewListResponseDTO;
import com.example.reviews3.dto.ReviewRequestDTO;
import com.example.reviews3.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ReviewController {
	private final ReviewService reviewService;

	@PostMapping("/review")
	public void uploadReview(@RequestParam("content")String content,
							 @RequestParam("rating")Float rating,
							 @RequestParam(value = "imageFiles", required = false) List<MultipartFile> imageFiles){
		ReviewRequestDTO reviewRequestDTO = new ReviewRequestDTO();
		reviewRequestDTO.setContent(content);
		reviewRequestDTO.setRating(rating);
		reviewRequestDTO.setImage(imageFiles);
		reviewService.uploadReview(reviewRequestDTO);
	}
	@GetMapping("/review")
	public List<ReviewListResponseDTO> getAllReview(){
		return reviewService.getAllReview();
	}

	@GetMapping("/review/{id}")
	public ReviewDetailResponseDTO getDetailReview(@PathVariable("id")Long id){
		return reviewService.getReview(id);
	}
}
