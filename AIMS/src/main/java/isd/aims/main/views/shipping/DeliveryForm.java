package isd.aims.main.views.shipping;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.ResourceBundle;

import isd.aims.main.controller.placeorder.ordervalidator.RushInfoValidator;
import isd.aims.main.controller.placeorder.ordervalidator.StandardInfoValidator;
import isd.aims.main.controller.placeorder.shippingfee.RushShippingFee;
import isd.aims.main.controller.placeorder.shippingfee.StandardShippingFee;
import isd.aims.main.entity.deliveryinfo.DeliveryInfo;
import isd.aims.main.exception.InvalidDeliveryInfoException;
import isd.aims.main.controller.placeorder.PlaceOrderController;
import isd.aims.main.entity.invoice.Invoice;
import isd.aims.main.entity.order.Order;
import isd.aims.main.utils.Configs;
import isd.aims.main.views.BaseForm;
import isd.aims.main.views.cart.CartForm;
import isd.aims.main.views.invoice.InvoiceForm;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class DeliveryForm extends BaseForm implements Initializable {

	@FXML
	private Label screenTitle;

	@FXML
	private TextField name;

	@FXML
	private TextField phone;

	@FXML
	private TextField address;

	@FXML
	private TextField instructions;

	@FXML
	private TextField email;

	@FXML
	private ComboBox<String> province;

	@FXML
	private CheckBox rush;

	private Order order;
	
	private PlaceOrderController placeOrderController;

	public DeliveryForm(Stage stage, String screenPath, Order order) throws IOException {
		super(stage, screenPath);
		this.order = order;
		this.placeOrderController = new PlaceOrderController(new StandardShippingFee(), new StandardInfoValidator());
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		final BooleanProperty firstTime = new SimpleBooleanProperty(true); // Variable to store the focus on stage load
		name.focusedProperty().addListener((observable,  oldValue,  newValue) -> {
            if(newValue && firstTime.get()){
                content.requestFocus(); // Delegate the focus to container
                firstTime.setValue(false); // Variable value changed for future references
            }
        });
		this.province.getItems().addAll(Configs.PROVINCES);
	}

	@FXML
	void submitDeliveryInfo(MouseEvent event) throws IOException, InterruptedException, SQLException {

		DeliveryInfo deliveryInfo = new DeliveryInfo(name.getText(),phone.getText(),email.getText(),address.getText(),province.getValue());
		System.out.println(deliveryInfo.toString());

		if (rush.isSelected()) {
			placeOrderController.setRushController();
		} else {
			placeOrderController.setStandardController();
		}
		try {
			// process and validate delivery info
			placeOrderController.processDeliveryInfo(deliveryInfo);
		} catch (InvalidDeliveryInfoException e) {
			System.out.println(e.getMessage());
			throw new InvalidDeliveryInfoException(e.getMessage());
		}
		order.setDeliveryInfo(deliveryInfo);
		// calculate shipping fees
		int shippingFees = placeOrderController.calculateShippingFee(order);
		System.out.println(shippingFees);
		order.setShippingFees(shippingFees);

		// create invoice screen
		Invoice invoice = placeOrderController.createInvoice(order);
		System.out.print(invoice.toString());
		BaseForm InvoiceScreenHandler = new InvoiceForm(this.stage, Configs.INVOICE_SCREEN_PATH, invoice);
		InvoiceScreenHandler.setPreviousScreen(this);
		InvoiceScreenHandler.setHomeScreenHandler(homeScreenHandler);
		InvoiceScreenHandler.setScreenTitle("Invoice Screen");
		InvoiceScreenHandler.setBController(placeOrderController);
		InvoiceScreenHandler.show();
	}


	public void notifyError(){
		// TODO: implement later on if we need
	}

}
