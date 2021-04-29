package com.easyiat.system.service.impl;

import com.easyiat.common.ienum.BaseNumber;
import com.easyiat.common.pojo.PageResult;
import com.easyiat.common.exception.BaseMessageEnum;
import com.easyiat.common.exception.BaseException;
import com.easyiat.system.entity.SysRole;
import com.easyiat.system.mapper.SysRoleMapper;
import com.easyiat.system.pojo.Role;
import com.easyiat.system.pojo.RoleAddReq;
import com.easyiat.system.pojo.RoleEditReq;
import com.easyiat.system.pojo.RoleReq;
import com.easyiat.system.service.SysRoleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description:
 * @author: changchun_wu
 * @version: 1.0
 * @blame: Test Team
 **/
@Slf4j
@Service
public class SysRoleServiceImpl implements SysRoleService {
    @Autowired
    private SysRoleMapper roleMapper;

    @Override
    public List<Role> getRoleListByUserId(Integer userId) {
        try {
            return roleMapper.getRoleListByUserId(userId);
        } catch (Exception e) {
            log.error("业务异常: ", e);
        }
        throw new BaseException(BaseMessageEnum.INTERNAL_SERVER_ERROR);
    }

    @Override
    public PageResult<SysRole> selectRoleListByRoleName(RoleReq roleReq) {
        try {
            PageHelper.startPage(roleReq.getPageInfo().getPageNum(), roleReq.getPageInfo().getPageSize());
            List<SysRole> roleList = roleMapper.selectRoleListByRoleName(roleReq.getRoleName());
            PageInfo<SysRole> pageInfo = new PageInfo<>(roleList);
            return new PageResult<>(pageInfo);
        } catch (Exception e) {
            log.error("业务异常: ", e);
            throw new BaseException(BaseMessageEnum.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public Boolean checkRoleKeyIsAvailable(String roleKey) {
        try {
            Integer count = roleMapper.selectRoleCountByRoleKey(roleKey);
            return count == BaseNumber.ZERO;
        } catch (Exception e) {
            log.error("业务异常: ", e);
            throw new BaseException(BaseMessageEnum.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public void insertRole(RoleAddReq roleAddReq) {
        try {
            roleMapper.insertRole(roleAddReq);
        } catch (Exception e) {
            log.error("业务异常: ", e);
            throw new BaseException(BaseMessageEnum.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public SysRole selectRoleByRoleId(Integer roleId) {
        try {
            return roleMapper.selectRoleByRoleId(roleId);
        } catch (Exception e) {
            log.error("业务异常: ", e);
            throw new BaseException(BaseMessageEnum.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public Integer updateRole(RoleEditReq roleEditReq) {
        try {
            return roleMapper.updateRole(roleEditReq);
        } catch (Exception e) {
            log.error("业务异常: ", e);
            throw new BaseException(BaseMessageEnum.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public SysRole selectRoleByRoleKey(String roleKey) {
        try {
            return roleMapper.selectRoleByRoleKey(roleKey);
        } catch (Exception e) {
            log.error("业务异常: ", e);
            throw new BaseException(BaseMessageEnum.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public Integer deleteRoleByRoleId(Integer roleId) {
        try {
            return roleMapper.deleteRoleByRoleId(roleId);
        } catch (Exception e) {
            log.error("业务异常: ", e);
            throw new BaseException(BaseMessageEnum.INTERNAL_SERVER_ERROR);
        }
    }
}
