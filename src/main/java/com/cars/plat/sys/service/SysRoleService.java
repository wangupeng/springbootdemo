package com.cars.plat.sys.service;

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
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by wangyupeng on 2017/8/18.
 */
@Component
public class SysRoleService {
    @Autowired
    private SysRoleDao sysRoleDao;
    @Autowired
    private SysRoleResourceDao sysRoleResourceDao;

    /**
     * 查询所有角色
     * @return
     */
    public List<SysRole> listRole(){
        List<SysRole> list = sysRoleDao.listRole();
        return list;
    }

    /**
     * 新增角色
     * @param sysRole
     * @return
     */
    @Transactional
    public int addRole(SysRole sysRole){
        //获取当前登录用户
        SysUser sysUser = (SysUser) SecurityUtils.getSubject().getSession().getAttribute("userSession");
        sysRole.setCreateUser(sysUser.getUserName());

        //增加角色
        sysRoleDao.addRole(sysRole);
        return 1;
    }

    /**
     * 根据角色ID获取角色信息
     * @param roleCode
     * @return
     */
    public SysRole getRoleByCode(String roleCode){
        SysRole sysRole = sysRoleDao.getRoleByCode(roleCode);
        return sysRole;
    }

    /**
     * 更新角色信息
     * @param sysRole
     * @return
     */
    @Transactional
    public int updateRole(SysRole sysRole){
        //获取当前登录用户
        SysUser sysUser = (SysUser) SecurityUtils.getSubject().getSession().getAttribute("userSession");
        sysRole.setLastModifiedUser(sysUser.getUserName());
        sysRole.setLastModifiedDate(new Date());

        return sysRoleDao.updateRole(sysRole);
    }

    /**
     * 删除角色
     * @param roleCode
     * @return
     */
    @Transactional
    public int deleteRole(String roleCode){
        //删除角色
        int n1 = sysRoleDao.deleteRole(roleCode);
        int n2 = 0;
        if(n1>0){
            //删除角色成功，删除角色资源对应关系
            n2 = sysRoleResourceDao.deleteRoleResourceByRoleCode(roleCode);
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
        int delNum = sysRoleResourceDao.deleteRoleResourceByRoleCode((String)map.get("roleCode"));
        int addNum = 0;
        if(delNum>=0&&map.get("resourceIds")!=null){
            addNum = sysRoleResourceDao.addRoleResource (map);
        }
        return addNum;
    }

    /**
     * 根据角色ID获取资源
     * @param roleCode
     * @return
     */
    public List<SysResource> listResourceByRoleCode(String roleCode){
        return sysRoleResourceDao.listResourceByRoleCode(roleCode);
    }
}
