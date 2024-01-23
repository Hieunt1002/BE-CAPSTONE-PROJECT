package com.capstoneproject.educonnect.Repository.Impl;

import org.springframework.stereotype.Repository;

import com.capstoneproject.educonnect.DTO.ViewFileDTO;
import com.capstoneproject.educonnect.Entity.QFiles;
import com.capstoneproject.educonnect.Repository.ViewFileRepository;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class ViewFileRepositoryIpmI implements ViewFileRepository {

	private JPAQueryFactory jpaQueryFactory;
	
	private final QFiles File = QFiles.files1;
	
	public ViewFileRepositoryIpmI(JPAQueryFactory jpaQueryFactory) {
		this.jpaQueryFactory = jpaQueryFactory;
	}

	@Override
	public ViewFileDTO ViewFile(int fileId) {
		ViewFileDTO result = jpaQueryFactory
				.select(Projections.constructor(ViewFileDTO.class, File.fileId, File.exerciseid, File.nameFile, File.files, File.status))
				.distinct()
				.from(File)
				.where(File.fileId.eq(fileId))
				.fetchOne();
		return result;
	}

}
