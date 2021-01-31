package com.cafe.dolphago.web.dto.menu;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Getter
public class MenuUpdateRequestDto {
    private String mname;
    private String mpic;
    private List<MenuSizeUpdateDto> menuSizeUpdateDtoList;
    private List<MenuOptionUpdateRequestDto> menuOptionUpdateRequestDtoList;

    @Builder
    public MenuUpdateRequestDto(String mname,String mpic,List<MenuSizeUpdateDto> menuSizeUpdateDtoList,List<MenuOptionUpdateRequestDto> menuOptionUpdateRequestDtoList) {
        this.mname = mname;
        this.mpic = mpic;
        this.menuSizeUpdateDtoList=menuSizeUpdateDtoList;
        this.menuOptionUpdateRequestDtoList=menuOptionUpdateRequestDtoList;
    }
}