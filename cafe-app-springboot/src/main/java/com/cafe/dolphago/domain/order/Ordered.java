package com.cafe.dolphago.domain.order;

import com.cafe.dolphago.domain.BaseTimeEntity;
import com.cafe.dolphago.domain.user.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Ordered extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ooid;

    @Column(nullable = true)
    private int ostatus;  // 주문상태: -1=취소, 0=대기, 1=사장님확인, 2=완료

    // fk -> 1:N = order:orderDetail (받는 쪽 표시)
    //ordered-orderdetail 관계에서 ordered가 연관관계의 대상이므로 mappedby로 표시해주자.
    @OneToMany(cascade=CascadeType.ALL, mappedBy = "ordered")
    @JsonManagedReference
    private List<OrderDetail> orderDetails=new ArrayList<>();

    // fk -> 1:N = user:order -> if role=1(손님)
    //user-ordered의 관계는 ordered가 연관관계의 주인
    @ManyToOne(optional = false)
    @JsonBackReference
    private User orderuser;

    //Pay관련 정보는 추후에 추가 예정

    @Builder
    public Ordered(User orderuser){
        this.orderuser=orderuser;
    }
}
