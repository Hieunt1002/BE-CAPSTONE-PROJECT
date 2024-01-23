package com.capstoneproject.educonnect.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.capstoneproject.educonnect.DTO.FilesDTO;
import com.capstoneproject.educonnect.Entity.Files;

@Repository
public interface FilesRepository extends JpaRepository<Files, Integer> {

}
