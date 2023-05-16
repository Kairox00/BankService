package com.indigo.bankservice.repositories;

import com.indigo.bankservice.models.BankAccount;
import jakarta.persistence.QueryHint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BankRepository extends JpaRepository<BankAccount, String> {
    @QueryHints(@QueryHint(name = org.hibernate.annotations.QueryHints.CACHEABLE, value = "false"))
    Optional<BankAccount> findById(String id);

}
