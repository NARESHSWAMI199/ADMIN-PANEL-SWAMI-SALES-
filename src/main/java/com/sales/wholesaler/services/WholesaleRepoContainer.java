package com.sales.wholesaler.services;


import com.sales.dto.SearchFilters;
import com.sales.wholesaler.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

@Component
public class WholesaleRepoContainer {

    @Autowired
    protected WholesaleStoreRepository wholesaleStoreRepository;
    @Autowired
    protected WholesaleStoreHbRepository wholesaleStoreHbRepository;
    @Autowired
    protected WholesaleUserRepository wholesaleUserRepository;
    @Autowired
    protected WholesaleUserHbRepository wholesaleUserHbRepository;
    @Autowired
    protected WholesaleItemRepository wholesaleItemRepository;
    @Autowired
    protected WholesaleItemHbRepository wholesaleItemHbRepository;
    @Autowired
    protected WholesaleAddressRepository wholesaleAddressRepository;
    @Autowired
    protected  WholesaleAddressHbRepository wholesaleAddressHbRepository;


    public Pageable getPageable(SearchFilters filters){
        System.err.println("page : "+ filters.getPageNumber() + " "+filters.getSize());
        Sort sort = (filters.getOrder().equalsIgnoreCase("asc")) ?
                Sort.by(filters.getOrderBy()).ascending() :  Sort.by(filters.getOrderBy()).descending();
        Pageable pageable = PageRequest.of(filters.getPageNumber(), filters.getSize(),sort);
        return pageable;
    }



}