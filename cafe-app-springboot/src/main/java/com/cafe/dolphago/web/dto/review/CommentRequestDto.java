package com.cafe.dolphago.web.dto.review;

import com.cafe.dolphago.domain.review.Comment;
import com.cafe.dolphago.domain.review.Review;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentRequestDto {

    private String comment;


    @Builder
    public CommentRequestDto(String comment) {
        this.comment=comment;
    }

    public Comment toEntity(Review review, String couid) {
        return Comment.builder()
                .comment(comment)
                .couid(couid)
                .review(review)
                .build();
    }
}
