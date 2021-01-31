package com.cafe.dolphago.web.dto.cafe;

import com.cafe.dolphago.domain.cafe.Cafe;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CafeSaveRequestDto {
    private String cname;
    private String cloc;
    private String cpic;
    private String cdesc;

    @Builder
    public CafeSaveRequestDto(String cname, String cloc,String cpic,String cdesc) {
        this.cname = cname;
        this.cloc = cloc;
        this.cpic = cpic;
        this.cdesc=cdesc;
    }

    public Cafe toEntity(String uid) {
        return Cafe.builder()
                .cname(cname)
                .cloc(cloc)
                .cpic(cpic)
                .cdesc(cdesc)
                .uid(uid)
                .build();
    }
}