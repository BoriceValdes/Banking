package com.bouali.banking.services;

import com.bouali.banking.dto.UserDto;

public interface UserService extends AbstractService<UserDto>{

    Integer validateAccount(Integer id);

    Integer invalidateAccount(Integer id);


}
