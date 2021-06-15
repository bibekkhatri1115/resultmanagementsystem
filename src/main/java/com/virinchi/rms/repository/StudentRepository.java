package com.virinchi.rms.repository;

import com.virinchi.rms.entity.Student;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends
        JpaRepository<Student, Integer> {

    @Modifying
    @Query(value = "select s.id,s.name,s.subject_code,s.credit_hour from tbl_subject s join tbl_program_semester ps on ps.subject_id = s.id "
            + "where ps.semester_id = ? and ps.program_id = ?", nativeQuery = true)
    List<Object> findSemester(int semesterId, int programId);
}
