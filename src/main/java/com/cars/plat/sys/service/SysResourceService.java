package com.cars.plat.sys.service;

import com.cars.plat.sys.dao.SysResourceDao;
import com.cars.plat.sys.dao.SysRoleResourceDao;
import com.cars.plat.sys.model.SysResource;
import com.cars.plat.util.date.DateUtil;
import com.cars.plat.util.string.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wangyupeng on 2017/8/18.
 */
@Component
public class SysResourceService {
    @Autowired
    private SysResourceDao sysResourceDao;
    @Autowired
    private SysRoleResourceDao sysRoleResourceDao;

    /**
     * 查询资源
     * @return
     */
    public List<SysResource> listResource(SysResource sysResource){
        List<SysResource> list = sysResourceDao.listResource(sysResource);
        return list;
    }

    /**
     * 生成所有资源Ztree的树节点
     * @return
     */
    public List<Map<String,String>> createResourceZtree(SysResource sysResource) {
        List<SysResource> listResource = sysResourceDao.listResource(sysResource);
        List<Map<String,String>> list = new ArrayList<>();
        for (SysResource resource : listResource) {
            Map<String,String> map = new HashMap<>();
            map.put("id",resource.getResourceId());
            map.put("pid",resource.getParentId());
            map.put("name",resource.getResourceName());
            list.add(map);
        }
        return list;
    }

    /**
     * 根据ID类型查询资源
     * @param resourceId
     * @return
     */
    public SysResource getResourceById(String resourceId){
        SysResource sysResource = sysResourceDao.getResourceById(resourceId);
        return sysResource;
    }

    /**
     * 添加资源
     * @param sysResource
     * @return
     */
    @Transactional
    public int addResource(SysResource sysResource){
        sysResource.setResourceId(DateUtil.getShortSystemTime());
        int n = sysResourceDao.addResource(sysResource);
        return n;
    }

    /**
     * 更新资源
     * @param sysResource
     * @return
     */
    @Transactional
    public int updateResource(SysResource sysResource){
        int n = sysResourceDao.updateResource(sysResource);
        return n;
    }

    /**
     * 删除资源
     * @param resourceId
     * @return
     */
    @Transactional
    public int deleteResource(String resourceId){
        int n1 = sysResourceDao.deleteResource(resourceId);
        int n2 = 0;
        if(n1>0){
            //删除角色成功，删除角色资源对应关系
            n2 = sysRoleResourceDao.deleteRoleResourceResourceId(resourceId);
        }
        return n2;
    }

    /**
     * 查询用户拥有的资源
     * @param map
     * @return
     */
    public List<SysResource> loadUserResource(Map<String,Object> map){
        List<SysResource> resourceList = sysResourceDao.loadUserResource(map);
        return resourceList;
    }

    /**
     * 根据资源类型查询资源
     * @param resourceType
     * @return
     */
    public List<SysResource> listResourceByType(String resourceType){
        return sysResourceDao.listResourceByType(resourceType);
    }
}
