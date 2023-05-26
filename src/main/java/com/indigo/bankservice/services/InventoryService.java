package com.indigo.bankservice.services;

import com.indigo.bankservice.models.BankAccount;
import com.indigo.bankservice.models.Inventory;
import com.indigo.bankservice.repositories.InventoryRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class InventoryService {
     @Autowired
     InventoryRepository repository;

      @PersistenceContext
    private EntityManager entityManager;



     @Transactional
     public Inventory incInv(String productId, int quantity) throws Exception {
         Inventory inv = repository.findById(productId).orElse(null);
         if(inv != null){
              entityManager.refresh(inv);
              inv.setQuantity(inv.getQuantity() + quantity);
              repository.save(inv);
              return inv;
          }
          else{
              throw new Exception("Cannot find inventory");
          }
     }

      public Inventory decInv(String productId, int quantity) throws Exception {
        Inventory inv = repository.findById(productId).orElse(null);
        if(inv != null && inv.getQuantity() >= quantity){
            inv.setQuantity(inv.getQuantity()-quantity);
            repository.save(inv);
            return inv;
        }
        else{
            throw new Exception("Cannot find inventory or not enough quantity");
        }

    }
}
