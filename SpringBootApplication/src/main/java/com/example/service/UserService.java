package com.example.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.User;
import com.example.repository.UserRepository;


@Service
public class UserService {
	
	@Autowired
	
	private UserRepository userRepo;
	
	public String register(User user) {
		
		User u=userRepo.save(user);
		
		if(u!=null) {
			return "user register successfully";
		}
		else
			return "user not register";
		
	}
	
public User login(User user) {
	
	User login=userRepo.findByUsernameAndPassword(user.getUsername(), user.getPassword());
	
	return login;
		
	}

public User userEmail(User user) {

	User user1 = userRepo.findByEmailAndPassword(user.getEmail(), user.getPassword());
	return user1;
}

public List<User> userlist(User user){
	
	List<User> u=(List<User>)userRepo.findAll();
	List<User> list= new ArrayList<User>();
	if(u!=null) {
		for(User us:u) {
			if(us.getUsername().equals(user.getUsername())) {
				list.add(us);
			}
		}
	}
	return list;
}

public String saveUser(User user) {
	User us=userRepo.save(user);
	
	if(us!=null) {
		return "user saved suceesfully";
	}
	else
		return "user not saved suceesfully";
}

public User updateUser(User user) {
	Optional<User> Us = userRepo.findById(user.getUserid());
	User u = new User();
	if(null!= Us) {
		user.setUserid(Us.get().getUserid());
		u=userRepo.save(user);
	}
	return u;
}

public void deleteUser(int id) {
	userRepo.deleteById(id);;
}

public List<User> getAllUser() {
	List<User> user = (List<User>) userRepo.findAll();
	return user;
}

public Optional<User> getUser(int id) {
	Optional<User> us = userRepo.findById(id);
	return us;
}
	
}
