package com.belhopat.backoffice.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.belhopat.backoffice.model.User;
import com.belhopat.backoffice.repository.UserRepository;
import com.belhopat.backoffice.service.LoginService;
import com.belhopat.backoffice.session.SessionUser;

/**
 * @author Akhil Prakash Spring security login service
 */
@Service("loginService")
public class LoginServiceImpl implements UserDetailsService, LoginService {

	@Autowired
	UserRepository userRepo;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.core.userdetails.UserDetailsService#
	 * loadUserByUsername(java.lang.String) loads the user by its username and
	 * authenticates
	 */
	@Transactional
	@Override
	public UserDetails loadUserByUsername(String username) {
		List<GrantedAuthority> authorities = null;
		User user = userRepo.findByUsername(username);
		if (user != null) {
			authorities = buildUserAuthority(user.getRoles().iterator().next().getRoleName());
			return buildUserForAuthentication(user, authorities);
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.core.userdetails.UserDetailsService#
	 * loadUserByUsername(java.lang.String) builds the userauthentication
	 */
	private SessionUser buildUserForAuthentication(User user, List<GrantedAuthority> authorities) {
		return new SessionUser(user.getId(), user.getRoles().iterator().next().getRoleName(), user.getUsername(), user.getPassword(), user.getEmail(),
				authorities);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.core.userdetails.UserDetailsService#
	 * loadUserByUsername(java.lang.String) builds user authority
	 */
	private List<GrantedAuthority> buildUserAuthority(String role) {
		Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();
		setAuths.add(new SimpleGrantedAuthority("ROLE_" + role));
		List<GrantedAuthority> Result = new ArrayList<GrantedAuthority>(setAuths);
		return Result;
	}

}
