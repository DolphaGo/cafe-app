package com.cafe.dolphago.web.dto.kakaoPay;


import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OrderDetailtoPay {
    Long mmid;
    int quantity;
    int total;

    public OrderDetailtoPay(Long mmid,int quantity,int total){
        this.mmid=mmid;
        this.quantity=quantity;
        this.total=total;
    }

}
