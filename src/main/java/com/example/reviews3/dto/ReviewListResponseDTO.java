package com.example.reviews3.dto;

import com.example.reviews3.domain.Review;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReviewListResponseDTO {
	private Long id;
	private String content;
	private String imgUrl;

	private Float rating;

	public ReviewListResponseDTO (Review review){
		this.id=review.getId();
		this.content=review.getContent();
		this.rating=review.getRating();
	}

}
