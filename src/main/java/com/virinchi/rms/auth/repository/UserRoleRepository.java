package com.virinchi.rms.auth.repository;

import com.virinchi.rms.auth.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Integer> {

    List<UserRole> findByUserIdOrderById(int userId);

    List<UserRole> findByUserUserName(String userName);

}
