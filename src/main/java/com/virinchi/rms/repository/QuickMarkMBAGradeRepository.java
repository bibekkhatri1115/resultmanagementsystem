package com.virinchi.rms.repository;

import com.virinchi.rms.entity.QuickMarkMBAGrade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuickMarkMBAGradeRepository extends 
        JpaRepository<QuickMarkMBAGrade, Integer>{
    
}
