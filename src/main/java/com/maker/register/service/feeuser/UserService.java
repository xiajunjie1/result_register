package com.maker.register.service.feeuser;

import com.maker.register.model.FeeUser;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

public interface UserService {
    @Cacheable(cacheNames = "xia:register:feeuser")
    public List<FeeUser> getAllUser()throws Exception;
}
