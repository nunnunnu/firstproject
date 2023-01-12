package com.green.firstproject.repository.menu.basicmenu;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.green.firstproject.entity.menu.CategoryEntity;
import com.green.firstproject.entity.menu.basicmenu.BurgerInfoEntity;

import io.micrometer.common.lang.Nullable;

@Repository
public interface BurgerInfoRepository extends JpaRepository<BurgerInfoEntity, Long> {
     // List<BurgerInfoEntity> searchBurgerSeq(Long seq);

    Integer countByBiName(String biName);


     BurgerInfoEntity findByBiSeq(Long seq);


    List<BurgerInfoEntity> findByCate(CategoryEntity cate);
    
    @Query(value = "select * from burger_info where bi_cate = 2 or bi_cate = 3 or bi_cate = 4 or bi_cate = 5 order by bi_sales_rate desc limit 0,10", nativeQuery = true)
    List<BurgerInfoEntity> searchBurgerName();

    @Query(value = "select * from burger_info where date(bi_reg_dt) >= date(subdate(now(), INTERVAL 30 DAY)) and date(bi_reg_dt) <= date(now())", nativeQuery = true)
    List<BurgerInfoEntity> searchNewBurger();
    // @Query(
    //     value = "select new com.grenn.firstproject.vo.menu.HiaMenuListVO(bi.burger_info.biName, cate.category_info.cateName, bi.burger_info.biDetail, bi.burger_info.biFile) from BurgerInfoEntity bi left join CategoryEntity cate on bi.cate = cate.seq = :categoryInfoSeq"
    // )
    // List<HiaMenuListVO> getNewMenuList(@Param("categoryInfoSeq") Long categoryInfoSeq);

}
