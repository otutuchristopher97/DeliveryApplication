package com.ParcelDelivery.EnterpriseParcelDelivery.user;

import com.ParcelDelivery.EnterpriseParcelDelivery.entity.User;
import com.ParcelDelivery.EnterpriseParcelDelivery.user.command.UserCommand;
import com.ParcelDelivery.EnterpriseParcelDelivery.user.command.UserCommandFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

import java.util.List;
@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserCommandFactory userCommandFactory;

    public User addUser(UserDTO userDTO){
        return (User) userCommandFactory
                .create(UserCommand.ADD_USER, userDTO).execute();
    }

    public List<User> getUsers(){
        return (List<User>) userCommandFactory
                .create(UserCommand.GET_ALL_USER).execute();
    }
    public List<User> getUsersByRoleId(int roleId){
        return (List<User>) userCommandFactory
                .create(UserCommand.GET_USER_BY_ROLE_ID, roleId).execute();
    }
    public User getUserById(int id){
        return (User) userCommandFactory
                .create(UserCommand.GET_USER_BY_ID, id).execute();
    }
    public Boolean deleteUser(int id){
        return (Boolean) userCommandFactory
                .create(UserCommand.DELETE_USER, id).execute();
    }
    public User updateUser(User user){
        return (User) userCommandFactory
                .create(UserCommand.UPDATE_USER, user).execute();
    }

    public User authenticatedUser(@AuthenticationPrincipal User user){
        return (User) userCommandFactory
                .create(UserCommand.GET_CURRENT_USER, user).execute();
    }

    public User findUserByEmail(String email) {
        return (User) userCommandFactory
                .create(UserCommand.GET_USER_BY_EMAIL, email).execute();
    }
}
