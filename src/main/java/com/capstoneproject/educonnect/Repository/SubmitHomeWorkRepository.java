package com.capstoneproject.educonnect.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.capstoneproject.educonnect.Entity.Submit;

@Repository
public interface SubmitHomeWorkRepository extends JpaRepository<Submit, Integer> {
	@Modifying	
	@Query("Update Submit set score = :newScore , status = 'Y'  where submitid = :submitid ")
	int updateScoresubmit(@Param("newScore")float score, @Param("submitid")int submitid);
}
