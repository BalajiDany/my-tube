package com.showcase.mytube.repository;

import com.showcase.mytube.model.UserFileModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserFileRepository extends JpaRepository<UserFileModel, Integer> {

    List<UserFileModel> findAllByUserId(int userId);
}
