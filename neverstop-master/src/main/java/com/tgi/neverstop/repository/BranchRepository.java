package com.tgi.neverstop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tgi.neverstop.model.Branch;

@Repository
public interface BranchRepository extends JpaRepository<Branch, String> 
{

	@Query("Select u from Branch u where u.branchName like %:branchName%")
	List<Branch> searchbyName(@Param("branchName") String branchName);
	
}