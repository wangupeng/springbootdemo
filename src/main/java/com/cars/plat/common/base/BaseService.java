package com.cars.plat.common.base;

import com.cars.plat.sys.model.SysUser;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * Created by wangyupeng on 2018/3/31 11:25
 */
public abstract class BaseService<T> {
    //获取当前登录用户
    //protected SysUser userSession = (SysUser) SecurityUtils.getSubject().getSession().getAttribute("userSession");

    @Autowired
    private BaseDao<T> baseDao;

    /**
     * 保存一个实体，null的属性不会保存，会使用数据库默认值
     * @param t
     * @return
     */
    public int insert(T t){
        return baseDao.insertSelective(t);
    }

    /**
     * 根据主键更新属性不为null的值
     * @param t
     * @return
     */
    public int update(T t){
        return baseDao.updateByPrimaryKeySelective(t);
    }

    /**
     * 根据实体属性作为条件进行删除，查询条件使用等号
     * @param t
     * @return
     */
    public int delete(T t){
        return baseDao.delete(t);
    }

    /**
     * 根据主键字段进行删除，方法参数必须包含完整的主键属性
     * @param key
     * @return
     */
    public int deleteByPrimaryKey(Object key){
        return baseDao.deleteByPrimaryKey(key);
    }

    /**
     * 查询全部结果
     * @return
     */
    public List<T> selectAll(){
        return baseDao.selectAll();
    }

    /**
     * 根据实体中的属性值进行查询，查询条件使用等号
     * @param t
     * @return
     */
    public List<T> select(T t){
        return baseDao.select(t);
    }
    /**
     * 根据主键查询
     * @param id
     * @return
     */
    public T selectById(Serializable id){
        return (T) baseDao.selectByPrimaryKey(id);
    }

    /**
     * 根据实体中的属性进行查询，只能有一个返回值，有多个结果是抛出异常，查询条件使用等号
     * @param t
     * @return
     */
    public T selectOne(T t){
        return (T) baseDao.selectOne(t);
    }

    /**
     * 根据实体中的属性查询总数，查询条件使用等号
     * @param t
     * @return
     */
    public int selectCount(T t){
        return baseDao.selectCount(t);
    }

    /**
     * 根据主键查询是否存在
     * @param key
     * @return
     */
    public boolean existsWithPrimaryKey(Object key){
        return baseDao.existsWithPrimaryKey(key);
    }

}
