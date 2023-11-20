package com.maker.register.service.feeuser.impl;

import com.maker.register.dao.feeuser.UserMapper;
import com.maker.register.model.FeeUser;
import com.maker.register.service.feeuser.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public List<FeeUser> getAllUser() throws Exception {
        return userMapper.selectAllUser();
    }
}
