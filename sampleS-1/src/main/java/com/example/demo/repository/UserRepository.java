package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.User;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * ユーザー情報 Repository
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	public Page<User> findAll(Pageable pageable);
	
	public Page<User> findAlla(String statusCode,Pageable pageable);
	@Query(value = "select * from user where  address like %:statusCode% ;", nativeQuery = true)
	Page<User> findByStatusCode(@Param("statusCode") String statusCode, Pageable pageable);
	
}