package com.indigo.bankservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Inventory {
    @Id
    String productId;
    int quantity;

}
