package com.roky.thunderspi.repositories;

import com.roky.thunderspi.entities.Role;
import com.roky.thunderspi.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User,Long> {
    Optional<User> findByEmail(String email);

    @Modifying
    @Query("update User set role = :role where email = :email")
    void updateUserRole(@Param("email") String email, @Param("role") Role role);


    @Query("SELECT u FROM User u WHERE u.verificationCode = ?1")
    public User findByVerificationCode(String code);

    public User findByResetPasswordToken(String token);
}
