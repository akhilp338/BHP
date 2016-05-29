package com.belhopat.backoffice.service;

import java.util.List;

import javax.mail.MessagingException;

import org.springframework.stereotype.Service;

import com.belhopat.backoffice.model.User;


@Service
public interface UserService {
	
	User findById( long id );
	
	User findByName( String name );
	
	void saveUser( User user );
	
	void updateUser( User user );
	
	void deleteUserById( long id );

	List<User> findAllUsers(); 
	
	void deleteAllUsers();
	
	public boolean isUserExist( User user );

	boolean generatePasswordResetLink( String email ) throws MessagingException;
	
}
