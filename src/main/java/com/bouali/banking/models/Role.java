package com.bouali.banking.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Data
@AllArgsConstructor
@SuperBuilder
@NoArgsConstructor
@Entity
public class Role extends AbstractEntity{


    private String name;
    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;
}
