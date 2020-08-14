package com.tgi.neverstop.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "branch", uniqueConstraints = { @UniqueConstraint(columnNames = { "branch_name" }) })
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Branch implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	private String id;

	@NotBlank
	@Size(min = 3, max = 25)
	@Column(name = "branch_name", nullable = false)
	private String branchName;

	@Column(name = "status")
	private int status;
	
	
	public Branch() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	

	

}