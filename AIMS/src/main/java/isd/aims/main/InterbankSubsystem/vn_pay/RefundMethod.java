package isd.aims.main.InterbankSubsystem.vn_pay;

import isd.aims.main.entity.db.dao.Media.MediaDAO;
import isd.aims.main.entity.db.dao.order.OrderDAO;
import isd.aims.main.entity.db.dao.order_media.OrderMediaDAO;
import isd.aims.main.entity.order.OrderMedia;

import java.util.List;

public class RefundMethod {

    // Thực hiện xử lý giao dịch sau khi đã hoàn tiền thành công
    public void refundTransationCompleted(int orderId){
        //
        List<OrderMedia> orderMedias = new OrderMediaDAO().getByOrderId(orderId);
        System.out.println(orderMedias);



        for (OrderMedia orderMedia : orderMedias){
            // Cập nhật lại quantity cho orderMedia
            System.out.println(orderMedia.getQuantity());
            boolean update = new MediaDAO().updateBeforeRefund(orderMedia.getMedia().getId(), orderMedia.getQuantity());
            System.out.println(update);

            // Xóa orderMedia tương ứng
            boolean delete = new OrderMediaDAO().delete(orderMedia.getMedia().getId(), orderId);
            System.out.println(delete);
        }

        // Xóa Order tương ứng
        System.out.println("----------");
        boolean delete = new OrderDAO().delete(orderId);
        System.out.println(delete);


    }

}
