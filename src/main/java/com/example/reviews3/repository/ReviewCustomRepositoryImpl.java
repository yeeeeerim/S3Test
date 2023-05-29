package com.example.reviews3.repository;

import com.example.reviews3.domain.QImage;
import com.example.reviews3.domain.QReview;
import com.example.reviews3.domain.Review;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewCustomRepositoryImpl implements ReviewCustomRepository{
	private final JPAQueryFactory queryFactory;

	@Override
	public List<Review> getAllReviewsWithImages() {
		QReview qReview = QReview.review;
		QImage qReviewImage = QImage.image;

		List<Review> reviews = queryFactory.selectDistinct(qReview)
				.from(qReview)
				.leftJoin(qReview.reviewImage, qReviewImage)
				.fetchJoin()
				.fetch();

		return reviews;
	}

	@Override
	public Review getReviewWithImages(Long reviewId) {
		QReview qReview = QReview.review;
		QImage qReviewImage = QImage.image;

		Review review = queryFactory.selectDistinct(qReview)
				.from(qReview)
				.leftJoin(qReview.reviewImage, qReviewImage)
				.fetchJoin()
				.where(qReview.id.eq(reviewId))
				.fetchOne();

		return review;
	}


}
