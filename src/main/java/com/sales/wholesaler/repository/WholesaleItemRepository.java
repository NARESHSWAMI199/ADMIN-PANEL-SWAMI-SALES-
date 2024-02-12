package com.sales.wholesaler.repository;


import com.sales.entities.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface WholesaleItemRepository extends JpaRepository<Item, Integer> , JpaSpecificationExecutor {

   Item findItemBySlug(String slug);

   @Query(value = "select count(id) as count from Item")
   Integer totalItemCount();
   @Query(value = "select count(id) as count from Item where status=:status")
   Integer optionItemCount(@Param("status") String status);

}
