package com.projmanager.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name = "project_tb")
public class Project {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "project_id")
	private Long projectId;

	@Column(name = "project")
	private String project;
	
	@Column(name = "status")
	private String status;
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "priority")
	private String priority;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "start_dt")
	private Date startdt;
	@Temporal(TemporalType.DATE)
	@Column(name = "end_dt")
	private Date enddt;
	
	
	public String getTotalTask() {
		return totalTask;
	}

	public void setTotalTask(String totalTask) {
		this.totalTask = totalTask;
	}

	public String getCompletedTask() {
		return completedTask;
	}

	public void setCompletedTask(String completedTask) {
		this.completedTask = completedTask;
	}

	//total_task
	transient private String totalTask;
	//task_comp
	transient private String completedTask;
	
	transient private String userName;
	
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(name = "userId")
 private Long userId;

	public Long getUserId() {
	return userId;
}

public void setUserId(Long userId) {
	this.userId = userId;
}

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public Date getStartdt() {
		return startdt;
	}

	public void setStartdt(Date startdt) {
		this.startdt = startdt;
	}

	public Date getEnddt() {
		return enddt;
	}

	public void setEnddt(Date enddt) {
		this.enddt = enddt;
	}

	
}
