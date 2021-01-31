package com.cafe.dolphago.domain.cafe;

import com.cafe.dolphago.domain.BaseTimeEntity;
import com.cafe.dolphago.domain.menu.Menu;
import com.cafe.dolphago.domain.review.Review;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Cafe extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ccid;

    @Column
    private String uid;

    @Column(nullable = false)
    private String cname;

    @Column(nullable = false)
    private String cloc;

    @Column
    private String cpic;

    @Column(nullable = false)
    private String cdesc;

    @Column(nullable = false)
    private int coperation;  // 1:운영중, 0:끝

    @Column
    private int y;//위도

    @Column
    private int x;//경도

    // fk -> 1:N = cafe:menu
    @OneToMany(cascade=CascadeType.ALL, mappedBy = "cafemenu")
    @JsonManagedReference
    private List<Menu> menus=new ArrayList<>();

    // 1:N = cafe:review
    @OneToMany(cascade=CascadeType.ALL, mappedBy = "cafereview")
    @JsonManagedReference
    private List<Review> reviews=new ArrayList<>();


    public Cafe(Long ccid){
        this.ccid=ccid;
    }

    @Builder
    public Cafe(String uid,String cname, String cloc, String cpic, String cdesc,int coperation) {
        this.uid=uid;
        this.cname = cname;
        this.cloc = cloc;
        this.cpic = cpic;
        this.cdesc=cdesc;
        this.coperation=coperation;
    }

    public void CafeUpdate(String cname,String cloc,String cpic,String cdesc) {
        this.cname=cname;
        this.cloc=cloc;
        this.cpic=cpic;
        this.cdesc=cdesc;
    }
    public void setCoperation(int coperation) {this.coperation=coperation;}

    private void setyx(int y,int x){
        this.y=y;
        this.x=x;
    }
}