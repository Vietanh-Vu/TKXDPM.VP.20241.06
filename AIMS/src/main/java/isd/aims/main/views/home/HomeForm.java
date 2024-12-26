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
import isd.aims.main.views.detail.MediaDetailForm;
import isd.aims.main.views.order.ListOrderForm;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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

public class HomeForm extends BaseForm implements Initializable {

    public static Logger LOGGER = Utils.getLogger(HomeForm.class.getName());

    @FXML
    private Label numMediaInCart;

    @FXML
    private ImageView aimsImage;

    @FXML
    private ImageView cartImage;

    @FXML
    private TextField txtSearch;

    @FXML
    private HBox hboxMedia;

    @FXML
    private SplitMenuButton splitMenuBtnSearch;

    @FXML
    private SplitMenuButton splitMenuBtnSort;

    @FXML
    private ImageView orderImage;

    @SuppressWarnings("rawtypes")
    private List homeItems;

    //Store the selected menu item
    private String selectedMenuItem = null;

    public HomeForm(Stage stage, String screenPath) throws IOException {
        super(stage, screenPath);
    }

    public Label getNumMediaCartLabel(){
        return this.numMediaInCart;
    }

    public HomeController getBController() {
        return (HomeController) super.getBController();
    }

    @Override
    public void show() {
        numMediaInCart.setText(String.valueOf(Cart.getCart().getListMedia().size()) + " media");
        super.show();
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        setBController(new HomeController());
        try{
            List medium = getBController().getAllMedia();
            this.homeItems = new ArrayList();
            for (Object object : medium) {
                Media media = (Media) object;
                MediaForm m1 = new MediaForm(Configs.HOME_MEDIA_PATH, media, this);
                //Handle view detail media
                m1.mediaImage.setOnMouseClicked(event -> {
                    try {
                        MediaDetailForm mediaDetailForm = new MediaDetailForm(this.stage, Configs.DETAIL_PATH, this, media);
                        mediaDetailForm.setHomeScreenHandler(this);
                        mediaDetailForm.requestToViewDetail(this);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                });

                this.homeItems.add(m1);
            }
        }catch (SQLException | IOException e){
            LOGGER.info("Errors occured: " + e.getMessage());
            e.printStackTrace();
        }


        aimsImage.setOnMouseClicked(e -> {
            addMediaHome(this.homeItems);
        });

        cartImage.setOnMouseClicked(e -> {
            try {
                LOGGER.info("User clicked to view cart");
                CartForm cartScreen = new CartForm(this.stage, Configs.CART_SCREEN_PATH);
                cartScreen.setHomeScreenHandler(this);
                cartScreen.setBController(new ViewCartController());
                cartScreen.requestToViewCart(this);
            } catch (IOException | SQLException e1) {
                throw new ViewCartException(Arrays.toString(e1.getStackTrace()).replaceAll(", ", "\n"));
            }
        });

        orderImage.setOnMouseClicked(e -> {
            try {
                LOGGER.info("User clicked to view orders");
                ListOrderForm listOrderForm = new ListOrderForm(this.stage, Configs.LIST_ORDER_PATH);
                listOrderForm.setHomeScreenHandler(this);
                listOrderForm.show();

            } catch (IOException e1) {
//                throw new ViewOrderException(Arrays.toString(e1.getStackTrace()).replaceAll(", ", "\n"));
            }
        });


        //Search by name + menu item
        txtSearch.setOnKeyReleased(event ->
                searchMedia(txtSearch.getText())
        );
//        splitMenuBtnSearch.setOnAction(event ->
//            searchMedia(txtSearch.getText())
//        );

        //Sort media by price
        splitMenuBtnSort.setOnAction(event ->
                sortMediaByPrice(splitMenuBtnSort.getText())
        );

        addMediaHome(this.homeItems);
        addMenuItem(0, "Book", splitMenuBtnSearch);
        addMenuItem(1, "DVD", splitMenuBtnSearch);
        addMenuItem(2, "CD", splitMenuBtnSearch);
        addMenuItem(3, "Category", splitMenuBtnSearch);

        addMenuItem(0, "Ascending", splitMenuBtnSort);
        addMenuItem(1, "Descending", splitMenuBtnSort);
        addMenuItem(2, "None", splitMenuBtnSort);
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

    @SuppressWarnings("rawtypes")
    public void addMediaHome(List items){
        ArrayList mediaItems = (ArrayList)((ArrayList) items).clone();
        hboxMedia.getChildren().forEach(node -> {
            VBox vBox = (VBox) node;
            vBox.getChildren().clear();
        });
        while(!mediaItems.isEmpty()){
            hboxMedia.getChildren().forEach(node -> {
                int vid = hboxMedia.getChildren().indexOf(node);
                VBox vBox = (VBox) node;
                while(vBox.getChildren().size()<3 && !mediaItems.isEmpty()){
                    MediaForm media = (MediaForm) mediaItems.get(0);
                    vBox.getChildren().add(media.getContent());
                    mediaItems.remove(media);
                }
            });
            return;
        }
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    private void addMenuItem(int position, String text, MenuButton menuButton){
        MenuItem menuItem = new MenuItem();
        Label label = new Label();
        label.prefWidthProperty().bind(menuButton.widthProperty().subtract(31));
        label.setText(text);
        label.setTextAlignment(TextAlignment.RIGHT);
        menuItem.setGraphic(label);
        menuItem.setOnAction(e -> {
            selectedMenuItem = text;
            menuButton.setText(text);

            // empty home media
            hboxMedia.getChildren().forEach(node -> {
                VBox vBox = (VBox) node;
                vBox.getChildren().clear();
            });

            // filter only media with the choosen category
            List filteredItems = new ArrayList<>();
            homeItems.forEach(me -> {
                MediaForm media = (MediaForm) me;
                if (media.getMedia().getTitle().toLowerCase().startsWith(text.toLowerCase())){
                    filteredItems.add(media);
                }
            });
            //If category is chosen
            if(filteredItems.isEmpty()) {
                addMediaHome(homeItems);
            } else {
                // fill out the home with filted media as category
                addMediaHome(filteredItems);
            }
        });
        menuButton.getItems().add(position, menuItem);
    }
    //Search media
    private void searchMedia(String keyword) {
        if(keyword == null || keyword.trim().isEmpty()) {
            addMediaHome(this.homeItems);
            return;
        }
        String lowerCaseKeyword = keyword.toLowerCase();
        List<MediaForm> filteredItems = new ArrayList<>();

        for(Object item : this.homeItems) {
            MediaForm media = (MediaForm) item;
            boolean matchingKeyword = media.getMedia().getTitle().toLowerCase().contains(lowerCaseKeyword);
            //If the selected menu item is null
            if(selectedMenuItem == null) {
                //Search by name
                if(matchingKeyword) {
                    filteredItems.add(media);
                }
            } else {
                //Search by category
                if(selectedMenuItem.equals("Category")) {
                    boolean matchingCategory = media.getMedia().getCategory().contains(lowerCaseKeyword);
                    if(matchingCategory) {
                        filteredItems.add(media);
                    }
                } else {
                    //Search by menu item
                    String lowerCaseSelectedMenuItem = selectedMenuItem.toLowerCase();
                    boolean matchingMenuItem =  media.getMedia().getTitle().toLowerCase().contains(lowerCaseSelectedMenuItem);
                    if (matchingKeyword && matchingMenuItem) {
                        filteredItems.add(media);
                    }
                }

            }
        }
        LOGGER.info("Search query: " + keyword + " - Found: " + filteredItems.size() + " items");
        addMediaHome(filteredItems);
    }
    //Sort media
    private void sortMediaByPrice(String order) {
        List<MediaForm> mediaForms = new ArrayList<>();
        for(Object item : this.homeItems) {
            mediaForms.add((MediaForm) item);
        }
        //Sort by price
        if(order.equals("Ascending")) {
            mediaForms.sort(Comparator.comparingDouble(mediaForm -> mediaForm.getMedia().getPrice()));
        } else if (order.equals("Descending")) {
            mediaForms.sort((mediaForm1, mediaForm2) -> Double.compare(mediaForm2.getMedia().getPrice(), mediaForm1.getMedia().getPrice()));
        } else {
            addMediaHome(this.homeItems);
        }
        // Update the UI
        addMediaHome(mediaForms);
    }
}
