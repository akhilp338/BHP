package com.belhopat.backoffice.util;

import java.math.BigInteger;
import java.security.SecureRandom;

import org.springframework.stereotype.Component;

/**
 * @author BHP_DEV
 * Generates random password
 *
 */
@Component
public class RandomPasswordGenerator {
	  private SecureRandom random = new SecureRandom();

	  public String nextSessionId() {
	    return new BigInteger(50, random).toString(32);
	  }
}

