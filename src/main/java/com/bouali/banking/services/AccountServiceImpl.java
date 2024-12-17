package com.bouali.banking.services;

import com.bouali.banking.dto.AccountDto;
import com.bouali.banking.exceptions.OperationNonPermittedException;
import com.bouali.banking.models.Account;
import com.bouali.banking.repositories.AccountRepository;
import com.bouali.banking.validators.ObjectsValidator;
import lombok.RequiredArgsConstructor;
import org.iban4j.CountryCode;
import org.iban4j.Iban;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements  AccountService{

    private final AccountRepository repository;
    private final ObjectsValidator<AccountDto> validator;

    @Override
    public Integer save(AccountDto dto) {
        // block account update -> iban cannot be changed
        /*if(dto.getId() != null){
            throw new OperationNonPermittedException(
                    "Account cannot be updated",
                    "save account",
                    "Account",
                    "Update not permitted"
            );
        }*/
        validator.validate(dto);
        Account account = AccountDto.toEntity(dto);
        boolean userHasAlreadyAccount = repository.findByUserId(account.getUser().getId()).isPresent();
        if(userHasAlreadyAccount && account.getUser().isActive()){
            throw new OperationNonPermittedException(
                    "The selected user has already an active account",
                    "Create account",
                    "Account service",
                    "Account creation"
            );
        }
            // generate random IBAN when creating new account else do not update the IBAN
        if(dto.getId() == null) {
            account.setIban(generateRandomIban());
        }
        return repository.save(account).getId();
    }

    @Override
    public List<AccountDto> findAll() {
        return repository.findAll().stream()
                .map(AccountDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public AccountDto findById(Integer id) {
        return repository.findById(id)
                .map(AccountDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException("No account was found with the ID : "+ id));
    }

    @Override
    public void delete(Integer id) {
        // todo check delete Account
        repository.deleteById(id);
    }

    private String generateRandomIban(){

        String iban = Iban.random(CountryCode.DE).toFormattedString();
        boolean ibanExists = repository.findByIban(iban).isPresent();
        if(ibanExists){
            generateRandomIban();
        }
        return iban;
    }
}
