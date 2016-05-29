package com.belhopat.backoffice.session;

import org.springframework.security.core.context.SecurityContextHolder;

import com.belhopat.backoffice.model.User;

/**
 * @author Akhil Prakash
 *
 */
public class SessionManager {

	/**
	 * gets the current logged in user
	 *
	 */
    public static User getCurrentUser() {
        if ( SecurityContextHolder.getContext().getAuthentication() != null ) {
            SessionUser userDetails =
                ( SessionUser ) SecurityContextHolder.getContext().getAuthentication()
                    .getPrincipal();
            return new User( userDetails.getUserId(),
               userDetails.getUsername(), userDetails.getPassword(),userDetails.getEmail());
        }
        return null;
    }
    /**
     * gets the current user details from spring security session
     *
     */
    public static SessionUser getCurrentUserDetails() {
        SessionUser userDetails = null;
        if ( SecurityContextHolder.getContext().getAuthentication() != null ) {
            userDetails =
                ( SessionUser ) SecurityContextHolder.getContext().getAuthentication()
                    .getPrincipal();
        }
        return userDetails;

    }
    /**
     * get the current logged in user as entity
     *
     */
    public static User getCurrentUserAsEntity() {
        SessionUser userDetails =
            ( SessionUser ) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return new User( userDetails.getUserId(), 
            userDetails.getUsername(),
            userDetails.getPassword(),
            userDetails.getEmail() );
    }

}