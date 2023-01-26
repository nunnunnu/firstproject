package com.green.firstproject.repository.menu.basicmenu;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.green.firstproject.entity.menu.CategoryEntity;
import com.green.firstproject.entity.menu.basicmenu.DrinkInfoEntity;
import com.green.firstproject.vo.menu.DrinkCateVO;

@Repository
public interface DrinkInfoRepository extends JpaRepository<DrinkInfoEntity, Long> {
    List<DrinkInfoEntity> findByCate(CategoryEntity cate);
    public Integer countByDiName(String diName);
    DrinkInfoEntity findByDiSeq(Long seq);

    @Query(value = "select di.di_seq as seq, di.di_name as name, di.di_detail as detail, di.di_uri as uri, "
                +"mi.count, mi.price, if(ds.ds_stock=0,'true', 'false') as soldout "
                +"from drink_info di "
                +"join (select * from drink_stock where ds_si_seq=:store) ds on di.di_seq =ds.ds_di_seq "
                +"join (select menu_di_seq, count(menu_di_seq) as 'count', min(menu_price) as 'price' from menu_info where menu_bi_seq is null group by menu_di_seq) mi "
                +" on mi.menu_di_seq =di.di_seq "
                +"where di.di_cate =:cate"
            , nativeQuery = true)
    List<DrinkCateVO> searchDrink(@Param("cate") Long cate, @Param("store") Long store);
}
