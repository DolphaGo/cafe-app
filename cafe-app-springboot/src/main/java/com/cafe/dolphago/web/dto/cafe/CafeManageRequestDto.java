package com.cafe.dolphago.web.dto.cafe;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class CafeManageRequestDto {
    private Long ccid;
    private int cstatus;

    @Builder
    public CafeManageRequestDto(Long ccid, int cstatus){
        this.ccid=ccid;
        this.cstatus=cstatus;
    }
}