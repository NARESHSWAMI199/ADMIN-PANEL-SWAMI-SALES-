package com.sales.wholesaler.services;


import com.sales.dto.StoreDto;
import com.sales.dto.UserDto;
import com.sales.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class WholesaleUserService extends WholesaleRepoContainer {

    @Autowired
    WholesaleStoreService wholesaleStoreService;

    public User findByEmailAndPassword(Map<String,String> param) {
        String email = param.get("email");
        String password = param.get("password");
        return wholesaleUserRepository.findByEmailAndPassword(email,password);
    }

    public Map<String, Integer> getUserCounts() {
        Map<String, Integer> responseObj = new HashMap<>();
        responseObj.put("all", wholesaleUserRepository.totalUserCount());
        responseObj.put("active", wholesaleUserRepository.optionUserCount("A"));
        responseObj.put("deactive", wholesaleUserRepository.optionUserCount("D"));
        return responseObj;
    }


    public StoreDto userDtoToStoreDto(UserDto userDto) {
        StoreDto storeDto = new StoreDto();
        storeDto.setStoreName(userDto.getStoreName());
        storeDto.setStoreEmail(userDto.getStoreEmail());
        storeDto.setDescription(userDto.getDescription());
        storeDto.setCity(userDto.getCity());
        storeDto.setState(userDto.getState());
        storeDto.setStorePhone(userDto.getStorePhone());
        return storeDto;
    }


    @Transactional
    public Map<String, Object> registerUser(UserDto userDto) throws Exception {
        Map<String, Object> responseObj = new HashMap<>();
        StoreDto storeDto = null;
        User savedUser = createUser(userDto);
        System.out.println(userDto.getUserType() + " : " + userDto.getUserSlug());
        if (userDto.getUserType().equalsIgnoreCase("W")) {
            storeDto = userDtoToStoreDto(userDto);
            storeDto.setUserSlug(savedUser.getSlug());
            wholesaleStoreService.createOrUpdateStore(storeDto, savedUser);
        }
        if (savedUser.getId() > 0) {
            responseObj.put("res", savedUser);
            responseObj.put("message", "successfully inserted.");
            responseObj.put("status", 200);
        } else {
            responseObj.put("message", "nothing to insert. may be something went wrong");
            responseObj.put("status", 400);
        }
        return responseObj;
    }


    @Transactional
    public Map<String, Object> updateUserProfile(UserDto userDto, User loggedUser) throws Exception {
        Map<String, Object> responseObj = new HashMap<>();
        StoreDto storeDto = null;
        int isUpdated = updateUser(userDto, loggedUser);
        if (userDto.getUserType().equalsIgnoreCase("W")) {
            storeDto = userDtoToStoreDto(userDto);
            storeDto.setUserSlug(userDto.getSlug());
            wholesaleStoreService.createOrUpdateStore(storeDto, loggedUser);
        }
        if (isUpdated > 0) {
            responseObj.put("message", "successfully updated.");
            responseObj.put("status", 201);
        } else {
            responseObj.put("message", "nothing to updated. may be something went wrong");
            responseObj.put("status", 400);
        }
        return responseObj;
    }

    @Transactional
    public User createUser(UserDto userDto) {
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setSlug(UUID.randomUUID().toString());
        user.setPassword(userDto.getPassword());
        user.setContact(userDto.getContact());
        user.setEmail(userDto.getEmail());
        user.setUserType(userDto.getUserType());
        User savedUser = wholesaleUserRepository.save(user);
        /** this object also will save with the help of same transaction */
        savedUser.setCreatedBy(user.getId());
        savedUser.setUpdatedBy(user.getId());
        return savedUser;
    }

    @Transactional
    public int updateUser(UserDto userDto, User loggedUser) {
        return wholesaleUserHbRepository.updateUser(userDto, loggedUser);
    }

    public User getUserDetail(String slug) {
        return wholesaleUserRepository.findUserBySlug(slug);
    }


}
