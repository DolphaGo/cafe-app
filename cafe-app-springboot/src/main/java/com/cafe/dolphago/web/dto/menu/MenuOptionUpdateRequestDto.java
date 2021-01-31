package com.cafe.dolphago.web.dto.menu;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MenuOptionUpdateRequestDto {
    Long opid;
    String optionName;
    int optionPrice;

    @Builder
    MenuOptionUpdateRequestDto(Long opid,String optionName,int optionPrice){
        this.opid=opid;
        this.optionName=optionName;
        this.optionPrice=optionPrice;
    }
}
