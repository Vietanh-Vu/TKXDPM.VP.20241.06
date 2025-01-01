package isd.aims.main.views.detail;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Logger;

import isd.aims.main.entity.media.CD;
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
public class CDForm extends FXMLForm {
    @FXML
    protected Label mediaArtist;

    @FXML
    protected Label mediaRecordLabel;

    @FXML
    protected Label mediaMusicType;

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

    private static Logger LOGGER = Utils.getLogger(CDForm.class.getName());
    private Media media;
    private CD cd;
    private HomeForm home;

    public CDForm(String screenPath, Media media, CD cd, HomeForm home) throws SQLException, IOException{
        super(screenPath);
        this.media = media;
        this.cd = cd;
        this.home = home;
        addToCartBtn.setOnMouseClicked(event -> {
            try {
                if (spinnerChangeNumber.getValue() > media.getCurrentQuantity()) throw new MediaNotAvailableException();
                Cart cart = Cart.getCart();
                // if media already in cart then we will increase the quantity instead of create the new cartMedia
                CartMedia mediaInCart = home.getBController().checkMediaInCart(media);
                if (mediaInCart != null) {
                    mediaInCart.setQuantity(mediaInCart.getQuantity() + spinnerChangeNumber.getValue());
                }else{
                    CartMedia cartMedia = new CartMedia(media, cart, spinnerChangeNumber.getValue(), media.getPrice());
                    cart.getListMedia().add(cartMedia);
                    LOGGER.info("Added " + cartMedia.getQuantity() + " " + media.getTitle() + " to cart");
                }

                // subtract the quantity and redisplay
                media.setQuantity(media.getCurrentQuantity() - spinnerChangeNumber.getValue());
                mediaAvail.setText(String.valueOf(media.getCurrentQuantity()));
                home.getNumMediaCartLabel().setText(String.valueOf(cart.getTotalMedia() + " media"));
                PopupForm.success("The media " + media.getTitle() + " added to Cart");

            } catch (MediaNotAvailableException exp) {
                try {
                    String message = "Not enough media:\nRequired: " + spinnerChangeNumber.getValue() + "\nAvail: " + media.getQuantity();
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
        setCDInfo(media.getId());

    }

    private void setCDInfo(int id) throws SQLException {
        // set the cover image of media
        File file = new File(Configs.IMAGE_PATH + media.getImageURL());
        Image image = new Image(file.toURI().toString());
        mediaImage.setImage(image);

        CD cd = this.cd.getMediaById(id);
        mediaArtist.setText(cd.getArtist());
        mediaRecordLabel.setText(cd.getRecordLabel());
        mediaMusicType.setText(cd.getMusicType());

        mediaTitle.setText(media.getTitle());
        mediaPrice.setText(Utils.getCurrencyFormat(media.getPrice()));
        mediaAvail.setText(Integer.toString(media.getCurrentQuantity()));
        spinnerChangeNumber.setValueFactory(
                new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 1)
        );

    }
}
