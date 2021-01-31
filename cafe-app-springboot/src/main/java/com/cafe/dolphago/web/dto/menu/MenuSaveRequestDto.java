package com.cafe.dolphago.web.dto.menu;

import com.cafe.dolphago.domain.cafe.Cafe;
import com.cafe.dolphago.domain.menu.Menu;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class MenuSaveRequestDto {

    private String mname;
    private List<MenuSizeRequestDto> menuSizeRequestDtos;
    private String mpic;

    @Builder
    public MenuSaveRequestDto(String mname,List<MenuSizeRequestDto> menuSizeRequestDtos,String mpic) {
        this.mname = mname;
        this.menuSizeRequestDtos = menuSizeRequestDtos;
        this.mpic = mpic;
    }

    public Menu toEntity(Cafe cafe) {
        return Menu.builder()
                .cafemenu(cafe) //조인용
                .mname(mname)
                .mpic(mpic)
                .isMain(0)
                .build();
    }

}