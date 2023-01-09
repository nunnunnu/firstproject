package com.green.firstproject.repository.menu.sellermenu;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.green.firstproject.entity.menu.sellermenu.EventInfoEntity;

@Repository
public interface EventInfoRepository extends JpaRepository<EventInfoEntity, Long>{
     EventInfoEntity findByEiSeq(Long seq);
     public Integer countByEiName(String eiName);
}
