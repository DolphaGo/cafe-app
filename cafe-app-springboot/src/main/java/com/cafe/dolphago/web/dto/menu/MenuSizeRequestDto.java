package com.cafe.dolphago.web.dto.menu;


import com.cafe.dolphago.domain.menu.Menu;
import com.cafe.dolphago.domain.menu.MenuSize;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MenuSizeRequestDto {
    private String msname;
    private int msprice;

    public MenuSizeRequestDto(String msname,int msprice){
        this.msname=msname;
        this.msprice=msprice;
    }

    public MenuSize toEntity(Menu menu){
        return MenuSize.builder()
                .msname(msname)
                .msprice(msprice)
                .menu(menu)
                .build();
    }
}
