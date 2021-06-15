package com.virinchi.rms.repository;

import com.virinchi.rms.entity.ProgramSemesterSubject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProgramSemesterSubjectRepository extends 
        JpaRepository<ProgramSemesterSubject, Integer>{
    
}
