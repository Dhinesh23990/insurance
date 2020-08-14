package com.tgi.neverstop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tgi.neverstop.model.Agent;

@Repository
public interface AgentRepository extends JpaRepository<Agent, String> {

	List<Agent> findByAgentId(String agentId);

	@Query("Select u from Agent u where u.agentName like %:agentName%")
	List<Agent> searchbyName(@Param("agentName") String agentName);

}