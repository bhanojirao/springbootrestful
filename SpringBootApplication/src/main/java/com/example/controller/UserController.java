package com.example.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.User;
import com.example.service.UserService;

@RestController
@RequestMapping(path="/user")

public class UserController {
	
	@Autowired
	
	private UserService userService;
	
	@RequestMapping(value="/register", method = RequestMethod.POST)
	
	public ResponseEntity <String> register(@RequestBody User user){
		
		String status;
		
		try {
			status=userService.register(user);
			return new ResponseEntity <String>(status, HttpStatus.OK);
		}
		
		catch(Exception e) {
			status="user not found";
			return new ResponseEntity <String>(status, HttpStatus.NOT_FOUND);
		}
		
	}
	
	@RequestMapping(value="/login", method = RequestMethod.POST)
	public ResponseEntity <String> login(@RequestBody User user){
		
		User u=userService.userEmail(user);
		
		if(u!=null)
			return new ResponseEntity <String>("user login successfully", HttpStatus.OK);
		else
			return new ResponseEntity <String>("user fails to login", HttpStatus.NOT_FOUND);	
	}
	
	@RequestMapping(value="/userlist", method = RequestMethod.POST)
	
	public List<User> userlist(@RequestBody User user){
		List<User> u= userService.userlist(user);
		return u;
	}
	
	@RequestMapping(value="/add", method = RequestMethod.POST)
	
	public ResponseEntity <String> addUser(@RequestBody User user) {
		
		String status;
		
		try {
			status=userService.saveUser(user);
			return new ResponseEntity <String>(status, HttpStatus.OK);
		}
		catch(Exception e) {
			status="user not added";
			return new ResponseEntity <String>(status, HttpStatus.NOT_FOUND);
		}
		
	}
	
@RequestMapping(value = "/edit", method = RequestMethod.PUT)
	
	public User updateUser(@RequestBody User user) {
		User result = userService.updateUser(user);
		return result;
	}
    
	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	
	public String deleteUser( int id) {
		
		String status;
		try {
			userService.deleteUser(id);
			status = "User deleted successfully!...:" + id;
		} catch (Exception e) {
			status = e.getMessage();
		}
		
		return status;
	}
	
	@RequestMapping(value = "/getAll", method = RequestMethod.GET)
	public List<User> getAllUser() {
		List<User> user = userService.getAllUser();
		return user;
	}
	@RequestMapping(value = "/get", method = RequestMethod.GET)
	public Optional<User> getUser(@RequestParam int id) {
		return userService.getUser(id);
	}
	
}
