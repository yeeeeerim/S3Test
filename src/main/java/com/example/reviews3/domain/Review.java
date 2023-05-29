package com.example.reviews3.domain;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Review {
	@Id
	@GeneratedValue
	private Long id;
	private String content;
	private Float rating;
	@OneToMany(mappedBy = "review" , cascade = CascadeType.ALL)
	private List<Image> reviewImage;

}
