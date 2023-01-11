package com.green.firstproject.repository.menu.option;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.green.firstproject.entity.menu.option.SideOptionEntity;

@Repository
public interface SideOptionRepository extends JpaRepository<SideOptionEntity,Long>{
     SideOptionEntity findBySoSeq(Long seq);
     List<SideOptionEntity> findBySoSize(Integer size);
}
