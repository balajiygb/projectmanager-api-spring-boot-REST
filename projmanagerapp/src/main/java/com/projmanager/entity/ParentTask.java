package com.projmanager.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "parent_task_tb")
public class ParentTask {
	
	public ParentTask(String parentTask) {
		super();
		this.parentTask = parentTask;
	}
	
	public ParentTask() {
		
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long parentTaskId =101L;

	@Column(name = "parent_task")
	String parentTask;

	public Long getParentTaskId() {
		return parentTaskId;
	}

	public void setParentTaskId(Long parentTaskId) {
		this.parentTaskId = parentTaskId;
	}

	public String getParentTask() {
		return parentTask;
	}

	public void setParentTask(String parentTask) {
		this.parentTask = parentTask;
	}
	

	

}
