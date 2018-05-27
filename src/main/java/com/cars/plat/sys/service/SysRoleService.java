package com.cars.plat.sys.service;

import com.cars.plat.common.base.BaseService;
import com.cars.plat.sys.dao.SysRoleDao;
import com.cars.plat.sys.dao.SysRoleResourceDao;
import com.cars.plat.sys.model.SysResource;
import com.cars.plat.sys.model.SysRole;
import com.cars.plat.sys.model.SysRoleResource;
import com.cars.plat.sys.model.SysUser;
import com.cars.plat.util.date.DateUtil;
import com.cars.plat.util.string.StringUtil;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by wangyupeng on 2017/8/18.
 */
@Service
public class SysRoleService extends BaseService<SysRole> {
    @Autowired
    private SysRoleDao sysRoleDao;
    @Autowired
    private SysRoleResourceDao sysRoleResourceDao;

    /**
     * 删除角色
     * @param roleCode
     * @return
     */
    @Transactional
    public int delete(String roleCode){
        //删除角色
        int n1 = sysRoleDao.deleteByPrimaryKey(roleCode);
        int n2 = 0;
        if(n1>0){
            //删除角色成功，删除角色资源对应关系
            n2 = sysRoleResourceDao.deleteByPrimaryKey(roleCode);
        }
        return n2;
    }

    /**
     * 授权
     * @param map
     * @return
     */
    @Transactional
    public int addRoleResource(Map<String, Object> map){
        int delNum = sysRoleResourceDao.deleteByPrimaryKey((String)map.get("roleCode"));
        int addNum = 0;
        if(delNum>=0&&map.get("resourceIds")!=null){
            addNum = sysRoleResourceDao.add (map);
        }
        return addNum;
    }

    /**
     * 根据角色ID获取资源
     * @param roleCode
     * @return
     */
    public List<SysResource> listResourceByRoleCode(String roleCode){
        return sysRoleResourceDao.listByRoleCode(roleCode);
    }
}
