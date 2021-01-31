package com.cafe.dolphago.web.dto.menu;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MenuSizeUpdateDto {
    Long msid;
    String msname;
    int msprice;

    @Builder
    public MenuSizeUpdateDto(Long msid,String msname,int msprice){
        this.msid=msid;
        this.msname=msname;
        this.msprice=msprice;
    }
}
