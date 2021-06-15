package com.virinchi.rms.repository;

import com.virinchi.rms.entity.Registration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistrationRepository extends 
        JpaRepository<Registration, Integer>{
    
}
