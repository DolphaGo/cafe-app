package com.cafe.dolphago.web.dto.review;

import com.cafe.dolphago.domain.review.Comment;
import com.cafe.dolphago.domain.review.Review;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class ReviewResponseDto {
    private Long rvid;
    private String rvuid;
    private String rvcontent;
    private String rvpic;
    private int rvlike;
    private LocalDateTime createdDate;
    private List<CommentResponseDto> commentResponseDtoList;

    public ReviewResponseDto(Review review) {
        rvid=review.getRvid();
        rvuid=review.getRvuid();
        rvcontent=review.getRvcontent();
        rvpic=review.getRvpic();
        rvlike=review.getRvlike();
        createdDate=review.getCreatedDate();
        commentResponseDtoList=new ArrayList<>();
        for(Comment cr:review.getCommentList()) {
            commentResponseDtoList.add(new CommentResponseDto(cr));
        }

    }
}
