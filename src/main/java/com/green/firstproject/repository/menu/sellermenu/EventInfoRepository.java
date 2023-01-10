package com.green.firstproject.repository.menu.sellermenu;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.green.firstproject.entity.menu.sellermenu.EventInfoEntity;

@Repository
public interface EventInfoRepository extends JpaRepository<EventInfoEntity, Long>{
     EventInfoEntity findByEiSeq(Long seq);

     @Query("select e from EventInfoEntity e join fetch e.eiDiSeq join fetch e.eiDi2Seq where e.eiSeq=:seq")
     EventInfoEntity findByEventMenu(@Param("seq") Long seq);

     public Integer countByEiName(String eiName);
}
