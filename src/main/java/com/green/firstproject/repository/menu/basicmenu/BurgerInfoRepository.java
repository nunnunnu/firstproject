package com.green.firstproject.repository.menu.basicmenu;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.green.firstproject.entity.menu.CategoryEntity;
import com.green.firstproject.entity.menu.basicmenu.BurgerInfoEntity;
import com.green.firstproject.vo.menu.BurgerCateVo;

@Repository
public interface BurgerInfoRepository extends JpaRepository<BurgerInfoEntity, Long> {
     // List<BurgerInfoEntity> searchBurgerSeq(Long seq);

    Integer countByBiName(String biName);


     BurgerInfoEntity findByBiSeq(Long seq);


    List<BurgerInfoEntity> findByCate(CategoryEntity cate);

    // List<BurgerInfoEntity> findTop10ByOrderByBiSalesRateDesc(CategoryEntity cate);
    
//    @Query("select bi.biSeq , bi.biName , bi.biRegDt , bi.biSalesRate , bi.biDetail, bi.biFile , bi.biUri , RANK() OVER (ORDER BY biSalesRate desc) from BurgerInfoEntity bi where bi.cate=:cate")
//     List<Object[]> searchBurgerName(@Param("cate") CategoryEntity cate);

    @Query(value = "select c.bi_seq , c.bi_name, c.bi_reg_dt, c.bi_sales_rate , c.bi_detail , c.bi_file , c.bi_uri, b.ranking from burger_info c join(select RANK() OVER (ORDER BY a.bi_sales_rate desc) as ranking, a.bi_seq from burger_info a) b on c.bi_seq=b.bi_seq where bi_cate=:cate", nativeQuery = true)
    List<Object[]> searchBurgerName(@Param("cate") Long cate);

    List<BurgerInfoEntity> findAllByOrderByBiSalesRateDesc();
    
    // @Query(
    //     value = "select new com.grenn.firstproject.vo.menu.HiaMenuListVO(bi.burger_info.biName, cate.category_info.cateName, bi.burger_info.biDetail, bi.burger_info.biFile) from BurgerInfoEntity bi left join CategoryEntity cate on bi.cate = cate.seq = :categoryInfoSeq"
    // )
    // List<HiaMenuListVO> getNewMenuList(@Param("categoryInfoSeq") Long categoryInfoSeq);

}
