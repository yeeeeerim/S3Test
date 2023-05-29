package com.example.reviews3.dto;

import com.example.reviews3.domain.Review;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReviewRequestDTO {
	private String content;
	private Float rating;
	private List<MultipartFile> image;

	public Review toEntity(){
		return Review.builder()
				.content(content)
				.rating(rating)
				.build();
	}
}
