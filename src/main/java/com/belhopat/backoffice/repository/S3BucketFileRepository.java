package com.belhopat.backoffice.repository;

import java.util.List;

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

	@Query("select fileName from S3BucketFile where fileType = :fileType "
			+ "and contentType = :contentType and fileEntityId =:fileEntityId")
	String getLatestFileName(@Param("fileType") String fileType, @Param("contentType") String contentType,
			@Param("fileEntityId") Long fileEntityId);

	S3BucketFile findById(Long s3BucketFileId);

	List<S3BucketFile> findByFileTypeAndFileEntityId(String reimburseFile, Long reimburseId);

	List<S3BucketFile> findByUserId(String string);

}
