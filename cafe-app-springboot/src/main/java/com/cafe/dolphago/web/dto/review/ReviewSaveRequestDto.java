package com.cafe.dolphago.web.dto.review;

import com.cafe.dolphago.domain.cafe.Cafe;
import com.cafe.dolphago.domain.review.Review;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ReviewSaveRequestDto {
    private String rvcontent;
    private String rvpic;

    @Builder
    public ReviewSaveRequestDto(String rvcontent, String rvpic) {
        this.rvcontent=rvcontent;
        this.rvpic=rvpic;
    }


    public Review toEntity(Cafe cafereview, String rvuid) {
        return Review.builder()
                .rvcontent(rvcontent)
                .rvpic(rvpic)
                .rvlike(0)
                .rvuid(rvuid)
                .cafereview(cafereview)
                .build();
    }
}
