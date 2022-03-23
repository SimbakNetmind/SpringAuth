package com.r2system.security.app.SpringBootSecurityApp.repository;


import com.r2system.security.app.SpringBootSecurityApp.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<Users,Long> {

    Optional<Users> findByUsername(String username);

//    @Modifying
//    @Query("update User set role = :role where username = :username")
//    void updateUserRole(@Param("username") String username, @Param("role") Role role);
}
