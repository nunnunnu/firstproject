package com.green.firstproject.repository.menu.basicmenu;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.green.firstproject.entity.menu.CategoryEntity;
import com.green.firstproject.entity.menu.basicmenu.DogInfoEntity;
import com.green.firstproject.vo.menu.DogCateVO;

@Repository
public interface DogInfoRepository extends JpaRepository<DogInfoEntity, Long>{
    List<DogInfoEntity> findByCate(CategoryEntity cate);
    public Integer countByDogName(String dogName);
    DogInfoEntity findByDogSeq(Long seq);

    @Query(value = "select a.dog_seq as seq, a.dog_name as name , a.dog_detail as detail , a.dog_uri as uri , c.count, c.price, "
                    +"if(b.dogs_stock=0,'true', 'false') as soldout "
            +"from dog_info a "
            +"join (select * from dog_stock where dogs_si_seq=:store) b on a.dog_seq =b.dogs_seq "
            +"join (select menu_dog_seq, count(menu_dog_seq) as 'count', min(menu_price) as 'price' from menu_info "
            +"where menu_bi_seq is null group by menu_dog_seq) c on c.menu_dog_seq = a.dog_seq "
            +"where a.dog_cate =:cate"
        , nativeQuery = true)
    List<DogCateVO> searchDog(@Param("cate") Long cate, @Param("store") Long store);

}
