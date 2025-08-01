//package com.deepanshu.redisLocal.redisDB;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import java.util.Optional;
//
//@Component
//public class UserComponet {
//
//    @Autowired
//    private UserRepository userRepo;
//
//    // Save or update a user
//    public UserAuthData saveUser(UserAuthData user) {
//        // If ID is not set, generate a UUID
//        if (user.getId() == null || user.getId().isEmpty()) {
//            user.setId(java.util.UUID.randomUUID().toString());
//        }
//        return userRepo.save(user);
//    }
//
//    // Find user by ID
//    public Optional<UserAuthData> getUserById(String id) {
//        return userRepo.findById(id);
//    }
//
//    // Delete user by ID
//    public void deleteUserById(String id) {
//        userRepo.deleteById(id);
//    }
//
//    // Get all users
//    public Iterable<UserAuthData> getAllUsers() {
//        return userRepo.findAll();
//    }
//
//    // Find user by username (optional custom method if defined in repository)
//    // public Optional<UserAuthData> getUserByUsername(String username) {
//    //     return userRepo.findByUsername(username);
//    // }
//}