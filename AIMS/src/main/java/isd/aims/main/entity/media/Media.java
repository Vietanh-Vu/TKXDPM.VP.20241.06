package isd.aims.main.entity.media;

import isd.aims.main.entity.db.SQLiteConnection;
import isd.aims.main.entity.db.dao.Media.MediaDAO;
import isd.aims.main.utils.Utils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * The general media class, for another media it can be done by inheriting this class
 * @author nguyenlm
 */
public class Media {

    private static Logger LOGGER = Utils.getLogger(Media.class.getName());

    protected Statement stm;
    protected int id;
    protected String title;
    protected String category;
    protected int value; // the real price of product (eg: 450)
    protected int price; // the price which will be displayed on browser (eg: 500)
    protected int quantity;
    protected String type;
    protected String imageURL;
    protected boolean isRush;
    protected float weight;

    public Media() throws SQLException{
        stm = SQLiteConnection.getConnection().createStatement();
    }

    public Media (int id, String title, String category, int price, int quantity, String type) throws SQLException{
        this.id = id;
        this.title = title;
        this.category = category;
        this.price = price;
        this.quantity = quantity;
        this.type = type;

        //stm = DBConnection.getConnection().createStatement();
    }

    public int getQuantity() throws SQLException{
        int updated_quantity = getMediaById(id).quantity;
        this.quantity = updated_quantity;
        return updated_quantity;
    }

    public int getCurrentQuantity() {
        return this.quantity;
    }

    public Media getMediaById(int id) throws SQLException{
        return  MediaDAO.getInstance().getById(id);
    }

    public List getAllMedia() throws SQLException{
        return MediaDAO.getInstance().getAll();
    }


    // getter and setter
    public int getId() {
        return this.id;
    }

    public Media setId(int id){
        this.id = id;
        return this;
    }

    public String getTitle() {
        return this.title;
    }

    public Media setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getCategory() {
        return this.category;
    }

    public Media setCategory(String category) {
        this.category = category;
        return this;
    }

    public int getValue() {
        return this.value;
    }

    public Media setValue(int value) {
        this.value = value;
        return this;
    }

    public int getPrice() {
        return this.price;
    }

    public Media setPrice(int price) {
        this.price = price;
        return this;
    }

    public String getImageURL(){
        return this.imageURL;
    }

    public Media setImageURL(String url){
        this.imageURL = url;
        return this;
    }

    public Media setQuantity(int quantity) {
        this.quantity = quantity;
        return this;
    }

    public String getType() {
        return this.type;
    }

    public Media setType(String type) {
        this.type = type;
        return this;
    }

    public boolean isRush() {
        return isRush;
    }

    public Media setIsRush(boolean isRush) {
        this.isRush = isRush;
        return this;
    }

    public float getWeight() {
        return weight;
    }

    public Media setWeight(float weight) {
        this.weight = weight;
        return this;
    }

    @Override
    public String toString() {
        return "{" +
                " id='" + id + "'" +
                ", title='" + title + "'" +
                ", category='" + category + "'" +
                ", price='" + price + "'" +
                ", quantity='" + quantity + "'" +
                ", type='" + type + "'" +
                ", rush='" + isRush + "'" +
                ", weight='" + weight + "'" +
                ", imageURL='" + imageURL + "'" +
                "}";
    }

}