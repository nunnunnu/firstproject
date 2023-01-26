package com.green.firstproject.repository.menu.sellermenu;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.green.firstproject.entity.menu.basicmenu.BurgerInfoEntity;
import com.green.firstproject.entity.menu.basicmenu.DogInfoEntity;
import com.green.firstproject.entity.menu.basicmenu.DrinkInfoEntity;
import com.green.firstproject.entity.menu.basicmenu.SideInfoEntity;
import com.green.firstproject.entity.menu.sellermenu.EventInfoEntity;
import com.green.firstproject.entity.menu.sellermenu.MenuInfoEntity;

@Repository
public interface MenuInfoRepository extends JpaRepository<MenuInfoEntity, Long>{
     MenuInfoEntity findByMenuSeq(Long seq);

     @Query("select m from MenuInfoEntity m left join fetch m.burger left join fetch m.side left join fetch m.drink left join fetch m.dog where m.menuSeq=:seq")
     MenuInfoEntity findMenuSeq(@Param("seq") Long seq);

     List<MenuInfoEntity> findByBurger(Long burger);
     List<MenuInfoEntity> findByBurger(BurgerInfoEntity burger);
     List<MenuInfoEntity> findBydog(DogInfoEntity dog);
     List<MenuInfoEntity> findByDrink(DrinkInfoEntity drink);
     List<MenuInfoEntity> findBySide(SideInfoEntity side);

     List<MenuInfoEntity> findBySideAndBurgerIsNullAndEventIsNull(SideInfoEntity side);

     @Query("select m from MenuInfoEntity m where m.drink=:drink and side is null and burger is null and event is null")
     List<MenuInfoEntity> findDrinkMenu(@Param("drink") DrinkInfoEntity drink);

     @Query("select m from MenuInfoEntity m where m.dog=:dog and side is null and burger is null and event is null and drink is null")
     List<MenuInfoEntity> findDogMenu(@Param("dog") DogInfoEntity dog);
     
     
     @Query("select m from MenuInfoEntity m where m.event=:event and side is null and burger is null")
     List<MenuInfoEntity> findEventMenu(@Param("event") EventInfoEntity event);
}
