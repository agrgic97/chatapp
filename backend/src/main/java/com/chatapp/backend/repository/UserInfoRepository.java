package com.chatapp.backend.repository;

import com.chatapp.backend.model.UserInfo;
import org.springframework.data.mongodb.repository.ExistsQuery;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserInfoRepository extends MongoRepository<UserInfo, Integer> {
    Optional<UserInfo> findByName(String name);

    boolean existsByName(String name);
}
