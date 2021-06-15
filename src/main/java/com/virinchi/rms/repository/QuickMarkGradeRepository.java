package com.virinchi.rms.repository;

import com.virinchi.rms.entity.QuickMarkGrade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuickMarkGradeRepository extends 
        JpaRepository<QuickMarkGrade, Integer>{
    
}
