package com.bouali.banking.services;

import com.bouali.banking.dto.AddressDto;
import com.bouali.banking.dto.ContactDto;
import com.bouali.banking.repositories.ContactRepository;
import com.bouali.banking.validators.ObjectsValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

public interface AddressService extends AbstractService<AddressDto>{
    @Service
    @RequiredArgsConstructor
    class ContactServiceImpl implements ContactService {

        private final ContactRepository repository;
        private ObjectsValidator<ContactDto> validator;
        @Override
        public Integer save(ContactDto dto) {
            validator.validate(dto);
            return repository.save(ContactDto.toEntity(dto)).getId();
        }

        @Override
        public List<ContactDto> findAll() {
            return repository.findAll().stream()
                    .map(ContactDto::fromEntity)
                    .collect(Collectors.toList());
        }

        @Override
        public ContactDto findById(Integer id) {
            return repository.findById(id)
                    .map(ContactDto::fromEntity)
                    .orElseThrow(() -> new EntityNotFoundException("No contact was found with the ID : "+ id));
        }

        @Override
        public void delete(Integer id) {
            // todo check delete contact
            repository.deleteById(id);
        }

        @Override
        public List<ContactDto> findAllByUserId(Integer userId) {
            return repository.findAllByUserId(userId)
                    .stream()
                    .map(ContactDto::fromEntity)
                    .collect(Collectors.toList());
        }
    }
}
