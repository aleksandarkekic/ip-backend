package com.example.ip_backend.services;
import com.example.ip_backend.base.CrudService;
import com.example.ip_backend.models.dto.User;
import com.example.ip_backend.models.dto.UserResponse;
import com.example.ip_backend.models.entities.UserEntity;

import java.util.List;
import java.util.Optional;

public interface UserService extends CrudService<Integer> {

    User getUserByUsername(String username);

    Integer getCurrentId();
    public User getCurrentUser();

    public UserResponse getCurrentUserInfo();
    String getCurrentRole();
    Boolean isEmailConfirmed(String username);
    String findUsernameById(Integer id);

    Boolean isSubscribed(Integer userId);
    Integer findCategoryIdByUserId(Integer userId);

    Integer findIdByUsername(String username);
    List<User> findAllUsersWithRoleUser();

    Boolean ifRoleIsAdvisor(Integer id);
    List<User> findAllUsersWithRoleAdvisor();

}
