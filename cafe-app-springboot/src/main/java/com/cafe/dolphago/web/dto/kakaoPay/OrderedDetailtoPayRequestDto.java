package com.cafe.dolphago.web.dto.kakaoPay;


import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class OrderedDetailtoPayRequestDto {
    Long ccid;
    Long ooid;
    String orderuserid;
    List<OrderDetailtoPay> orderDetails;


}
