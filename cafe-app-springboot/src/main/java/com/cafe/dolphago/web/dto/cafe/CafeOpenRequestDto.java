package com.cafe.dolphago.web.dto.cafe;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CafeOpenRequestDto {
    private Long ccid;
    private int coperation;

    @Builder
    public CafeOpenRequestDto(Long ccid, int coperation){
        this.ccid=ccid;
        this.coperation=coperation;
    }
}
