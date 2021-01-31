package com.cafe.dolphago.web.dto.order;

import com.cafe.dolphago.domain.menu.Menu;
import com.cafe.dolphago.domain.order.OrderDetail;
import com.cafe.dolphago.domain.order.Ordered;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class OrderDetailRequestDto {

    private Long mmid; //메뉴가져오고
    private int msid; //사이즈 번호(메뉴 하나당 사이즈는 1개)
    private List<Integer> opid; //옵션번호 가져오고(여러개 가능)
    private int pay; //가격 가져와(프론트에서 옵션,사이즈,수량 계산해서 보낸 가격)

    @Builder
    public OrderDetailRequestDto(Long mmid,int msid,List<Integer> opid,int pay) {
        this.mmid=mmid;
        this.msid=msid;
        this.opid=opid;
        this.pay=pay;
    }

    public OrderDetail toEntity(Menu ordermenu, Ordered ordered) {
        return OrderDetail.builder()
                .ordered(ordered)
                .ordermenu(ordermenu)
                .build();
    }
}
