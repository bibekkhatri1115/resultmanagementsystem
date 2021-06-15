package com.virinchi.rms.repository;

import com.virinchi.rms.entity.Program;
import com.virinchi.rms.entity.Semester;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProgramRepository extends 
        JpaRepository<Program, Integer>{
        List<Semester> findByRelationsSemesterId(Integer id);
}

