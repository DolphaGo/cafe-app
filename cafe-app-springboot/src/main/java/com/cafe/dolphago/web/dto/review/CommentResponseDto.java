package com.cafe.dolphago.web.dto.review;

import com.cafe.dolphago.domain.review.Comment;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class CommentResponseDto {
    private Long coid;
    private String couid;
    private String comment;
    private LocalDateTime createdDate;

    @Builder
    public CommentResponseDto(Comment commentEntity) {
        coid=commentEntity.getCoid();
        couid="사장님";
        comment=commentEntity.getComment();
        createdDate=commentEntity.getCreatedDate();
    }

}
