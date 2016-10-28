package com.belhopat.backoffice.repository;

import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.belhopat.backoffice.model.S3BucketFile;

/**
 * @author BHP_DEV Data repository for Attendance entity
 *
 */
@Repository
public interface S3BucketFileRepository
		extends JpaRepository<S3BucketFile, Long>, DataTablesRepository<S3BucketFile, Long> {

	@Query("select fileName from S3BucketFile where bucketName = :bucketName and userId = :userId "
			+ "and fileType = :fileType and contentType = :contentType")
	String getLatestFileName(@Param("bucketName") String bucketName, @Param("userId") String userId,
			@Param("fileType") String fileType, @Param("contentType") String contentType);

}
