package com.green.firstproject.repository.menu.basicmenu;

import java.security.Timestamp;
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

    Integer countByBiName(String biName);


    BurgerInfoEntity findByBiSeq(Long seq);


    List<BurgerInfoEntity> findByCate(CategoryEntity cate);

    @Query(value = "select c.bi_seq as burgerSeq , c.bi_name as burgerName, c.bi_reg_dt as burgerRegDt, c.bi_detail as burgerDetail , c.bi_file as burgerFile, c.bi_uri as burgerUri, if(DATEDIFF(CURDATE( ),c.bi_reg_dt)<=30,'true','false') as burgerNew,if(ranking<=10,'true','false') as burgerBest, if(d.bs_stock=0, 'true','false') as burgerSoldout from burger_info c join(select RANK() OVER (ORDER BY a.bi_sales_rate desc) as ranking, a.bi_seq from burger_info a) b on c.bi_seq=b.bi_seq join burger_stock d on d.bs_bi_seq = c.bi_seq where bi_cate=:cate", nativeQuery = true)
    List<BurgerCateVo> searchBurger(@Param("cate") Long cate);

    List<BurgerInfoEntity> findAllByOrderByBiSalesRateDesc();
    
}
