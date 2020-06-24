package com.example.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer>{
	
	
	User findByUsernameAndPassword(String username, String password);
	User findByEmailAndPassword(String email, String password);
	
	Optional<User> findById(int id);

}
