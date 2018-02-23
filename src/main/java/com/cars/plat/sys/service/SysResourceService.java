package com.cars.plat.sys.service;

import com.cars.plat.sys.dao.SysResourceDao;
import com.cars.plat.sys.dao.SysRoleResourceDao;
import com.cars.plat.sys.model.SysResource;
import com.cars.plat.util.date.DateUtil;
import com.cars.plat.util.string.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

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
     * 查询所有资源
     * @return
     */
    public List<SysResource> listResource(){
        List<SysResource> list = sysResourceDao.listResource();
        return list;
    }

    /**
     * 生成Ztree的树节点,新增模块时使用，只有模块上下级
     * @return
     */
    public String createZtreeByModule() {
        List<SysResource> listModule = sysResourceDao.listResourceByType("1");// 模块列表
        StringBuilder sb = new StringBuilder();
        for (SysResource sysModule : listModule) {
            if (sysModule.getParentId().equals("0")) {
                String parentId = sysModule.getResourceId();
                sb.append("{id : \"" + sysModule.getResourceId() + "\",pId :\"" + sysModule.getParentId() + "\",name :\"" + sysModule.getResourceName() + "\",open : false");
                sb.append("},");
                for (SysResource sysModule2 : listModule) {
                    if (sysModule2.getParentId().equals(parentId)&&!sysModule2.getParentId().equals("0")) {
                        sb.append("{id : \"" + sysModule2.getResourceId() + "\",pId :\"" + sysModule2.getParentId() + "\",name :\"" + sysModule2.getResourceName() + "\",open : false");
                        sb.append("},");
                    }
                }
            }
        }
        return StringUtil.subTract(sb.toString());
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
}
