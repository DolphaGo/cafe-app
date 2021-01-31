package com.cafe.dolphago.domain.order;

import com.cafe.dolphago.domain.BaseTimeEntity;
import com.cafe.dolphago.domain.menu.Menu;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class OrderDetail extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long odid;

    // fk -> 1:1 = orderDetail:menu
    //orderdetail-menu 의 관계는 orderdetail이 연관관계의 주인
    @ManyToOne
    @JsonBackReference
    private Menu ordermenu;

    // fk -> 1:N = order:orderDetail
    //order-orderdetail의 연관관계는 orderdetail이 연관관계의 주인임
    @ManyToOne(optional = false)
    @JsonBackReference
    private Ordered ordered; //오더디테일이 오더에게는 연관관계의 주인

    //하나의 orderDetail에는 여러개의 옵션이 들어올 수 있다.
    //상속을 하려고 했는데(mapped by)
    /*내가 생각하기에는
    orderdetail 안에는 여러 옵션들이 들어올텐데
    이게 과연 상속인가? 싶다.
    그저 들어오는 옵션들은 다양하고
    이 옵션들에 대한 정보만 있으면 되기때문에
    상속이 아니라 나는 join 관계라는 생각이 든다.
    지금껏 상속이라는 것들은 무조건 포함을 해야 하는 관계
    즉 orderdetail의 상위에는 ordered라는 정보가 무조건 있어야 했고
    menu의 상위에는 cafe라는 정보가 무조건 있어야 했다.
    그리고 option의 상위에는 menu라는 정보가 무조건 있어야 했다.
    같은 의미로 orderdetail의 상위에는 option이라는 정보가 무조건 있어야 하나?
    선택에 따라 달라지는 문제라고 생각한다. 어딘가에 꼭 종속되어야 하는 녀석이 아니다.
    그러므로 나는 orderdetail과 option을 상속이 아닌 join관계로 설계했다.
    허나 여러개가 들어올 수가 있기 때문에, 이는 dto에서 list로 받아서
    option을 읽어오는 식으로 설계를 해야한다. 나중에 결제건에서
    orderdetail에 대한 정보를 원한다면, orderDetailDto에서 읽어온 옵션을 바탕으로
    보여주기만 하면 된다고 생각한다. 굳이 상속은 안해도 될 것 같다.
    같은 맥락으로 그럼 orderdetail과 menu는 왜 상속하였느냐고 반문할 수 있다.
    orderdetail에서는 무조건 메뉴를 선택하게 될 것이고 이 메뉴를 통해서 카페로 갈 수도 있다.
    즉 orderdetail에서는 메뉴선택이 필수적이다.
    그런데 그 메뉴를 선택하고 나서 옵션은 필수인가? 라고 묻는다면
    선택할 수도, 안할 수도 있다. 따라서 옵션은 필수가 아니다. 그러므로 조인으로 처리하겠다는 뜻이다.
*/
    @Builder
    public OrderDetail(Menu ordermenu,Ordered ordered) {
        this.ordermenu=ordermenu;
        this.ordered = ordered;
    }
}
