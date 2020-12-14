package com.bright.bookstore.pojo.vo;

import com.bright.bookstore.pojo.Order;

import java.util.List;

/**
 * @author 徐亮亮
 * @since 2020/12/11
 */
public class OrderVO {
    private Integer status;
    private List<Order> orderList;
    private Integer total;

    public OrderVO() {
    }

    @Override
    public String toString() {
        return "OrderVO{" +
                "status=" + status +
                ", orderList=" + orderList +
                ", total=" + total +
                '}';
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }
}
