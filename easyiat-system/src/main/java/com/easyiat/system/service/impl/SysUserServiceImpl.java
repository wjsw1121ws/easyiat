package com.easyiat.system.service.impl;

import com.easyiat.common.ienum.BaseNumber;
import com.easyiat.common.exception.BaseMessageEnum;
import com.easyiat.common.exception.BaseException;
import com.easyiat.system.mapper.SysUserMapper;
import com.easyiat.system.pojo.RegisterUserReq;
import com.easyiat.system.pojo.User;
import com.easyiat.system.service.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author: changchun_wu
 * @version: 1.0
 * @blame: Test Team
 **/

@Slf4j
@Service
public class SysUserServiceImpl implements SysUserService {


    @Autowired
    private SysUserMapper userMapper;

    @Override
    public User loadUserByUsername(String username) {
        try {
            return userMapper.loadUserByUsername(username);
        } catch (Exception e) {
            log.info("业务异常: ", e);
        }
        throw new BaseException(BaseMessageEnum.INTERNAL_SERVER_ERROR);
    }

    @Override
    public Boolean checkUsernameIsAvailable(String username) {
        try {
            Integer count = userMapper.selectUserCountByUsername(username);
            return count == BaseNumber.ZERO;
        } catch (Exception e) {
            log.error("业务异常: ", e);
            throw new BaseException(BaseMessageEnum.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public void registerUser(RegisterUserReq registerUserReq) {
        try {
            userMapper.registerUser(registerUserReq);
        } catch (Exception e) {
            log.error("业务异常: ", e);
            throw new BaseException(BaseMessageEnum.INTERNAL_SERVER_ERROR);
        }
    }
}
