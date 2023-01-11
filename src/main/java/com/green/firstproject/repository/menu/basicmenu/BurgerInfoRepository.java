package com.green.firstproject.repository.menu.basicmenu;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.green.firstproject.entity.menu.CategoryEntity;
import com.green.firstproject.entity.menu.basicmenu.BurgerInfoEntity;

@Repository
public interface BurgerInfoRepository extends JpaRepository<BurgerInfoEntity, Long> {
     // List<BurgerInfoEntity> searchBurgerSeq(Long seq);

    Integer countByBiName(String biName);


     BurgerInfoEntity findByBiSeq(Long seq);


    List<BurgerInfoEntity> findByCate(CategoryEntity cate);

    // @Query(
    //     value = "select new com.grenn.firstproject.vo.menu.HiaMenuListVO(bi.burger_info.biName, cate.category_info.cateName, bi.burger_info.biDetail, bi.burger_info.biFile) from BurgerInfoEntity bi left join CategoryEntity cate on bi.cate = cate.seq = :categoryInfoSeq"
    // )
    // List<HiaMenuListVO> getNewMenuList(@Param("categoryInfoSeq") Long categoryInfoSeq);
}
