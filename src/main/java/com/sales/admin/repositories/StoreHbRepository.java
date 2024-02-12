package com.sales.admin.repositories;

import com.sales.dto.StoreDto;
import com.sales.entities.User;
import com.sales.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

@Component
@Transactional
public class StoreHbRepository {

    @Autowired
    EntityManager entityManager;

    public int deleteStore(String slug){
        String hql = "Update Store set isDeleted='Y' where slug=:slug";
        Query query = entityManager.createQuery(hql);
        query.setParameter("slug",slug);
        return query.executeUpdate();
    }


    public int deleteStore(int userId){
        String sql = "Update store set is_deleted='Y' where user_id=:userId";
        Query query = entityManager.createNativeQuery(sql);
        query.setParameter("userId",userId);
        return query.executeUpdate();
    }




    public int updateStatus(String slug, String status){
        String hql = "Update Store set status=:status where slug=:slug";
        Query query = entityManager.createQuery(hql);
        query.setParameter("status",status);
        query.setParameter("slug",slug);
        return query.executeUpdate();
    }

    public int updateStore(StoreDto storeDto, User loggedUser){
        String strQuery = "update Store set " +
                "storeName=:name , " +
                "email=:email, "+
                "phone=:phone, "+
                "rating=:rating, "+
                "description=:description, "+
                "updatedAt=:updatedAt, "+
                "updatedBy=:updatedBy "+
                "where slug =:slug";

        Query query = entityManager.createQuery(strQuery);
        query.setParameter("name", storeDto.getStoreName());
        query.setParameter("email", storeDto.getStoreEmail());
        query.setParameter("phone", storeDto.getStorePhone());
        query.setParameter("rating", storeDto.getRating());
        query.setParameter("description", storeDto.getDescription());
        query.setParameter("updatedAt", Utils.getCurrentMillis());
        query.setParameter("updatedBy", loggedUser.getId());
        query.setParameter("slug", storeDto.getStoreSlug());
        return query.executeUpdate();
    }


}