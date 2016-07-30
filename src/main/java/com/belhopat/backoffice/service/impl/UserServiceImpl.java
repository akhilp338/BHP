package com.belhopat.backoffice.service.impl;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.belhopat.backoffice.dto.EmployeeDto;
import com.belhopat.backoffice.dto.UserDTO;
import com.belhopat.backoffice.model.Employee;
import com.belhopat.backoffice.model.User;
import com.belhopat.backoffice.repository.CandidateRepository;
import com.belhopat.backoffice.repository.EmployeeRepository;
import com.belhopat.backoffice.repository.UserRepository;
import com.belhopat.backoffice.service.MailService;
import com.belhopat.backoffice.service.UserService;
import com.belhopat.backoffice.util.PasswordUtil;
import com.belhopat.backoffice.util.RandomPasswordGenerator;

/**
 * @author BHP_DEV
 * Logged in user service
 */
@Component
@Transactional
public class UserServiceImpl implements UserService{

	@Autowired
	UserRepository userRepo;
	
	@Autowired
	MailService mailService;
	
	@Autowired
	CandidateRepository candidateRepo;
	
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	@Autowired
	RandomPasswordGenerator randomPasswordGenerator;
	
	private static final AtomicLong counter = new AtomicLong();
	
	private static List<User> users;
	
	static{
		users= populateDummyUsers();
	}

	public List<User> findAllUsers() {
		return users;
	}
	public User findById(long id) {
		for(User user : users){
			if(user.getId() == id){
				return user;
			}
		}
		return null;
	}
	public User findByName(String name) {
		for(User user : users){
			if(user.getUsername().equalsIgnoreCase(name)){
				return user;
			}
		}
		return null;
	}
	public User saveUser(User user) {
		User userNew=userRepo.save(user);
		user.setId(counter.incrementAndGet());
		users.add(user);
		return userNew;
	}
	public void updateUser(User user) {
		int index = users.indexOf(user);
		users.set(index, user);
	}
	public void deleteUserById(long id) {
		
		for (Iterator<User> iterator = users.iterator(); iterator.hasNext(); ) {
		    User user = iterator.next();
		    if (user.getId() == id) {
		        iterator.remove();
		    }
		}
	}
	public boolean isUserExist(User user) {
		return findByName(user.getUsername())!=null;
	}
	
	public void deleteAllUsers(){
		users.clear();
	}

	private static List<User> populateDummyUsers(){
		List<User> users = new ArrayList<User>();
		
		users.add(new User(counter.incrementAndGet(),"Sam", "NY", "sam@abc.com"));
		users.add(new User(counter.incrementAndGet(),"Tomy", "ALBAMA", "tomy@abc.com"));
		users.add(new User(counter.incrementAndGet(),"Kelly", "NEBRASKA", "kelly@abc.com"));
		return users;
	}

	@Override
	public boolean generatePasswordResetLink( String userEmail ) throws MessagingException, UnsupportedEncodingException {
		User user = userRepo.findByEmail( userEmail );
		if ( user != null ){
			String generatedPassword = randomPasswordGenerator.nextSessionId();
			String forgotPasswordToken = PasswordUtil.generateRandomPassword( 15 );
			user.setForgotPasswordStatus(true);
			user.setForgotPasswordToken(forgotPasswordToken);
			user.setPassword( generatedPassword );
			userRepo.saveAndFlush( user );
			mailService.sendPasswordResetMail( user );
			return true;
		}
		return false;
	}
	@Override
	public Boolean resetPassword( UserDTO user ) {
		Boolean resetStatus = false;
		if( user.getResetToken() != null ){
			User persistedUser = userRepo.findByForgotPasswordToken( user.getResetToken() );
			if( persistedUser != null && persistedUser.getForgotPasswordStatus().equals(true) 
					&& persistedUser.getPassword().equals( user.getCurrentPassword() )){
				persistedUser.setPassword ( user.getNewPassword() );
				persistedUser.setForgotPasswordStatus( false );
				persistedUser.setForgotPasswordToken( null );
				userRepo.saveAndFlush( persistedUser );
			}
			resetStatus = true;
		}
		return resetStatus;
	}
	@Override
	public Employee createUser(EmployeeDto employee) {
		Employee employeeEntity=employeeRepository.findByEmployeeId(employee.getEmployeeId());
		User user=null;
		if(employeeEntity.getEmployeeUser()==null)
			 user=new User();
		else
			 user=employeeEntity.getEmployeeUser();
		user.setEmail(employee.getOfficialEmailId());
		user.setUsername(employee.getEmployeeId());
		user.setDesignation(employeeEntity.getEmployeeMaster().getDesignation());
		employeeEntity.setEmployeeUser(user);
		Employee returnEntity=employeeRepository.save(employeeEntity);
		try {
			mailService.sendPasswordResetMail( employeeEntity.getEmployeeUser() );
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return returnEntity;
	}

}
