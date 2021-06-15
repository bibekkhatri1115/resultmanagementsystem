/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.virinchi.rms.auth.repository;

import com.virinchi.rms.auth.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author apple
 */
@Repository
public interface UserRepository 
        extends JpaRepository<User,Integer> {
    
    User findByUserName(String userName);
    User findByEmail(String email);
    //User findByUserNameOrEmail(String param);
    int countByUserName(String userName);
    int countByUserNameAndIdNot(String userName,int id);
    int countByEmail(String email);
    int countByEmailAndIdNot(String email,int id);
    
    @Modifying
    @Query(nativeQuery =true,value = "update tbl_users set last_login=CURRENT_TIMESTAMP where id=?")
    void updateLastLogin(int id);
    
}
