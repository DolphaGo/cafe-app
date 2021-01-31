package com.cafe.dolphago.web.dto.order;

import com.cafe.dolphago.domain.order.Ordered;
import com.cafe.dolphago.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OrderedResponseDto {
    private User orderuser;

    @Builder
    public OrderedResponseDto(Ordered entity){
        this.orderuser=entity.getOrderuser();
    }
}