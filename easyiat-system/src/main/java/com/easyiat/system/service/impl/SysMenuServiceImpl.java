package com.easyiat.system.service.impl;

import com.easyiat.common.exception.BaseMessageEnum;
import com.easyiat.common.exception.BaseException;
import com.easyiat.system.mapper.SysMenuMapper;
import com.easyiat.system.service.SysMenuService;
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
public class SysMenuServiceImpl implements SysMenuService {
    @Autowired
    private SysMenuMapper menuMapper;

    @Override
    public List<String> selectUrlListByMenuType(List<String> menuTypeList) {
        try {
            return menuMapper.selectUrlListByMenuType(menuTypeList);
        } catch (Exception e) {
            log.error("业务异常: ", e);
        }
        throw new BaseException(BaseMessageEnum.INTERNAL_SERVER_ERROR);
    }

    @Override
    public List<String> queryRoleKeyByUrl(String url) {
        try {
            return menuMapper.queryRoleKeyByUrl(url);
        } catch (Exception e) {
            log.error("业务异常: ", e);
        }
        throw new BaseException(BaseMessageEnum.INTERNAL_SERVER_ERROR);
    }
}
