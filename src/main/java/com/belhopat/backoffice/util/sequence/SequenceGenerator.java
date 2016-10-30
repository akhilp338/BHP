package com.belhopat.backoffice.util.sequence;

import java.util.Date;

import com.belhopat.backoffice.util.DateUtil;

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
    public static String generateEmployeeId( Long increment, String division ) {

    	String div = division.substring(0, 1);
        String yy = DateUtil.getYearYY(new Date());
        String mm = DateUtil.getYMonthMM(new Date());
        String candidateId = div + yy + mm;
        Long sequence = getSequenceNumber( increment );
        candidateId = candidateId + sequence;
        return candidateId;

    }
    
    /**
     * @param increment
     * @return Client-ID
     * Generates sequence for listing of Clients
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
    
    /**
     * @param increment
     * @return Employee-ID
     * Generates sequence for listing of Employees
     */
    public static String generateReimburseId( Long increment) {

        String yy = DateUtil.getYearYY(new Date());
        String mm = DateUtil.getYMonthMM(new Date());
        String reimburseId = "REIM" + yy + mm;
        Long sequence = getSequenceNumber( increment );
        reimburseId = reimburseId + sequence;
        return reimburseId;

    }
    
    /**
     * @param increment
     * @return Vendor-ID
     * Generates sequence for listing of Vendors
     */
    public static String generateVendorId( Long increment ) {
        String vendorId = "BHP-V-";
        Long sequence = getSequenceNumber( increment );
        vendorId = vendorId + sequence;
        return vendorId;

    }

    /**
     * @param increment
     * @return Consultant-ID
     * Generates sequence for listing of Consultants
     */
    public static String generateConsultantId( Long increment ) {
        String consultantId = "BHP-CONS-";
        Long sequence = getSequenceNumber( increment );
        consultantId = consultantId + sequence;
        return consultantId;

    }
    
}