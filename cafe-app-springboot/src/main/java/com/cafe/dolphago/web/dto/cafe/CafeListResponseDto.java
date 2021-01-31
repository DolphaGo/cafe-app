package com.cafe.dolphago.web.dto.cafe;

import com.cafe.dolphago.domain.cafe.Cafe;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CafeListResponseDto {
    private Long ccid;
    private String cname;
    private String cloc;
    private String cpic;
    private String cdesc;
    private int coperation;
    private LocalDateTime createdDate;

    public CafeListResponseDto(Cafe entity) {
        this.ccid=entity.getCcid();
        this.cname=entity.getCname();
        this.cloc=entity.getCloc();
        this.cpic=entity.getCpic();
        this.cdesc=entity.getCdesc();
        this.coperation=entity.getCoperation();
        this.createdDate=entity.getCreatedDate();
    }
}