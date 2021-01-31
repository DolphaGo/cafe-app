package com.cafe.dolphago.domain.menu;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MenuRepository extends JpaRepository<Menu, Long> {

    @Modifying
    @Query("update Menu m set m.isMain=:toggleValue where m.mmid=:mmid")
    void toggleMain(@Param("mmid") Long mmid, @Param("toggleValue") int toggleValue);

    @Query("select m from Menu m where m.mmid=:mmid")
    Menu findByMmid(@Param("mmid") Long mmid);

    @Query("select m from Menu m where m.cafemenu.ccid=:ccid")
    List<Menu> findAllByCcid(@Param("ccid") Long ccid);
    //이 카페에 대한 모든 메뉴들을 가져와라!
}