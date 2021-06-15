package com.virinchi.rms.repository;

import com.virinchi.rms.entity.QuickMarkCopy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuickMarkCopyRepository extends 
        JpaRepository<QuickMarkCopy, Integer>{
    
}
