package test.order.model;

import java.io.Serializable;

/**
 * order_1
 * @author 
 */
public class Order implements Serializable {
    private Integer orderid;

    private Integer userid;

    private Integer goods;

    private Double money;

    private static final long serialVersionUID = 1L;

    public Integer getOrderid() {
        return orderid;
    }

    public void setOrderid(Integer orderid) {
        this.orderid = orderid;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getGoods() {
        return goods;
    }

    public void setGoods(Integer goods) {
        this.goods = goods;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
}