package com.indigo.bankservice.seeder;

import com.indigo.bankservice.models.BankAccount;
import com.indigo.bankservice.repositories.InventoryRepository;
import com.indigo.bankservice.services.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class SeedRunner implements CommandLineRunner {
    @Autowired
    BankService bankService;

    @Autowired
    InventoryRepository inventoryService;
    @Override
    public void run(String... args) throws Exception {
        bankService.save(new BankAccount("123", 100));
        bankService.save(new BankAccount("456", 200));
//        inventoryService.save(new Inventory("1", 1));
//        inventoryService.save(new Inventory("2", 10));
        System.out.println("Seeding DB after application startup...");
    }

}
