package com.cafe.dolphago.web.dto.cafe;


import com.cafe.dolphago.domain.cafe.Cafe;

public class CafeResponseDto {

    private Long ccid;
    private String cname;
    private String cloc;
    private String cpic;
    private String cdesc;
    private int coperation;

    public CafeResponseDto(Cafe entity) {
        this.ccid = entity.getCcid();
        this.cname = entity.getCname();
        this.cloc = entity.getCloc();
        this.cpic = entity.getCpic();
        this.cdesc = entity.getCdesc();
        this.coperation=entity.getCoperation();
    }
}