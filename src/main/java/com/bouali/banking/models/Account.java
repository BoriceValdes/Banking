package com.bouali.banking.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Data
@NoArgsConstructor
@SuperBuilder
@AllArgsConstructor
@Entity
public class Account extends AbstractEntity{


    private String iban;

    @OneToOne
    @JoinColumn(name = "id_user")
    private User user;
}
