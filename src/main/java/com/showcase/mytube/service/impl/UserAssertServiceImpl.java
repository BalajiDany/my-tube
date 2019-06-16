package com.showcase.mytube.service.impl;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.showcase.mytube.entity.UserAssertEntity;
import com.showcase.mytube.model.UserDetailsModel;
import com.showcase.mytube.model.UserFileModel;
import com.showcase.mytube.repository.UserDetailsRepository;
import com.showcase.mytube.repository.UserFileRepository;
import com.showcase.mytube.service.spec.UserAssertService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
public class UserAssertServiceImpl implements UserAssertService {


    @Value("${aws.s3.bucketName}")
    private String bucketName;

    @Value("${assert.prefix}")
    private String prefix;

    @Autowired
    private UserDetailsRepository userDetailsRepository;

    @Autowired
    private UserFileRepository userFileRepository;

    @Autowired
    private AmazonS3 s3client;

    @Override
    public List<UserAssertEntity> getByUserName(String userName, int limit, int offset) {
        Optional<UserDetailsModel> optionalUserDetailsModel = userDetailsRepository.findByUsername(userName);
        if (optionalUserDetailsModel.isPresent()) {
            UserDetailsModel userDetailsModel = optionalUserDetailsModel.get();
            return userFileRepository.findAllByUserId(userDetailsModel.getId()).stream()
                    .skip(offset).limit(limit)
                    .map(UserAssertEntity::modelToEntity).collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    @Override
    public UserAssertEntity uploadFile(MultipartFile file, String userName) {

        UUID randomId = UUID.randomUUID();
        Optional<UserDetailsModel> optionalUserDetailsModel = userDetailsRepository.findByUsername(userName);
        UserFileModel userFileModel = new UserFileModel();

        if (optionalUserDetailsModel.isPresent()) {
            String fileName = file.getOriginalFilename();
            String folderPath = new StringBuffer(prefix).append("/")
                    .append(userName).append("/")
                    .append(randomId.toString()).append("/")
                    .append(file.getOriginalFilename()).toString();
            try {
                File converterFile = convertMultiPartToFile(file);
                s3client.putObject(new PutObjectRequest(bucketName, folderPath, converterFile));
                UserDetailsModel userDetailsModel = optionalUserDetailsModel.get();
                userFileModel.setUserId(userDetailsModel.getId());
                userFileModel.setFilePath(folderPath);
                userFileModel.setFileName(fileName);
                userFileRepository.save(userFileModel);
                converterFile.delete();
            } catch (Exception e) {
                log.error(e.getLocalizedMessage());
                e.printStackTrace();
            }
        }
        return UserAssertEntity.modelToEntity(userFileModel);
    }

    private File convertMultiPartToFile(MultipartFile file) throws Exception {
        File convFile = new File(file.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }
}
