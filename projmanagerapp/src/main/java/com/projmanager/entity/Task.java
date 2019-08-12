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
@Table(name = "task_tb")
public class Task {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long taskId;

	@Column(name = "task")
	String task;
	
	transient String parentTaskName;
	
	public String getParentTaskName() {
		return parentTaskName;
	}

	public void setParentTaskName(String parentTaskName) {
		this.parentTaskName = parentTaskName;
	}
	@Temporal(TemporalType.DATE)
	@Column(name = "start_dt")
	private Date startdt;
	@Temporal(TemporalType.DATE)
	@Column(name = "end_dt")
	private Date enddt;
	@Column(name = "project_id")
	private Long projectId;
	@Column(name = "parent_id")
	private Long parentId;
	
	@Column(name = "priority")
	private int priority;
	
	@Column(name = "status")
	private String status;
	
	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "parentFlag" ) String parentFlag="P";
	
	
	
	public String getParentFlag() {
		return parentFlag;
	}

	public void setParentFlag(String parentFlag) {
		this.parentFlag = parentFlag;
	}
	
	@Column(name = "userid" ) 
	private Long userid;

	public Long getUserid() {
	return userid;
}

public void setUserid(Long userid) {
	this.userid = userid;
}



	public Long getTaskId() {
		return taskId;
	}

	public void setTaskId(Long taskId) {
		this.taskId = taskId;
	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
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

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	
}
