package com.bouali.banking.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Transaction extends AbstractEntity{


    private BigDecimal amount;
    @Enumerated(EnumType.STRING)
    private TransactionType type;
    private String destinationIban;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;

}
