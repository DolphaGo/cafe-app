package com.cafe.dolphago.domain.user;

import com.cafe.dolphago.domain.BaseTimeEntity;
import com.cafe.dolphago.domain.order.Ordered;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class User extends BaseTimeEntity {

    @Id // pk
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 오토 인크리먼트
    private Long uuid; // 1, 2, 3, ...

    @Column(nullable = false)
    private String uid;

    @Column
    private String upass;

    @Column(nullable = false)
    private String unickname;

    @Column(nullable = false)
    private String uemail;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Column
    private String upic;

    // fk -> 1:N = user:order -> if role=1(손님)
    //user-order의 관계는 user가 연관관계의 대상.
    @OneToMany(cascade=CascadeType.ALL, mappedBy = "orderuser")
    @JsonManagedReference
    private List<Ordered> ordered;



    public User(Long uuid) {
        this.uuid=uuid;
    }

    @Builder
    public User(String uid, String upass, String uemail, String unickname, Role role, String upic) {
        this.uid = uid;
        this.upass = upass;
        this.uemail = uemail;
        this.unickname = unickname;
        this.role = role;
        if(upic!=null) this.upic=upic;
        else this.upic="./src/userimg/user.jpg";
    }

    // 수정
    public void update(String upass,String unickname,String upic) {
        this.upass = upass;
        this.unickname = unickname;
        this.upic=upic;
    }
}
