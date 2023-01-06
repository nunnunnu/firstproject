package com.green.firstproject.repository.menu.basicmenu;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.green.firstproject.entity.menu.basicmenu.DogInfoEntity;
@Repository
public interface DogInfoRepository extends JpaRepository<DogInfoEntity, Long>{
     public Integer countByDogName(String dogName);
}
