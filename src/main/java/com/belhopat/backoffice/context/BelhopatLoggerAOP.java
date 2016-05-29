package com.belhopat.backoffice.context;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import com.belhopat.backoffice.util.Constants;

@Aspect
public class BelhopatLoggerAOP {
	
    protected static final Logger LOGGER = Logger.getLogger( BelhopatLoggerAOP.class.getName() );

    @Around( "execution(* com.belhopat.backoffice.controller..*.*(..))" )
    public Object controllerAOP( ProceedingJoinPoint proceedingJoinPoint ) throws Throwable {
    	return loggerAOP( proceedingJoinPoint, Constants.WEBSERVICE_MODULE );
	}
    
    @Around( "execution(* com.belhopat.backoffice.service..*.*(..))" )
    public Object serviceAOP( ProceedingJoinPoint proceedingJoinPoint ) throws Throwable {
    	return loggerAOP( proceedingJoinPoint, Constants.SERVICE_MODULE );
	}
    
    @Around( "execution(* com.belhopat.backoffice.repository..*.*(..))" )
    public Object repositoryAOP( ProceedingJoinPoint proceedingJoinPoint ) throws Throwable {
    	return loggerAOP( proceedingJoinPoint, Constants.PERSISTENCE_MODULE );
	}
    
    public Object loggerAOP( ProceedingJoinPoint proceedingJoinPoint,
            String moduleName ) throws Throwable {
            Object output = null;
            String className = proceedingJoinPoint.getTarget().getClass().getSimpleName();
            String methodName = proceedingJoinPoint.getSignature().getName();

            LOGGER.info( "Starting " + moduleName + " : " + className + ", Method - " + methodName );
            try {
                output = proceedingJoinPoint.proceed();
                LOGGER.info( "Ending " + moduleName + " : " + className + ", Method - " + methodName );
            }
            catch ( Exception e ) {
                LOGGER.error( "Error Message - ", e );
                throw e;
            }

            return output;
        }
}
