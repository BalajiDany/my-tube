package com.showcase.mytube.service.impl;

import com.showcase.mytube.entity.UserDetailsEntity;
import com.showcase.mytube.model.UserDetailsModel;
import com.showcase.mytube.repository.UserDetailsRepository;
import com.showcase.mytube.service.spec.UserFetchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserFetchServiceImpl implements UserFetchService {

    @Autowired
    @Qualifier("userDetailService")
    UserDetailsService userDetailService;

    @Autowired
    private UserDetailsRepository userDetailsRepository;

    @Override
    public List<UserDetailsEntity> getAllUser(int limit, int offset) {
        return userDetailsRepository.findAll().stream()
                .skip(offset).limit(limit)
                .map(Optional::ofNullable)
                .map(UserDetailsEntity::modelToEntity).collect(Collectors.toList());
    }

    @Override
    public UserDetailsEntity getUserDetailsByName(String userName) {
        Optional<UserDetailsModel> userDetailsModel = userDetailsRepository.findByUsername(userName);
        return UserDetailsEntity.modelToEntity(userDetailsModel);
    }

}
