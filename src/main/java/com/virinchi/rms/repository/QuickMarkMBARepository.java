package com.virinchi.rms.repository;

import com.virinchi.rms.entity.QuickMarkMBA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuickMarkMBARepository extends 
        JpaRepository<QuickMarkMBA, Integer>{
    
}
