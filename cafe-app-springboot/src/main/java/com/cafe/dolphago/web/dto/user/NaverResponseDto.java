package com.cafe.dolphago.web.dto.user;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class NaverResponseDto {
    private String ncode;
    private String nstate;

    @Builder
    public NaverResponseDto(String ncode,String nstate){
        this.ncode=ncode;
        this.nstate=nstate;
    }
}
