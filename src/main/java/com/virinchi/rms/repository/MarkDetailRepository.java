package com.virinchi.rms.repository;

import com.virinchi.rms.entity.MarkDetail;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MarkDetailRepository extends 
        JpaRepository<MarkDetail, Integer>{
        List<MarkDetail> getByStudentMarkId(Integer id);

}
