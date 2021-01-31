package com.cafe.dolphago.web.dto.review;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ReviewUpdateRequestDto {
    private String rvcontent;
    private String rvpic;

    @Builder
    public ReviewUpdateRequestDto(String rvcontent,String rvpic) {
        this.rvcontent=rvcontent;
        this.rvpic=rvpic;
    }

}
