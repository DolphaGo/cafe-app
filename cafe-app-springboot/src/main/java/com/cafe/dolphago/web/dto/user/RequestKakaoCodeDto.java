package com.cafe.dolphago.web.dto.user;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RequestKakaoCodeDto {
    String code;

    @Builder
    public RequestKakaoCodeDto(String code){
        this.code=code;
    }
}
