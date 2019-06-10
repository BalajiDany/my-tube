package com.showcase.mytube.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.showcase.mytube.model.UserDetailsModel;
import com.showcase.mytube.repository.UserDetailsRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserDetailsRepository userDetailsRepository;

	@Override
	public UserDetails loadUserByUsername(String user) throws UsernameNotFoundException {
		Optional<UserDetailsModel> optionsUserDetail = userDetailsRepository.findByUsername(user);

		optionsUserDetail.orElseThrow(() -> new UsernameNotFoundException("User Not Found : " + user));
		UserDetailsModel userDetails = optionsUserDetail.get();

		AccountStatusUserDetailsChecker accountChecker = new AccountStatusUserDetailsChecker();
		accountChecker.check(userDetails);

		return userDetails;
	}

}
