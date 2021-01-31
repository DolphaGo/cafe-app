package com.cafe.dolphago.domain.cafe;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CafeRepository extends JpaRepository<Cafe,Long> {

    // 손님 카페 리스트 -> 손님페이지: 운영중인것 부터 보여주기
    @Query("select c from Cafe c order by c.coperation desc")
    List<Cafe> findAllByDesc();

    @Query("select c from Cafe c where c.ccid=:ccid")
    Cafe findByCcid(@Param("ccid") Long ccid);


    // coperation변경
    @Modifying
    @Query("update Cafe c set c.coperation=:coperation")
    void setOperation(@Param("coperation") int coperation);

    @Query("select c.ccid from Cafe c where c.uid=:uid")
    Long findCcidByUid(@Param("uid") String uid);
}