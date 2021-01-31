package com.cafe.dolphago.web.dto.order;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OrderStatusRequestDto {
    private Long ooid;
    private int ostatus;

    @Builder
    public OrderStatusRequestDto(Long ooid,int ostatus) {
        this.ooid=ooid;
        this.ostatus=ostatus;
    }
}
