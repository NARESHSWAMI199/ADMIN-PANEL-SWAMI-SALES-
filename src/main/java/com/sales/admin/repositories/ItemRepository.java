package com.sales.admin.repositories;


import com.sales.entities.Item;
import com.sales.entities.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository  extends JpaRepository<Item, Integer> , JpaSpecificationExecutor {



   @Query(" from Item where wholesale=:wholesaleId and createdAt >= :fromDate and createdAt  <= :toDate ")
   public List<Item> getAllItemsWithFilters(@Param("wholesaleId")Store wholesaleId,
                                            @Param("fromDate") Long fromDate,
                                            @Param("toDate") Long toDate);


   Item findItemBySlug(String slug);

   @Query(value = "select count(id) as count from Item")
   Integer totalItemCount();
   @Query(value = "select count(id) as count from Item where status=:status")
   Integer optionItemCount(@Param("status") String status);

}
