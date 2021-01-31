package com.cafe.dolphago.domain.menu;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class MenuSize {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long msid;

    @Column(nullable = false)
    private String msname;

    @Column(nullable = false)
    private int msprice;

    @ManyToOne
    @JsonBackReference
    Menu menu;

    @Builder
    public MenuSize(String msname,int msprice,Menu menu){
        this.msname=msname;
        this.msprice=msprice;
        this.menu=menu;
    }

    public void update(String msname,int msprice){
        this.msname=msname;
        this.msprice=msprice;
    }

}
