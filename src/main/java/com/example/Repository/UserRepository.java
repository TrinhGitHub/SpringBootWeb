package com.example.Repository;

import com.example.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    Optional<User> findByUsername(String username);
    @Transactional
    @Modifying
    @Query("UPDATE User u SET u.email = :email, u.address = :address, u.phone = :phone, u.name = :name WHERE u.userid = :userId")
    void updateUserDetails(int userId, String email, String address, String phone, String name);
}
