package com.cehn17.jobportal.repository;

import com.cehn17.jobportal.entity.UserType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserTypeRepository extends JpaRepository<UserType, Integer> {
}
