package com.sales.admin.controllers;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("admin/dashboard")
public class AdminDashboardController extends ServiceContainer {


    @GetMapping("/")
    public ResponseEntity<Map<String, Object>> getAllDashboardCount() {
        Map responseObj = new HashMap();
        responseObj.put("users" , userService.getUserCounts());
        responseObj.put("retailers" , userService.getRetailersCounts());
        responseObj.put("wholesalers" , userService.getWholesalersCounts());
        responseObj.put("staffs" , userService.getStaffsCounts());
        responseObj.put("items",itemService.getItemCounts());
        responseObj.put("wholesales",storeService.getWholesaleCounts());
        responseObj.put("status", 200);
        return new ResponseEntity<>(responseObj, HttpStatus.valueOf((Integer) responseObj.get("status")));
    }
}
