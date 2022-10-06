package com.boardgame.geek.service.repository;

import com.boardgame.geek.model.entity.UserInfo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInfoRepository extends CrudRepository<UserInfo,Integer> {
    UserInfo findOneByEmail(String email);
}
