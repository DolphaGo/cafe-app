package com.cafe.dolphago.service;

import com.cafe.dolphago.domain.order.OrderDetail;
import com.cafe.dolphago.domain.order.OrderDetailRepository;
import com.cafe.dolphago.web.dto.order.OrderDetailResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderDetailService {

    private final OrderDetailRepository orderDetailRepository;

    // 전체 선택 -> order하나에 대한 orderDetail 보여주기
    @Transactional
    public List<OrderDetailResponseDto> findAllDesc(Long ooid){
        return orderDetailRepository.findAllByOoid(ooid).stream()
                .map(OrderDetailResponseDto::new)
                .collect(Collectors.toList());
    }

    //들어오는 input들(주문번호,메뉴) 저장하기
    @Transactional
    public void save(OrderDetail orderDetail){
        orderDetailRepository.save(orderDetail);
    }
}
