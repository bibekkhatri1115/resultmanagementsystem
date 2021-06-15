package com.virinchi.rms.repository;

import com.virinchi.rms.entity.Semester;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SemesterRepository extends 
        JpaRepository<Semester, Integer>{
}
