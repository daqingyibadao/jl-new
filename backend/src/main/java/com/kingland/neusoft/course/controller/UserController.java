package com.kingland.neusoft.course.controller;

import com.kingland.neusoft.course.mapper.dao.UserModel;
import com.kingland.neusoft.course.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The user information related rest api controller
 *
 * @author KSC
 */
@RestController
public class UserController {

    private final UserService userService;

    /**
     * Initialize controller with user service bean
     *
     * @param userService service implementation bean
     */
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Api for creating user
     *
     * @param userModel creating user
     * @return created user record
     */
    @PostMapping("/users")
    public ResponseEntity addUser(@RequestBody UserModel userModel,HttpServletResponse response) {
    	response.setHeader("Access-Control-Allow-Origin","*");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, PATCH, DELETE, PUT");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");

        return ResponseEntity.ok(userService.addUser(userModel));
    }
    
//    @PostMapping("/users/update")
//    public ResponseEntity updateUser(@RequestBody UserModel userModel,HttpServletResponse response) {
//    	response.setHeader("Access-Control-Allow-Origin","*");
//        response.setHeader("Access-Control-Allow-Credentials", "true");
//        response.setHeader("Access-Control-Allow-Methods", "POST, GET, PATCH, DELETE, PUT");
//        response.setHeader("Access-Control-Max-Age", "3600");
//        response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
//
//        return ResponseEntity.ok(userService.updateUser(userModel));
//    }
    
    @PostMapping("/users/updatePass")
    public ResponseEntity updatePass(@RequestBody UserModel userModel,HttpServletResponse response) {
    	response.setHeader("Access-Control-Allow-Origin","*");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, PATCH, DELETE, PUT");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
        return ResponseEntity.ok(userService.updateUserPass(userModel));
    }
    
    @GetMapping("/login")
    public ResponseEntity login(@RequestParam("username") String username,@RequestParam("password") String password,HttpServletResponse response) {
    	response.setHeader("Access-Control-Allow-Origin","*");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, PATCH, DELETE, PUT");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");


    	boolean isHas = userService.isHasUserByName(username, password);
    	if(isHas) {
    		return ResponseEntity.ok(null);
    	}else {
    		return ResponseEntity.ok(null);
    	}
    	
    }
   

    /**
     * Api for counting all users exists in the system
     *
     * @return number of users exists in the system
     */
    @GetMapping("/users/count")
    public Map<String, Integer> countUser() {
        Integer userCount = userService.countUser();
        Map<String,Integer> count = new HashMap<String,Integer>();
        count.put("count", userCount);
        return count;
    }

    /**
     * Api get all users exists in the system
     *
     * @return all user data
     */
    @GetMapping("/users")
    public List<UserModel> getAllUser() {
        return userService.getAllUser();
    }


    /**
     * Api for counting all users exists in the system
     *
     * @return user id
     */
    @GetMapping("/users/{id}")
    public UserModel getUserById(@PathVariable("id") Long userId) {
        return userService.getUserById(userId);
    }

    /**
     * Api for deleting user by id
     *
     * @return id of deleted user
     */
    @DeleteMapping("/users/{id}")
    @PreAuthorize("hasAnyAuthority('role_admin')")
    public ResponseEntity deleteUser(@PathVariable("id") Long userId) {
        if (userService.deleteUser(userId) == 1) {
            // 204
            return ResponseEntity.noContent().build();
        } else {
            // 404
            return ResponseEntity.notFound().build();
        }
    }
}
