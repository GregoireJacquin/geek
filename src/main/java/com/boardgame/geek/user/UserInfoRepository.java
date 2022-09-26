package com.boardgame.geek.user;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInfoRepository extends CrudRepository<UserInfo,Integer> {
    UserInfo findOneByEmail(String email);
}
