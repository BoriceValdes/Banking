package com.bouali.banking.services;

import com.bouali.banking.dto.AddressDto;
import com.bouali.banking.repositories.AddressRepository;
import com.bouali.banking.validators.ObjectsValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService{

    private final AddressRepository repository;
    private final ObjectsValidator<AddressDto> validator;
    @Override
    public Integer save(AddressDto dto) {
        validator.validate(dto);
        return repository.save(AddressDto.fromEntity(dto)).getId();
    }

    @Override
    public List<AddressDto> findAll() {
        return repository.findAll().stream()
                .map(AddressDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public AddressDto findById(Integer id) {
        return repository.findById(id)
                .map(AddressDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException("No address found with the ID : "+ id));
    }

    @Override
    public void delete(Integer id) {
    // todo check delete
        repository.deleteById(id);
    }
}
