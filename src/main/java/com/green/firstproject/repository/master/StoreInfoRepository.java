package com.green.firstproject.repository.master;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.green.firstproject.entity.master.StoreInfoEntity;

@Repository
public interface StoreInfoRepository extends JpaRepository<StoreInfoEntity, Long>{
    public List <StoreInfoEntity> findAllBySiSeq(Long siSeq);
    public StoreInfoEntity findBySiSeq(Long seq);
    public StoreInfoEntity findBySiName(String name);

    @Query(value = "select * from store_info where si_name like %:keyword%", nativeQuery = true)
    List<StoreInfoEntity> searchStoreName(@Param("keyword") String keyword);
}