package com.boardgame.geek.user;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserInfoRepository extends CrudRepository<UserInfo,Integer> {
    List<UserInfo> findByEmail(String email);
}
