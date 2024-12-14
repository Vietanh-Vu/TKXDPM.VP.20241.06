package isd.aims.main.entity.order;

import isd.aims.main.utils.Configs;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Order {

    private int shippingFees;
    private List lstOrderMedia;
    private HashMap<String, String> deliveryInfo;
    private Integer id;

    public Order(){
        this.lstOrderMedia = new ArrayList<>();
    }

    public Order(List lstOrderMedia) {
        this.lstOrderMedia = lstOrderMedia;
    }

    // Communicational Cohension: Đều quản lý OrderMedia, cùng input là OrderMedia với removeOrderMedia
    public void addOrderMedia(OrderMedia om){
        this.lstOrderMedia.add(om);
    }

    public void removeOrderMedia(OrderMedia om){
        this.lstOrderMedia.remove(om);
    }

    // Content Coupling: trả về danh sách có thể bị sửa đổi trực tiếp
    // => Cần trả về phiên bản không thể thay đổi và tạo phương thức riêng để chỉnh sửa
    public List getlstOrderMedia() {
        return this.lstOrderMedia;
    }

    // Content Coupling: cho phép thay đổi thuộc tính trực tiếp từ bên ngoài
    // => loại bỏ
    public void setlstOrderMedia(List lstOrderMedia) {
        this.lstOrderMedia = lstOrderMedia;
    }

    public void setShippingFees(int shippingFees) {
        this.shippingFees = shippingFees;
    }

    // Content Coupling: cho phép thay đổi thuộc tính trực tiếp từ bên ngoài
    // => Nên loại bỏ
    public int getShippingFees() {
        return shippingFees;
    }

    // Content Coupling: Có thể bị sửa đổi thông tin giao hàng do là dạng HashMap sẽ trả về địa chỉ ô nhớ.
    // => Chỉ nên trả về cặp key-value và tạo phương thức riêng để cập nhật
    public HashMap getDeliveryInfo() {
        return deliveryInfo;
    }

    // Content Coupling: cho phép thay đổi thuộc tính trực tiếp từ bên ngoài
    // => Nên loại bỏ
    public void setDeliveryInfo(HashMap deliveryInfo) {
        this.deliveryInfo = deliveryInfo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getAmount(){
        double amount = 0;
        for (Object object : lstOrderMedia) {
            OrderMedia om = (OrderMedia) object;
            amount += om.getPrice();
        }
        return (int) (amount + (Configs.PERCENT_VAT/100)*amount);
    }

}
