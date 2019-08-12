package com.projmanager.repo;
import org.springframework.data.repository.CrudRepository;

import com.projmanager.entity.User;

public interface UserRepository extends CrudRepository<User,Long>{
	


}
