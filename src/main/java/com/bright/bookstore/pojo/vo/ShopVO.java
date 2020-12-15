package com.bright.bookstore.pojo.vo;

/**
 * @author 徐亮亮
 * @since 2020/12/15
 */
public class ShopVO {
    private Integer id;
    private String name;
    private String sendAddress;

    public ShopVO() {
    }

    public ShopVO(Integer id, String name, String sendAddress) {
        this.id = id;
        this.name = name;
        this.sendAddress = sendAddress;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSendAddress() {
        return sendAddress;
    }

    public void setSendAddress(String sendAddress) {
        this.sendAddress = sendAddress;
    }

    @Override
    public String toString() {
        return "ShopVO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sendAddress='" + sendAddress + '\'' +
                '}';
    }
}
