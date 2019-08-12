package com.projmanager.repo;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.projmanager.entity.Task;

public interface TaskRepository extends CrudRepository<Task,Long>{
	

	
	@Query(value = "select * from  task_tb t where t.project_id=?1", nativeQuery = true)
	List<Task> findByName(Long name);
	
	String query="SELECT t.task,pt.parent_task,t.priority,t.start_dt,t.end_dt FROM proj_manager_schema.task_tb t LEFT JOIN proj_manager_schema.parent_task_tb pt ON t.parent_id=pt.parent_task_id";

	
	@Query(value = query, nativeQuery = true)
	List<Task> findByProjId();
	
}
