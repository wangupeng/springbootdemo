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
     * 新增角色
     * @param sysRole
     * @param moduleArr
     * @param functionArr
     * @return
     */
    @Transactional
    public int addRole(SysRole sysRole, String moduleArr, String functionArr){
        //取当前时间为角色ID
        sysRole.setRoleId(DateUtil.getShortSystemTime());

        //获取当前登录用户
        SysUser sysUser = (SysUser) SecurityUtils.getSubject().getSession().getAttribute("userSession");
        sysRole.setCreateUser(sysUser.getUserName());

        //增加角色
        sysRoleDao.addRole(sysRole);

        String[] moduleSplit = moduleArr.split("@@");
        for (int i = 0; i < moduleSplit.length; i++) {
            if (StringUtil.isNotNullOrEmpty(moduleSplit[i])) {
                SysRoleResource sysRoleResource = new SysRoleResource();
                sysRoleResource.setRoleId(sysRole.getRoleId());
                sysRoleResource.setResourceId(moduleSplit[i]);
                sysRoleResourceDao.addRoleResource (sysRoleResource);
            }
        }

        String[] functionSplit = functionArr.split("@@");
        for (int i = 0; i < functionSplit.length; i++) {
            if (StringUtil.isNotNullOrEmpty(functionSplit[i])) {
                SysRoleResource sysRoleResource = new SysRoleResource();
                sysRoleResource.setRoleId(sysRole.getRoleId());
                sysRoleResource.setResourceId(functionSplit[i]);
                sysRoleResourceDao.addRoleResource(sysRoleResource);
            }
        }
        return 1;
    }

    /**
     * 更新角色信息
     * @param sysRole
     * @return
     */
    @Transactional
    public int updateRole(SysRole sysRole,String moduleArr, String functionArr){
        //获取当前登录用户
        SysUser sysUser = (SysUser) SecurityUtils.getSubject().getSession().getAttribute("userSession");
        sysRole.setLastModifiedUser(sysUser.getUserName());
        sysRole.setLastModifiedDate(new Date());

        //更新角色信息
        int n1 = sysRoleDao.updateRole(sysRole);
        int n2 = 0,n3 = 0;
        if(n1>0){
            //更新角色成功，删除角色资源对应关系
            n2 = sysRoleResourceDao.deleteRoleResourceByRoleId(sysRole.getRoleId());

            //删除角色资源对应关系成功，增加新的角色资源对应关系
            String[] moduleSplit = moduleArr.split("@@");
            for (int i = 0; i < moduleSplit.length; i++) {
                if (StringUtil.isNotNullOrEmpty(moduleSplit[i])) {
                    SysRoleResource sysRoleResource = new SysRoleResource();
                    sysRoleResource.setRoleId(sysRole.getRoleId());
                    sysRoleResource.setResourceId(moduleSplit[i]);
                    sysRoleResourceDao.addRoleResource (sysRoleResource);
                }
            }

            String[] functionSplit = functionArr.split("@@");
            for (int i = 0; i < functionSplit.length; i++) {
                if (StringUtil.isNotNullOrEmpty(functionSplit[i])) {
                    SysRoleResource sysRoleResource = new SysRoleResource();
                    sysRoleResource.setRoleId(sysRole.getRoleId());
                    sysRoleResource.setResourceId(functionSplit[i]);
                    sysRoleResourceDao.addRoleResource(sysRoleResource);
                }
            }
        }
        return n2;
    }

    /**
     * 通过js来设置选中的模块和按钮
     * @param roleId
     * @return
     */
    public String checkedResource(String roleId) {
        List<SysResource> listResource = sysRoleResourceDao.listResourceByRoleId(roleId);

        StringBuilder sb = new StringBuilder();
        for (SysResource SysResource : listResource) {
            sb.append("$('#mod_" + SysResource.getResourceId() + "').prop('checked',true);\r\n");
        }

        return sb.toString();
    }

    /**
     * 删除角色
     * @param roleId
     * @return
     */
    @Transactional
    public int deleteRole(String roleId){
        //删除角色
        int n1 = sysRoleDao.deleteRole(roleId);
        int n2 = 0;
        if(n1>0){
            //删除角色成功，删除角色资源对应关系
            n2 = sysRoleResourceDao.deleteRoleResourceByRoleId(roleId);
        }
        return n2;
    }

    /**
     * 查询所有角色
     * @return
     */
    public List<SysRole> listRole(){
        List<SysRole> list = sysRoleDao.listRole();
        return list;
    }

    /**
     * 查询总数
     * @return
     */
    public int count(){
        int count = sysRoleDao.count();
        return count;
    }
    /**
     * 根据角色ID获取角色信息
     * @param roleId
     * @return
     */
    public SysRole getRole(String roleId){
        SysRole sysRole = sysRoleDao.getRole(roleId);
        return sysRole;
    }
}
