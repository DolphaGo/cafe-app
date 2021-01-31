package com.cafe.dolphago.domain.review;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
//
//    // cafe정보찾기 -> 여기에서 사장님 uid찾기위해서!
//    @Query("select c from Cafe c where c.ccid=:ccid")
//    List<Cafe> findByCcid(@Param("ccid") Long ccid);

}
