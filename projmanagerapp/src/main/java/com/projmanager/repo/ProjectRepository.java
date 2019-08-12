package com.projmanager.repo;
import org.springframework.data.repository.CrudRepository;
import com.projmanager.entity.Project;


public interface ProjectRepository extends CrudRepository<Project,Long>{
	
	

}
