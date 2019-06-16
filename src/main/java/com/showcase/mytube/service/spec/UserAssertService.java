package com.showcase.mytube.service.spec;

import com.showcase.mytube.entity.UserAssertEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UserAssertService {

    List<UserAssertEntity> getByUserName(String userName, int limit, int offset);

    UserAssertEntity uploadFile(MultipartFile file, String userName);
}
