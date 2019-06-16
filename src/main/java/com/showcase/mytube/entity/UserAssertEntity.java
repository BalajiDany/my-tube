package com.showcase.mytube.entity;

import com.showcase.mytube.model.UserFileModel;
import lombok.Data;

@Data
public class UserAssertEntity {

    private String fileName;

    private String filePath;

    private String fileType;

    public static UserAssertEntity modelToEntity(UserFileModel userFileModel) {
        UserAssertEntity userAssertEntity = new UserAssertEntity();
        userAssertEntity.setFileName(userFileModel.getFileName());
        userAssertEntity.setFilePath(userFileModel.getFilePath());
        return userAssertEntity;
    }
}
