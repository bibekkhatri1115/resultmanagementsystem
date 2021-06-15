package com.virinchi.rms.repository;

import com.virinchi.rms.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectRepository extends 
        JpaRepository<Subject, Integer>{
    
}
