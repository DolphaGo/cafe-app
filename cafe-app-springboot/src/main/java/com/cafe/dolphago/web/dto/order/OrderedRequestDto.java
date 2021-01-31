package com.cafe.dolphago.web.dto.order;

import com.cafe.dolphago.domain.order.Ordered;
import com.cafe.dolphago.domain.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OrderedRequestDto {
    public Ordered toEntity(User user) {
        return Ordered.builder()
                .orderuser(user)
                .build();
    }
}
