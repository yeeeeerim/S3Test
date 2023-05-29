package com.example.reviews3.dto;

import com.example.reviews3.domain.Review;
import lombok.*;

import java.util.List;

@Getter
@Setter
public class ReviewDetailResponseDTO {
	private Long id;
	private String content;
	private Float rating;
	private List<String> image;

	public ReviewDetailResponseDTO(Review review){
		this.id=review.getId();
		this.content=review.getContent();
		this.rating= review.getRating();
	}

}
