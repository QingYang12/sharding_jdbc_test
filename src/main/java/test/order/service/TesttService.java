package test.order.service;

import test.order.model.Order;

import java.util.List;

/**
 * 客户收养宠物记录表(AdoptRecordHistory)表服务接口
 *
 * @author makejava
 * @since 2021-04-03 19:17:23
 */

public interface TesttService {

    /**
     * 查询多条数据
     * @return 对象列表
     */
    List<Order> query();

    /**
     * 新增数据
     * @return 实例对象
     */
    void insert();


}
