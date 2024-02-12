package com.sales.wholesaler.controller;

import com.sales.admin.services.AddressService;
import com.sales.admin.services.StoreService;
import com.sales.wholesaler.services.WholesaleItemService;
import com.sales.wholesaler.services.WholesaleUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WholesaleServiceContainer {
    @Autowired
    protected StoreService storeService;

    @Autowired
    protected WholesaleUserService wholesaleUserService;

    @Autowired
    protected WholesaleItemService wholesaleItemService;

    @Autowired
    protected AddressService addressService;
}
