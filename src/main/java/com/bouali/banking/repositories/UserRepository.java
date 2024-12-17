package com.bouali.banking.repositories;


import com.bouali.banking.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {

    List<User> findAllByFirstname(String firstname);

    List<User> findAllByFirstnameContaining(String firstname);

    List<User> findAllByFirstnameContainingIgnoreCase(String firstname);

    List<User> findAllByAccountIban(String iban);

    User findByFirstnameContainingIgnoreCaseAndEmail(String firstname, String email);


}
