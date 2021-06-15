package com.virinchi.rms.repository;

import com.virinchi.rms.entity.Guardian;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GuardianRepository extends 
        JpaRepository<Guardian, Integer>{
    
}
