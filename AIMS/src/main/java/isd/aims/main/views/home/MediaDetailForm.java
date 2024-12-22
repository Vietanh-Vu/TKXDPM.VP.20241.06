package isd.aims.main.views.home;

import isd.aims.main.exception.ViewCartException;
import isd.aims.main.controller.HomeController;
import isd.aims.main.controller.ViewCartController;
import isd.aims.main.entity.cart.Cart;
import isd.aims.main.entity.media.Media;
import isd.aims.main.utils.Configs;
import isd.aims.main.utils.Utils;
import isd.aims.main.views.BaseForm;
import isd.aims.main.views.cart.CartForm;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.*;
import java.util.logging.Logger;

public class MediaDetailForm extends BaseForm {
    @FXML
    private Label numMediaInCart;

    @FXML
    private ImageView aimsImage;

    @FXML
    private ImageView cartImage;

    @FXML
    private HBox hboxMediaDetail;

    private Media media;

    private static Logger LOGGER = Utils.getLogger(MediaDetailForm.class.getName());


    public HomeController getBController() {
        return (HomeController) super.getBController();
    }

    @Override
    public void show() {
        numMediaInCart.setText(String.valueOf(Cart.getCart().getListMedia().size()) + " media");
        super.show();
    }

    public MediaDetailForm(Stage stage, String screenPath, HomeForm homeForm, Media media) throws IOException, SQLException {
        super(stage, screenPath);
        this.media = media;


        try {
            MediaForm mediaForm = new MediaForm(Configs.HOME_MEDIA_PATH, media, homeForm);
            addMediaDetail(mediaForm);
        } catch (SQLException | IOException e) {
            LOGGER.info("Errors occured: " + e.getMessage());
            e.printStackTrace();
        }

        setImage();
        this.show();
        // on mouse clicked, we back to home
        aimsImage.setOnMouseClicked(e -> {
            homeScreenHandler.show();
        });

        cartImage.setOnMouseClicked(e -> {
            try {
                LOGGER.info("User clicked to view cart");
                CartForm cartScreen = new CartForm(this.stage, Configs.CART_SCREEN_PATH);
                cartScreen.setHomeScreenHandler(homeForm);
                cartScreen.setBController(new ViewCartController());
                cartScreen.requestToViewCart(this);
            } catch (IOException | SQLException e1) {
                throw new ViewCartException(Arrays.toString(e1.getStackTrace()).replaceAll(", ", "\n"));
            }
        });
    }

    public void setImage() {
        // fix image path caused by fxml
        File file1 = new File(Configs.IMAGE_PATH_ICON + "/" + "Logo.png");
        Image img1 = new Image(file1.toURI().toString());
        aimsImage.setImage(img1);

        File file2 = new File(Configs.IMAGE_PATH_ICON + "/" + "cart.png");
        Image img2 = new Image(file2.toURI().toString());
        cartImage.setImage(img2);
    }
    public void addMediaDetail(MediaForm mediaForm) {
        hboxMediaDetail.getChildren().forEach(node -> {
            VBox vBox = (VBox) node;
            vBox.getChildren().clear();

            vBox.getChildren().add(mediaForm.getContent());
        });

    }
    public void requetsToViewDetail(BaseForm prevScreen) throws SQLException {
        setPreviousScreen(prevScreen);
        setScreenTitle("Detail Media");
        show();
    }
}
