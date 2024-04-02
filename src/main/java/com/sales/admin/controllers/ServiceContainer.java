package com.sales.admin.controllers;

import com.sales.admin.services.AddressService;
import com.sales.admin.services.ItemService;
import com.sales.admin.services.StoreService;
import com.sales.admin.services.UserService;
import com.sales.utils.ReadExcel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class ServiceContainer {
    @Autowired
    protected StoreService storeService;

    @Autowired
    protected UserService userService;

    @Autowired
    protected ItemService itemService;

    @Autowired
    protected AddressService addressService;
    @Autowired
    Logger logger;

    @Autowired
    ReadExcel readExcel;


}
