package com.showcase.mytube.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.showcase.mytube.model.UserDetailsModel;

public interface UserDetailsRepository extends JpaRepository<UserDetailsModel, Integer> {

	Optional<UserDetailsModel> findByUsername(String username);

}
