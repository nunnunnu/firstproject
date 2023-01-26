package com.green.firstproject.repository.menu.basicmenu;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.green.firstproject.entity.menu.CategoryEntity;
import com.green.firstproject.entity.menu.basicmenu.SideInfoEntity;
import com.green.firstproject.vo.menu.SideCateVO;
@Repository
public interface SideInfoRepository extends JpaRepository<SideInfoEntity, Long> {
    List <SideInfoEntity> findByCate(CategoryEntity cate);
        public Integer countBySideName(String sideName);
        SideInfoEntity findBySideSeq(Long seq);
        SideInfoEntity findBySideUri(String uri);

    @Query(value = "select a.side_seq as Seq, a.side_name as Name , a.side_detail as detail , "
                    +"a.side_uri as uri , c.count, c.price, if(b.ss_stock=0,'true', 'false') as soldout "
                +"from side_info a "
                +"join (select * from side_stock where ss_si_seq=:store) b on a.side_seq =b.ss_side_seq "
                +"join (select menu_side_seq, count(menu_side_seq) as 'count', min(menu_price) as 'price' "
                +"from menu_info where menu_bi_seq is null group by menu_side_seq) c on c.menu_side_seq = a.side_seq "
                +"where a.side_cate =:cate"
        , nativeQuery = true)
    List<SideCateVO> searchSide(@Param("cate") Long cate, @Param("store") Long store);
}
