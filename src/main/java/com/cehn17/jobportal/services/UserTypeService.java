package com.cehn17.jobportal.services;

import com.cehn17.jobportal.entity.UserType;
import com.cehn17.jobportal.repository.UserTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserTypeService {
    private final UserTypeRepository userTypeRepository;

    @Autowired
    public UserTypeService(UserTypeRepository userTypeRepository) {
        this.userTypeRepository = userTypeRepository;
    }

    public List<UserType> findAll() {
        return userTypeRepository.findAll();
    }
}
