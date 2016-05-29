package com.belhopat.backoffice.util.sequence;

/**
 * @author BHP_DEV
 * Generates sequence
 *
 */
public class SequenceGenerator {
	
	private static final Long BASE_GENERATOR = 1000L; 

    /**
     * @param increment
     * @return Candidate-ID
     * Generates sequence for listing of Candidates
     */
    public static String generateCandidateId( Long increment ) {

        String candidateId = "BHP-C-";
        Long sequence = getSequenceNumber( increment );
        candidateId = candidateId + sequence;
        return candidateId;

    }
    
    /**
     * @param increment
     * @return Employee-ID
     * Generates sequence for listing of Employees
     */
    public static String generateEmployeeId( Long increment ) {

        String candidateId = "BHP-E-";
        Long sequence = getSequenceNumber( increment );
        candidateId = candidateId + sequence;
        return candidateId;

    }
    
    /**
     * @param increment
     * @return Client-ID
     * Generates sequence for listing of Employees
     */
    public static String generateClientId( Long increment ) {

        String clientId = "BHP-CL-";
        Long sequence = getSequenceNumber( increment );
        clientId = clientId + sequence;
        return clientId;

    }
    
    /**
     * @param increment
     * @return Base-Sequence-No
     * Base sequence generator
     */
    private static Long getSequenceNumber( Long increment ) {
        Long sequence = BASE_GENERATOR + increment;
        return sequence;
    }

}