package com.cafe.dolphago.web.dto.cafe;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CafeUpdateRequestDto {
    private String uid; //이건 프론트쪽에서 그대로 넘겨줘야함.
    private String cname;
    private String cloc;
    private String cpic;
    private String cdesc;

    @Builder
    public CafeUpdateRequestDto(String uid,String cname,String cloc,String cpic,String cdesc){
        this.uid=uid;
        this.cname=cname;
        this.cloc=cloc;
        this.cpic=cpic;
        this.cdesc=cdesc;
    }
}