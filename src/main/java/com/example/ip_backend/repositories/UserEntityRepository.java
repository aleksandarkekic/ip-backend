package com.example.ip_backend.repositories;

import com.example.ip_backend.models.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface UserEntityRepository extends JpaRepository<UserEntity, Integer> {
    UserEntity findByUsername(String username);
    @Query("select u.id from UserEntity u where u.username = :username")
    Integer findIdByUsername(String username);

    boolean existsByUsername(String username);
    @Query("select u.role from UserEntity u where u.username = :username")

   String findRoleByUsername(String username);
    @Query("select u.accountConfirmed from UserEntity u where u.username = :username")

    Boolean isEmailConfirmed(String username);

    @Query("select u.username from UserEntity u where u.id = :id")

    String findUsernameById(Integer id);

    @Query("SELECT CASE WHEN u.category.id IS NOT NULL THEN TRUE ELSE FALSE END FROM UserEntity u WHERE u.id = :userId")
    Boolean isSubscribed(Integer userId);

    @Query("select u.category.id from UserEntity u where u.id=:userId")
    Integer findCategoryIdByUserId(Integer userId);
    @Query("select u from UserEntity u where u.role = 'USER'")
    List<UserEntity> findAllUsersWithRoleUser();
    @Query("SELECT CASE WHEN u.role = 'advisor' THEN true ELSE false END FROM UserEntity u WHERE u.id = :id")
    Boolean ifRoleIsAdvisor(Integer id);

    @Query("select u from UserEntity u where u.role = 'ADVISOR'")
    List<UserEntity> findAllUsersWithRoleAdvisor();
}
