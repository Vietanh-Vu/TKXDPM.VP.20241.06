package isd.aims.main.views.detail;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Logger;

import isd.aims.main.entity.media.DVD;
import isd.aims.main.exception.MediaNotAvailableException;
import isd.aims.main.entity.cart.Cart;
import isd.aims.main.entity.cart.CartMedia;
import isd.aims.main.entity.media.Media;
import isd.aims.main.utils.Configs;
import isd.aims.main.utils.Utils;
import isd.aims.main.views.FXMLForm;
import isd.aims.main.views.home.HomeForm;
import isd.aims.main.views.popup.PopupForm;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
public class DVDForm extends FXMLForm {
    @FXML
    protected Label mediaDiscType;

    @FXML
    protected Label mediaDirector;

    @FXML
    protected Label mediaRuntime;

    @FXML
    protected Label mediaStudio;

    @FXML
    protected Label mediaSubtitles;

    @FXML
    protected Label mediaFilmType;

    @FXML
    protected ImageView mediaImage;

    @FXML
    protected Label mediaTitle;

    @FXML
    protected Label mediaPrice;

    @FXML
    protected Label mediaAvail;

    @FXML
    protected Spinner<Integer> spinnerChangeNumber;

    @FXML
    protected Button addToCartBtn;

    private static Logger LOGGER = Utils.getLogger(DVDForm.class.getName());
    private Media media;
    private DVD dvd;
    private HomeForm home;

    private int updateQuantity;
    public DVDForm(String screenPath, Media media, DVD dvd, HomeForm home) throws SQLException, IOException{
        super(screenPath);
        this.media = media;
        this.dvd = dvd;
        this.home = home;
        //Check remaining quantity after buying media in Home Screen
        CartMedia checkMediaInCart = home.getBController().checkMediaInCart(media);
        if(checkMediaInCart != null) {
            updateQuantity = media.getQuantity() - checkMediaInCart.getQuantity();
        } else {
            updateQuantity = media.getQuantity();
        }
        addToCartBtn.setOnMouseClicked(event -> {
            try {
                if (spinnerChangeNumber.getValue() > updateQuantity) throw new MediaNotAvailableException();
                Cart cart = Cart.getCart();
                // if media already in cart then we will increase the quantity instead of create the new cartMedia
                CartMedia mediaInCart = home.getBController().checkMediaInCart(media);
                if (mediaInCart != null) {
                    mediaInCart.setQuantity(mediaInCart.getQuantity() + spinnerChangeNumber.getValue());
                    updateQuantity = media.getQuantity() - mediaInCart.getQuantity();
                }else{
                    CartMedia cartMedia = new CartMedia(media, cart, spinnerChangeNumber.getValue(), media.getPrice());
                    cart.getListMedia().add(cartMedia);
                    LOGGER.info("Added " + cartMedia.getQuantity() + " " + media.getTitle() + " to cart");
                    updateQuantity = media.getQuantity() - spinnerChangeNumber.getValue();
                }
                // subtract the quantity and redisplay
                home.getNumMediaCartLabel().setText(String.valueOf(cart.getTotalMedia() + " media"));
                PopupForm.success("The media " + media.getTitle() + " added to Cart");
            } catch (MediaNotAvailableException exp) {
                try {
                    String message = "Not enough media:\nRequired: " + spinnerChangeNumber.getValue() + "\nAvail: " + updateQuantity;
                    LOGGER.severe(message);
                    PopupForm.error(message);
                } catch (Exception e) {
                    LOGGER.severe("Cannot add media to cart: ");
                }

            } catch (Exception exp) {
                LOGGER.severe("Cannot add media to cart: ");
                exp.printStackTrace();
            }
        });
        setDVDInfo(media.getId());

    }

    private void setDVDInfo(int id) throws SQLException {
        // set the cover image of media
        File file = new File(Configs.IMAGE_PATH + media.getImageURL());
        Image image = new Image(file.toURI().toString());
        mediaImage.setImage(image);

        DVD dvd = this.dvd.getMediaById(id);
        mediaDiscType.setText(dvd.getDiscType());
        mediaDirector.setText(dvd.getDirector());
        mediaRuntime.setText(Integer.toString(dvd.getRuntime()));
        mediaStudio.setText(dvd.getStudio());
        mediaSubtitles.setText(dvd.getSubtitle());
        mediaFilmType.setText(dvd.getFilmType());

        mediaTitle.setText(media.getTitle());
        mediaPrice.setText(Utils.getCurrencyFormat(media.getPrice()));
        mediaAvail.setText(Integer.toString(media.getQuantity()));
        spinnerChangeNumber.setValueFactory(
                new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 1)
        );

    }
}
