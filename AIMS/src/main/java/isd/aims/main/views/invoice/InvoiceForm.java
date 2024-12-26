package isd.aims.main.views.invoice;

import isd.aims.main.controller.payment.PaymentController;
import isd.aims.main.entity.deliveryinfo.DeliveryInfo;
import isd.aims.main.entity.invoice.Invoice;
import isd.aims.main.entity.order.Order;
import isd.aims.main.entity.order.OrderMedia;
import isd.aims.main.entity.payment.PaymentType;
import isd.aims.main.exception.MediaNotAvailableException;
import isd.aims.main.exception.PaymentException;
import isd.aims.main.exception.ProcessInvoiceException;
import isd.aims.main.utils.Configs;
import isd.aims.main.utils.Utils;
import isd.aims.main.views.BaseForm;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.logging.Logger;

public class InvoiceForm extends BaseForm {

    private static Logger LOGGER = Utils.getLogger(InvoiceForm.class.getName());

    @FXML
    private Label pageTitle;

    @FXML
    private Label name;

    @FXML
    private Label phone;

    @FXML
    private Label province;

    @FXML
    private Label address;

    @FXML
    private Label instructions;

    @FXML
    private Label subtotal;

    @FXML
    private Label shippingFees;

    @FXML
    private Label total;

    @FXML
    private VBox vboxItems;

    @FXML
    private Button btnConfirm;

    @FXML
    private ChoiceBox paymentType;

    private Invoice invoice;

    public InvoiceForm(Stage stage, String screenPath, Invoice invoice) throws IOException {
        super(stage, screenPath);
        this.invoice = invoice;
        setInvoiceInfo();
        paymentType.getItems().addAll(PaymentType.VNPay);
        btnConfirm.setOnMouseClicked(e -> {
            LOGGER.info("Pay Order button clicked");
            try {
                requestToPayOrder();

            } catch (IOException | SQLException exp) {
                LOGGER.severe("Cannot pay the order, see the logs");
                exp.printStackTrace();
                throw new PaymentException(Arrays.toString(exp.getStackTrace()).replaceAll(", ", "\n"));
            }

        });
    }

    @SuppressWarnings("unchecked")
    private void setInvoiceInfo(){
        DeliveryInfo deliveryInfo = invoice.getOrder().getDeliveryInfo();
        name.setText(deliveryInfo.getName());
        province.setText(deliveryInfo.getProvince());
        instructions.setText("This is the basic instructions");
        address.setText(deliveryInfo.getAddress());
        subtotal.setText(Utils.getCurrencyFormat(invoice.getOrder().getAmount()));
        shippingFees.setText(Utils.getCurrencyFormat(invoice.getOrder().getShippingFees()));
        int amount = invoice.getOrder().getAmount() + invoice.getOrder().getShippingFees();
        total.setText(Utils.getCurrencyFormat(amount));
        invoice.setAmount(amount);
        invoice.getOrder().getLstOrderMedia().forEach(orderMedia -> {
            try {
                MediaInvoiceForm mis = new MediaInvoiceForm(Configs.INVOICE_MEDIA_SCREEN_PATH);
                mis.setOrderMedia((OrderMedia) orderMedia);
                vboxItems.getChildren().add(mis.getContent());
            } catch (IOException | SQLException e) {
                System.err.println("errors: " + e.getMessage());
                throw new ProcessInvoiceException(e.getMessage());
            }
        });

    }

    public void requestToPayOrder() throws SQLException, IOException {
        try {
            String type = paymentType.getValue().toString();
            Order order = invoice.getOrder();
            order.setPaymentType(type);
            invoice.setOrder(order);

            PaymentController paymentController = new PaymentController(PaymentType.valueOf(type), invoice);
            paymentController.payment();
            this.stage.close();
        } catch (MediaNotAvailableException e) {

        }
    }
}
