package com.virinchi.rms.repository;

import com.virinchi.rms.entity.Mark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MarkRepository extends 
        JpaRepository<Mark, Integer>{
    
}
