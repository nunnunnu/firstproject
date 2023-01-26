package com.green.firstproject.repository.menu.basicmenu;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.green.firstproject.entity.menu.CategoryEntity;
import com.green.firstproject.entity.menu.basicmenu.DogInfoEntity;

@Repository
public interface DogInfoRepository extends JpaRepository<DogInfoEntity, Long>{
    List<DogInfoEntity> findByCate(CategoryEntity cate);
     public Integer countByDogName(String dogName);
     DogInfoEntity findByDogSeq(Long seq);
     DogInfoEntity findByDogUri(String uri);
}
