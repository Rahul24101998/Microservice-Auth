package com.demo.authentication_server.repository;

import com.demo.authentication_server.model.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo,Long> {

    Optional<UserInfo> findByUserName(String userName);

    Optional<UserInfo> findByEmail(String email);

    Optional<UserInfo> findByPhoneNumber(String phoneNumber);



}
