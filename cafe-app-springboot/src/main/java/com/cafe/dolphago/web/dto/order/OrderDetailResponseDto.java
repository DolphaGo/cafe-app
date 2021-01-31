package com.cafe.dolphago.web.dto.order;

import com.cafe.dolphago.domain.menu.Menu;
import com.cafe.dolphago.domain.order.OrderDetail;
import com.cafe.dolphago.domain.order.Ordered;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OrderDetailResponseDto {
    private Ordered ordered;
    private Menu ordermenu;

    @Builder
    public OrderDetailResponseDto(OrderDetail entity) {
        this.ordered=entity.getOrdered();
        this.ordermenu=entity.getOrdermenu();
    }
}
