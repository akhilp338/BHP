package com.belhopat.backoffice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.belhopat.backoffice.model.User;
/**
 * @author BHP_DEV
 * Data repository for user entity 
 *
 */
@Repository
public interface UserRepository extends JpaRepository< User, Long >  {

	User findByEmail(String lastname);


	@Query("select u from User u where u.username=:username and u.password=:password")
	public User findByUserNameAndPwd(@Param ("username")String username, @Param("password")String password);


	User findByUsername(String username);


}
