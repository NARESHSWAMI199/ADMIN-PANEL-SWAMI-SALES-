package com.sales.wholesaler.repository;

import com.sales.entities.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface WholesaleStoreRepository extends JpaRepository<Store, Integer> {

    Store findStoreBySlug(String slug);

    Store findStoreByUserId(int userId);

    @Query(value = "select count(id) as count from Store")
    Integer totalWholesaleCount();
    @Query(value = "select count(id) as count from Store where status=:status")
    Integer optionWholesaleCount(@Param("status") String status);
}
