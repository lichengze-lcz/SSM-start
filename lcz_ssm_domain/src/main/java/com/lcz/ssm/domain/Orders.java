package com.lcz.ssm.domain;

import com.lcz.ssm.util.DateUtils;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

//订单实体类
public class Orders {


        private Integer id;
        private String orderNum;
        private Date orderTime;
        private String orderTimeStr;  //字符串形式
        private int orderStatus;      //订单状态 0 为支付 1...
        private String orderStatusStr;
        private int peopleCount;   //人数
        private Product product;  //产品
        private List<Traveller> travellers;    //List 类型
        private Member member;
        private Integer payType;  //支付方式
        private String payTypeStr;
        private String orderDesc;  //描述

    public String getOrderStatusStr() {

        if(orderStatus ==0){
            orderStatusStr="未支付";
        }else{
            orderStatusStr="已支付";
        }
        return orderStatusStr;
    }

    public void setOrderStatusStr(String orderStatusStr) {
        this.orderStatusStr = orderStatusStr;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    //时间字符串表现形式
    public String getOrderTimeStr() {

        if (orderTime != null){
            orderTimeStr = DateUtils.date2String(orderTime, "yyyy-MM-dd HH:mm");
        }
        return orderTimeStr;
    }

    public void setOrderTimeStr(String orderTimeStr) {
        this.orderTimeStr = orderTimeStr;
    }

    public int getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(int orderStatus) {
        this.orderStatus = orderStatus;
    }

    public int getPeopleCount() {
        return peopleCount;
    }

    public void setPeopleCount(int peopleCount) {
        this.peopleCount = peopleCount;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public List<Traveller> getTravellers() {
        return travellers;
    }

    public void setTravellers(List<Traveller> travellers) {
        this.travellers = travellers;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    public String getPayTypeStr() {
        // 0支付包  1微信
        if (payType == 0) {
            payTypeStr = "支付宝";
        } else if (payType == 1) {
            payTypeStr = "微信";
        } else if (payType == 1) {
            payTypeStr = "其他";
        }
            return payTypeStr;
        }

    public void setPayTypeStr(String payTypeStr) {
        this.payTypeStr = payTypeStr;
    }

    public String getOrderDesc() {
        return orderDesc;
    }

    public void setOrderDesc(String orderDesc) {
        this.orderDesc = orderDesc;
    }

}
