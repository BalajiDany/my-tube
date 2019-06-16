package com.showcase.mytube.entity;

import com.showcase.mytube.model.UserDetailsModel;
import com.showcase.mytube.model.UserRoleModel;
import lombok.Data;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Data
public class UserDetailsEntity {

    private String userName;

    private String email;

    private List<String> role;

    public static UserDetailsEntity modelToEntity(Optional<UserDetailsModel> optionalUserDetailsModel) {
        UserDetailsEntity userDetailsEntity = new UserDetailsEntity();
        if (optionalUserDetailsModel.isPresent()) {
            UserDetailsModel userDetailsModel = optionalUserDetailsModel.get();
            userDetailsEntity.setUserName(userDetailsModel.getUsername());
            userDetailsEntity.setEmail(userDetailsModel.getEmail());

            List<String> userRoleList = userDetailsModel.getUserRoleModelList()
                    .stream()
                    .map(UserRoleModel::getName)
                    .collect(Collectors.toList());

            userDetailsEntity.setRole(userRoleList);
        }
        return userDetailsEntity;
    }

}
