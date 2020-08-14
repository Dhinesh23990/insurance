package com.tgi.neverstop.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "agent", uniqueConstraints = { @UniqueConstraint(columnNames = { "agent_name" }) })
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Agent implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	private String id;

	@NotBlank
	@Size(min = 3, max = 25)
	@Column(name = "agent_name", nullable = false)
	private String agentNames;

	
	@NotBlank
	@Column(name = "branch_id", nullable = false)
	private String branchId;
	
	@NotBlank
	@Column(name = "salary", nullable = false)
	private String salary;
	
	@ManyToOne(optional = false)
    @JoinColumn(name = "branch_id", insertable = false, updatable = false)
    private Branch branch;
	
	public Agent() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAgentNames() {
		return agentNames;
	}

	public void setAgentNames(String agentNames) {
		this.agentNames = agentNames;
	}

	public String getBranchId() {
		return branchId;
	}

	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}

	public Branch getBranch() {
		return branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}

	public String getSalary() {
		return salary;
	}

	public void setSalary(String salary) {
		this.salary = salary;
	}

	
}