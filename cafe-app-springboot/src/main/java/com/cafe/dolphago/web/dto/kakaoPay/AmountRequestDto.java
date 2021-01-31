package com.cafe.dolphago.web.dto.kakaoPay;
import lombok.Data;

@Data
public class AmountRequestDto {
    private Integer total, tax_free, vat, point, discount;
}

