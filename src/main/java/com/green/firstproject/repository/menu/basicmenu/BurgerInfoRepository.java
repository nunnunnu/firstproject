package com.green.firstproject.repository.menu.basicmenu;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.green.firstproject.entity.menu.CategoryEntity;
import com.green.firstproject.entity.menu.basicmenu.BurgerInfoEntity;
import com.green.firstproject.vo.menu.BurgerCateVo;

import io.micrometer.common.lang.Nullable;

@Repository
public interface BurgerInfoRepository extends JpaRepository<BurgerInfoEntity, Long> {
     // List<BurgerInfoEntity> searchBurgerSeq(Long seq);

    Integer countByBiName(String biName);


     BurgerInfoEntity findByBiSeq(Long seq);


    List<BurgerInfoEntity> findByCate(CategoryEntity cate);

    // List<BurgerInfoEntity> findTop10ByOrderByBiSalesRateDesc(CategoryEntity cate);
    
    // @Query(value = "select new com.green.firstproject.vo.menu.BurgerCateVo(b.biSeq,b.biName,b.biDetail,b.biFile,b.biUri,b.biRegDt,b.biSalesRate, RANK() OVER (ORDER BY biSalesRate desc)), from BurgerInfoEntity b", nativeQuery = true)
    // List<BurgerCateVo> searchBurgerName();

    List<BurgerInfoEntity> findAllByOrderByBiSalesRateDesc();
    
    // @Query(
    //     value = "select new com.grenn.firstproject.vo.menu.HiaMenuListVO(bi.burger_info.biName, cate.category_info.cateName, bi.burger_info.biDetail, bi.burger_info.biFile) from BurgerInfoEntity bi left join CategoryEntity cate on bi.cate = cate.seq = :categoryInfoSeq"
    // )
    // List<HiaMenuListVO> getNewMenuList(@Param("categoryInfoSeq") Long categoryInfoSeq);

}
