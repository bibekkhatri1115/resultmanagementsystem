package com.virinchi.rms.repository;

import com.virinchi.rms.entity.QuickMarkBICT;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface QuickMarkRepository extends 
        JpaRepository<QuickMarkBICT, Integer>{
        
    @Modifying 
    @Query(value ="INSERT tbl_quick_marks_copy SELECT * FROM tbl_quick_marks;",nativeQuery = true)
    void copyQuickMark();
}
