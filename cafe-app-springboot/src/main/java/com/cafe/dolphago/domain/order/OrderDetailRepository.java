package com.cafe.dolphago.domain.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {
    // order하나에 여러개의 orderDetail가지고오기
    @Query("select od from OrderDetail od where od.ordered.ooid=:ooid")
    List<OrderDetail> findAllByOoid(@Param("ooid") Long ooid);
}
