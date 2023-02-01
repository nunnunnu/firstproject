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
    public StoreInfoEntity findBySiDeliveryArea(String area);
    @Query(value = "select * from store_info where si_name like %:keyword%", nativeQuery = true)
    List<StoreInfoEntity> searchStoreName(@Param("keyword") String keyword);

    // @Query(value = "select *, if(time(now() between si_open_time and si_close_time), 'true', 'false')as openStatus from store_info where si_seq = :seq", nativeQuery = true)
    // List<StoreInfoEntity> searchStoreOpenStatus(@Param("seq") Long seq);
}