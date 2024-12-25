package isd.aims.main.entity.db;

//import isd.aims.main.entity.db.dao.media.MediaDAO;
import isd.aims.main.entity.media.Media;
import isd.aims.main.utils.Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;
import java.util.logging.Logger;
// Functional Cohesion: các thuộc tính và phương thức quản lý kết nối cơ sở dữ liệu
public class SQLiteConnection  {

	private static Logger LOGGER = Utils.getLogger(Connection.class.getName());
	private static Connection connect;

    public static Connection getConnection() {
        if (connect != null) return connect;

        try {
			Class.forName("org.sqlite.JDBC");
            String connectionString = "jdbc:sqlite:src/main/resources/isd/aims/main/assets/db/aims.db";
            connect = DriverManager.getConnection(connectionString);
            LOGGER.info("Connect database successfully");
            return connect;
        } catch (Exception e) {
            LOGGER.info(e.getMessage());
            return connect;
        }
    }

    public static void main(String[] args) {

//        System.out.println("In tất cả media: ");
//        System.out.println("----------------------------------------------------");
//        List<Media> listMedia = new MediaDAO().getAll();
//        for (int i = 0; i < listMedia.size(); i++) {
//            System.out.println(listMedia.get(i).toString());
//        }
//        System.out.println("----------------------------------------------------");
//        System.out.println("");
//
//
//
//        System.out.println("In media có id là 57: ");
//        System.out.println("----------------------------------------------------");
//        Media media = new MediaDAO().getById(57);
//        System.out.println(media.toString());
//        System.out.println("----------------------------------------------------");
//        System.out.println("");



//        System.out.println("Tạo media mới: ");
//        System.out.println("----------------------------------------------------");
//        Media new_media = new Media("Sample Product", "Electronics", 450, 500, 20, "Gadget", "http://example.com/image.jpg");
//        new MediaDAO().add(new_media);
//        listMedia = new MediaDAO().getAll();
//        for (int i = 0; i < listMedia.size(); i++) {
//            System.out.println(listMedia.get(i).toString());
//        }
//        System.out.println("----------------------------------------------------");
//        System.out.println("");



//        System.out.println("Update media có id 57: ");
//        System.out.println("----------------------------------------------------");
//        Media updateMedia = new Media("book1268", "adventure", 36, 38, 20, "book", "images/book/book8.jpg)");
//        updateMedia.setId(57);
//        new MediaDAO().update(updateMedia);
//        System.out.println(new MediaDAO().getById(57).toString());
//        System.out.println("----------------------------------------------------");
//        System.out.println("");



//        System.out.println("Delete media mới có id là  ");
//        System.out.println("----------------------------------------------------");
//        new MediaDAO().delete(1000);
//        listMedia = new MediaDAO().getAll();
//        for (int i = 0; i < listMedia.size(); i++) {
//            System.out.println(listMedia.get(i).toString());
//        }
//        System.out.println("----------------------------------------------------");
//        System.out.println("");
    }
}
