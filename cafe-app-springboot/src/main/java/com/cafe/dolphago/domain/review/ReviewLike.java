package com.cafe.dolphago.domain.review;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class ReviewLike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rvlid;

    @ManyToOne(cascade = CascadeType.ALL)
    @JsonBackReference
    private Review reviewlike;

    @Column
    private String uid;

    @Builder
    public ReviewLike(Review reviewlike, String uid) {
        this.reviewlike=reviewlike;
        this.uid=uid;
    }

}
