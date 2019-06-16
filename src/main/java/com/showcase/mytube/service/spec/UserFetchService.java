package com.showcase.mytube.service.spec;

import com.showcase.mytube.entity.UserDetailsEntity;

import java.util.List;

public interface UserFetchService {

    UserDetailsEntity getUserDetailsByName(String userName);

    List<UserDetailsEntity> getAllUser(int limit, int offset);

}
