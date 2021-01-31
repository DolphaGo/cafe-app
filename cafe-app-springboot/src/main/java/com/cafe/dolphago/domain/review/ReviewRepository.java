package com.cafe.dolphago.domain.review;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    // 카페별 찾기위함
    @Query("select r from Review r where r.cafereview.ccid=:ccid")
    List<Review> findByCcid(@Param("ccid") Long ccid);

    // uid로 찾기
    @Query("select r from Review r where r.rvid=:rvid")
    List<Review> findByRvid(@Param("rvid") Long rvid);


}
