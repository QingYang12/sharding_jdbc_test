package test.order.dao;

import org.apache.ibatis.annotations.Mapper;
import test.order.model.Order;

import java.util.List;
@Mapper
public interface OrderDao {

    int insert(Order record);

    List<Order> selectall();
}