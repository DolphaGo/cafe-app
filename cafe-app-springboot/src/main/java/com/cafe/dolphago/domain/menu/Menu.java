package com.cafe.dolphago.domain.menu;

import com.cafe.dolphago.domain.BaseTimeEntity;
import com.cafe.dolphago.domain.cafe.Cafe;
import com.cafe.dolphago.domain.order.OrderDetail;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Menu extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mmid;

    @Column(nullable = false)
    private String mname;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "menu")
    @JsonBackReference
    private List<MenuSize> menuSizes;

    @Column(nullable = false)
    private String mpic;

    @Column
    private int isMain;

    // fk -> 1:N = cafe:menu
    @ManyToOne(optional = false)
    @JsonBackReference
    private Cafe cafemenu;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ordermenu")
    @JsonBackReference
    private List<OrderDetail> orderDetail;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "optionmenu")
    @JsonBackReference
    private List<MenuOption> optionList;

    @Builder
    public Menu(Cafe cafemenu,String mname,String mpic, int isMain) {
        this.cafemenu=cafemenu;
        this.mname=mname;
        this.mpic=mpic;
        this.isMain=isMain;
    }


    public void update(String mname,String mpic) {
        this.mname=mname;
        this.mpic=mpic;
    }

    public void toggleMainMenu(){
        this.isMain=1-this.isMain;
    }
}