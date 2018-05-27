package com.cars.plat.util.page;

import com.cars.plat.util.global.GlobalConst;

import java.io.Serializable;

/**
 * Created by wangyupeng on 2017/8/18.
 */
public class Page implements Serializable{
    private int pageNum = 1;//当前页(传入的数据)
    private int pageSize = GlobalConst.PAGESIZE;//每页显示条数
    public Page() {
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }
}
