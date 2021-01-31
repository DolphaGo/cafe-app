package com.cafe.dolphago.web.dto.cafe;

import com.cafe.dolphago.domain.cafe.Cafe;
import com.cafe.dolphago.domain.menu.Menu;
import com.cafe.dolphago.web.dto.menu.MenuResponseDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class CafeDetailForGUEST {
    private String cname;
    private String cloc;
    private String cpic;
    private String cdesc;
    private int coperation;
    private List<MenuResponseDto> menus=new ArrayList<>();
    @Builder
    public CafeDetailForGUEST(Cafe entity) {
        this.cname = entity.getCname();
        this.cloc = entity.getCloc();
        this.cpic = entity.getCpic();
        this.cdesc = entity.getCdesc();
        this.coperation=entity.getCoperation();
        for(Menu m:entity.getMenus()){
            menus.add(new MenuResponseDto(m));
        }
    }
}
