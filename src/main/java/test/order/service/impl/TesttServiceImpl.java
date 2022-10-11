package test.order.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;
import test.order.dao.OrderDao;
import test.order.model.Order;
import test.order.service.TesttService;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 *
 * @author makejava
 * @since 2021-04-03 19:17:23
 */
@Service("testtServiceImpl")
public class TesttServiceImpl implements TesttService {
    @Resource
    private OrderDao orderDao;

    /**
     * 查询多条数据
     *
     * @return 对象列表
     */
    @Override
    public  List<Order> query() {
        List<Order> list= this.orderDao.selectall();

        System.out.println(1);
        return list;
    }

    /**
     * 新增数据
     *
     * @return 实例对象
     */
    @Override
    public void insert() {
        for (int i = 0; i < 10; i++) {
            Order order=new Order();
            order.setOrderid(i);
            order.setUserid(i);
            order.setGoods(new Random().nextInt(10));
            order.setMoney(i * 100.0);
            this.orderDao.insert(order);
        }
        System.out.println(1);
        //清空测试数据SQL备注
            /*
                delete from db_test_1.order_1;
                delete from db_test_1.order_2;
                delete from db_test_2.order_1;
                delete from db_test_2.order_2;
             */
    }


}
