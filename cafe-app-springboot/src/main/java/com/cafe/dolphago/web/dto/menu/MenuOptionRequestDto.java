package com.cafe.dolphago.web.dto.menu;

import com.cafe.dolphago.domain.menu.Menu;
import com.cafe.dolphago.domain.menu.MenuOption;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MenuOptionRequestDto {
    private String optionName;
    private int optionPrice;

    @Builder
    public MenuOptionRequestDto(String optionName, int optionPrice) {
        this.optionName=optionName;
        this.optionPrice=optionPrice;
    }

    public MenuOption toEntity(Menu menu){
        return MenuOption.builder()
                .OptionName(optionName)
                .OptionPrice(optionPrice)
                .optionmenu(menu)
                .build();
    }

}
