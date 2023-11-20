package com.maker.register.dao.feeuser;

import com.maker.register.model.FeeUser;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

public interface UserMapper {

    public List<FeeUser> selectAllUser() throws Exception;
}
