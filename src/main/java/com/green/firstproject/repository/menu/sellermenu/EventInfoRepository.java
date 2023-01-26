package com.green.firstproject.repository.menu.sellermenu;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.green.firstproject.entity.menu.CategoryEntity;
import com.green.firstproject.entity.menu.sellermenu.EventInfoEntity;
import com.green.firstproject.vo.menu.EventCateVO;

@Repository
public interface EventInfoRepository extends JpaRepository<EventInfoEntity, Long>{
     EventInfoEntity findByEiSeq(Long seq);

     public Integer countByEiName(String eiName);

     List<EventInfoEntity> findByCate(CategoryEntity cate);

     @Query(value = "select a.ei_seq as seq, a.ei_name as name , a.ei_detail as detail , a.ei_uri as uri , c.count, c.price, "
                    +"if(b.es_stock=0,'true', 'false') as soldout "
               +"from event_info a "
               +"join (select * from event_stock where es_si_seq=:store) b on a.ei_seq =b.es_ei_seq "
               +"join (select menu_ei_seq"
               +", count(menu_ei_seq) as 'count', min(menu_price) as 'price' from menu_info where menu_bi_seq is null group by menu_ei_seq) c on c.menu_ei_seq = a.ei_seq "
               +"where a.ei_cate =:cate and ( now() between a.ei_start_dt and a.ei_end_dt ) "
          , nativeQuery = true)
     List<EventCateVO> searchEvent(@Param("cate") Long cate, @Param("store") Long store);
}
