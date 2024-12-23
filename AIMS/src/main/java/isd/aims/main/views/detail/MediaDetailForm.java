package isd.aims.main.views.detail;

import isd.aims.main.entity.media.Book;
import isd.aims.main.entity.media.CD;
import isd.aims.main.entity.media.DVD;
import isd.aims.main.exception.ViewCartException;
import isd.aims.main.controller.HomeController;
import isd.aims.main.controller.ViewCartController;
import isd.aims.main.entity.cart.Cart;
import isd.aims.main.entity.media.Media;
import isd.aims.main.utils.Configs;
import isd.aims.main.utils.Utils;
import isd.aims.main.views.BaseForm;
import isd.aims.main.views.cart.CartForm;
import isd.aims.main.views.home.HomeForm;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
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
    private Book book;
    private DVD dvd;
    private CD cd;


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
        System.out.println(media.getId());

        if(media.getTitle().toLowerCase().contains("book")) {
            //View detail books
            this.book = new Book().getMediaById(media.getId());
            try {
                BookForm bookForm = new BookForm(Configs.BOOK_DETAIL_PATH, media, book, homeForm);
                addBookDetail(bookForm);
            } catch (SQLException | IOException e) {
                LOGGER.info("Errors occured: " + e.getMessage());
                e.printStackTrace();
            }
        } else if(media.getTitle().toLowerCase().contains("dvd")) {
            //View detail dvds
            this.dvd = new DVD().getMediaById(media.getId());
            try {
                DVDForm dvdForm = new DVDForm(Configs.DVD_DETAIL_PATH, media, dvd, homeForm);
                addDVDDetail(dvdForm);
            } catch (SQLException | IOException e) {
                LOGGER.info("Errors occured: " + e.getMessage());
                e.printStackTrace();
            }
        } else if(media.getTitle().toLowerCase().contains("cd")) {
            //View detail cds
            this.cd = new CD().getMediaById(media.getId());
            try {
                CDForm cdForm = new CDForm(Configs.CD_DETAIL_PATH, media, cd, homeForm);
                addCDDetail(cdForm);
            } catch (SQLException | IOException e) {
                LOGGER.info("Errors occured: " + e.getMessage());
                e.printStackTrace();
            }
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
    public void addBookDetail(BookForm bookForm) {
        hboxMediaDetail.getChildren().forEach(node -> {
            VBox vBox = (VBox) node;
            vBox.getChildren().clear();

            vBox.getChildren().add(bookForm.getContent());
        });
    }
    public void addDVDDetail(DVDForm dvdForm) {
        hboxMediaDetail.getChildren().forEach(node -> {
            VBox vBox = (VBox) node;
            vBox.getChildren().clear();

            vBox.getChildren().add(dvdForm.getContent());
        });
    }
    public void addCDDetail(CDForm cdForm) {
        hboxMediaDetail.getChildren().forEach(node -> {
            VBox vBox = (VBox) node;
            vBox.getChildren().clear();

            vBox.getChildren().add(cdForm.getContent());
        });
    }
    public void requestToViewDetail(BaseForm prevScreen) throws SQLException {
        setPreviousScreen(prevScreen);
        setScreenTitle("Detail Media");
        show();
    }
}
