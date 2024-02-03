package com.alexpages.firstapp.repository;

import org.springframework.stereotype.Repository;

import com.alexpages.firstapp.entity.NoteEntity;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

@Repository
public interface NoteRepository extends CrudRepository<NoteEntity, Long>, QueryByExampleExecutor<NoteEntity>,
		PagingAndSortingRepository<NoteEntity, Long> {
	
	List<NoteEntity> findAll();

}
