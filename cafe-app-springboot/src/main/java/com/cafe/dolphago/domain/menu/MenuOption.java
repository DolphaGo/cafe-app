package com.cafe.dolphago.domain.menu;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class MenuOption {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long opid;

    @Column
    String OptionName;

    @Column
    int OptionPrice;

    @ManyToOne
    @JsonBackReference
    Menu optionmenu;

    @Builder
    public MenuOption(String OptionName,int OptionPrice,Menu optionmenu){
        this.OptionName=OptionName;
        this.OptionPrice=OptionPrice;
        this.optionmenu=optionmenu;
    }

    public void update(String OptionName,int OptionPrice){
        this.OptionName=OptionName;
        this.OptionPrice=OptionPrice;
    }

}
