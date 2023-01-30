package com.green.firstproject.service.stock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.green.firstproject.entity.master.StoreInfoEntity;
import com.green.firstproject.entity.menu.basicmenu.BurgerInfoEntity;
import com.green.firstproject.entity.stock.BurgerStockEntity;
import com.green.firstproject.repository.master.StoreInfoRepository;
import com.green.firstproject.repository.menu.basicmenu.BurgerInfoRepository;
import com.green.firstproject.repository.stock.BurgerStockRepository;

@Service
public class StockService {
     @Autowired BurgerInfoRepository bRepo;
     @Autowired StoreInfoRepository siRepo;
     @Autowired BurgerStockRepository bsRepo;

     public void updateBurgerStatus(Long seq, Long storeSeq){
          BurgerInfoEntity burger = bRepo.findByBiSeq(seq);
          StoreInfoEntity store = siRepo.findBySiSeq(storeSeq);

          BurgerStockEntity burgerStock = bsRepo.findByStoreAndBurger(store, burger);
          System.out.println(burgerStock);
          if(burgerStock==null){
               burgerStock = bsRepo.save(new BurgerStockEntity(storeSeq, store, burger, 1));
          }else{
               if(burgerStock.getBsStock()==1){
                    burgerStock.setBsStock(0);
               }else{
                    burgerStock.setBsStock(1);
               }
          }
          bsRepo.save(burgerStock);
     }
}
