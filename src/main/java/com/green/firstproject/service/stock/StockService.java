package com.green.firstproject.service.stock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.green.firstproject.entity.master.StoreInfoEntity;
import com.green.firstproject.entity.menu.basicmenu.BurgerInfoEntity;
import com.green.firstproject.entity.menu.basicmenu.DogInfoEntity;
import com.green.firstproject.entity.menu.basicmenu.DrinkInfoEntity;
import com.green.firstproject.entity.menu.basicmenu.IngredientsInfoEntity;
import com.green.firstproject.entity.menu.basicmenu.SideInfoEntity;
import com.green.firstproject.entity.menu.sellermenu.EventInfoEntity;
import com.green.firstproject.entity.stock.BurgerStockEntity;
import com.green.firstproject.entity.stock.DogStockEntity;
import com.green.firstproject.entity.stock.DrinkStockEntity;
import com.green.firstproject.entity.stock.EventStockEntity;
import com.green.firstproject.entity.stock.IngredientsStockEntity;
import com.green.firstproject.entity.stock.SideStockEntity;
import com.green.firstproject.repository.master.StoreInfoRepository;
import com.green.firstproject.repository.menu.basicmenu.BurgerInfoRepository;
import com.green.firstproject.repository.menu.basicmenu.DogInfoRepository;
import com.green.firstproject.repository.menu.basicmenu.DrinkInfoRepository;
import com.green.firstproject.repository.menu.basicmenu.IngredientsInfoRepository;
import com.green.firstproject.repository.menu.basicmenu.SideInfoRepository;
import com.green.firstproject.repository.menu.sellermenu.EventInfoRepository;
import com.green.firstproject.repository.stock.BurgerStockRepository;
import com.green.firstproject.repository.stock.DogStockRepository;
import com.green.firstproject.repository.stock.DrinkStockRepository;
import com.green.firstproject.repository.stock.EventStockRepository;
import com.green.firstproject.repository.stock.IngredientsStockRepository;
import com.green.firstproject.repository.stock.SideStockRepository;

@Service
public class StockService {
     @Autowired BurgerInfoRepository bRepo;
     @Autowired StoreInfoRepository siRepo;
     @Autowired BurgerStockRepository bsRepo;
     @Autowired DogInfoRepository diRepo;
     @Autowired DogStockRepository dogsRepo;
     @Autowired DrinkInfoRepository drinkRepo;
     @Autowired DrinkStockRepository dsRepo;
     @Autowired EventInfoRepository eiRepo;
     @Autowired EventStockRepository esRepo;
     @Autowired IngredientsInfoRepository iiRepo;
     @Autowired IngredientsStockRepository isRepo;
     @Autowired SideInfoRepository sideRepo;
     @Autowired SideStockRepository sidesRepo;

     public void updateBurgerStatus(Long seq, Long storeSeq){
          BurgerInfoEntity burger = bRepo.findByBiSeq(seq);
          StoreInfoEntity store = siRepo.findBySiSeq(storeSeq);

          BurgerStockEntity burgerStock = bsRepo.findByStoreAndBurger(store, burger);
          if(burgerStock==null){
               burgerStock = bsRepo.save(new BurgerStockEntity(storeSeq, store, burger, 1));
          }else{
               if(burgerStock.getBsStock()==1){
                    burgerStock.setBsStock(0);
               }else{
                    burgerStock.setBsStock(1);
               }
               bsRepo.save(burgerStock);
          }
     }
     
     public void updateDogStatus(Long seq, Long storeSeq) {
          DogInfoEntity dog = diRepo.findByDogSeq(seq);
          StoreInfoEntity store = siRepo.findBySiSeq(storeSeq);
     
          DogStockEntity dogStock = dogsRepo.findByStoreAndDog(store, dog);
          if(dogStock==null){
               dogStock = dogsRepo.save(new DogStockEntity(storeSeq, store, dog, 1));
          }else{
               if(dogStock.getDogsStock()==1){
                    dogStock.setDogsStock(0);
               }else{
                    dogStock.setDogsStock(1);
               }
               dogsRepo.save(dogStock);
          }
     }
     
     public void updateDrinkStatus(Long seq, Long storeSeq) {
          DrinkInfoEntity drink = drinkRepo.findByDiSeq(seq);
          StoreInfoEntity store = siRepo.findBySiSeq(storeSeq);
     
          DrinkStockEntity drinkStock = dsRepo.findByStoreAndDrink(store, drink);
          if(drinkStock==null){
               drinkStock = dsRepo.save(new DrinkStockEntity(storeSeq, store, drink, 1));
          }else{
               if(drinkStock.getDsStock()==1){
                    drinkStock.setDsStock(0);
               }else{
                    drinkStock.setDsStock(1);
               }
               dsRepo.save(drinkStock);
          }
     }
     
     public void updateEventStatus(Long seq, Long storeSeq) {
          EventInfoEntity event = eiRepo.findByEiSeq(seq);
          StoreInfoEntity store = siRepo.findBySiSeq(storeSeq);
     
          EventStockEntity eventStock = esRepo.findByStoreAndEvent(store, event);
          if(eventStock==null){
               eventStock = esRepo.save(new EventStockEntity(storeSeq, store, event, 1));
          }else{
               if(eventStock.getEsStock()==1){
                    eventStock.setEsStock(0);
               }else{
                    eventStock.setEsStock(1);
               }
               esRepo.save(eventStock);
          }
     }
     
     public void updateIngredientStatus(Long seq, Long storeSeq) {
          IngredientsInfoEntity ingredient = iiRepo.findByIiSeq(seq);
          StoreInfoEntity store = siRepo.findBySiSeq(storeSeq);
     
          IngredientsStockEntity ingredientStock = isRepo.findByStoreAndIngredient(store, ingredient);
          if(ingredientStock==null){
               ingredientStock = isRepo.save(new IngredientsStockEntity(storeSeq, store, ingredient, 1));
          }else{
               if(ingredientStock.getIsStock()==1){
                    ingredientStock.setIsStock(0);
               }else{
                    ingredientStock.setIsStock(1);
               }
               isRepo.save(ingredientStock);
          }
     }
     
     public void updateSideStatus(Long seq, Long storeSeq) {
          SideInfoEntity side = sideRepo.findBySideSeq(seq);
          StoreInfoEntity store = siRepo.findBySiSeq(storeSeq);
     
          SideStockEntity sideStock = sidesRepo.findByStoreAndSide(store, side);
          if(sideStock==null){
               sideStock = sidesRepo.save(new SideStockEntity(storeSeq, store, side, 1));
          }else{
               if(sideStock.getSsStock()==1){
                    sideStock.setSsStock(0);
               }else{
                    sideStock.setSsStock(1);
               }
               sidesRepo.save(sideStock);
          }
     }

}
