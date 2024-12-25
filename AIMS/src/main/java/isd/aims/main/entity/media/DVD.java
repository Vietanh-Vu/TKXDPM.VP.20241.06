package isd.aims.main.entity.media;

import isd.aims.main.entity.db.dao.dvd.DvdDAO;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class DVD extends Media {

    String discType;
    String director;
    int runtime;
    String studio;
    String subtitle;
    Date releasedDate;
    String filmType;

    public DVD() throws SQLException{

    }

    public DVD(int id, String title, String category, int price, int quantity, String type, String discType,
            String director, int runtime, String studio, String subtitle, Date releasedDate, String filmType) throws SQLException{
        super(id, title, category, price, quantity, type);
        this.discType = discType;
        this.director = director;
        this.runtime = runtime;
        this.studio = studio;
        this.subtitle = subtitle;
        this.releasedDate = releasedDate;
        this.filmType = filmType;
    }
    public DVD(int id, String title, String category, int price, int quantity, String type, String discType,
               String director, int runtime, String studio, String subtitle, String filmType) throws SQLException{
        super(id, title, category, price, quantity, type);
        this.discType = discType;
        this.director = director;
        this.runtime = runtime;
        this.studio = studio;
        this.subtitle = subtitle;
        this.filmType = filmType;
    }

    public String getDiscType() {
        return this.discType;
    }

    public DVD setDiscType(String discType) {
        this.discType = discType;
        return this;
    }

    public String getDirector() {
        return this.director;
    }

    public DVD setDirector(String director) {
        this.director = director;
        return this;
    }

    public int getRuntime() {
        return this.runtime;
    }

    public DVD setRuntime(int runtime) {
        this.runtime = runtime;
        return this;
    }

    public String getStudio() {
        return this.studio;
    }

    public DVD setStudio(String studio) {
        this.studio = studio;
        return this;
    }

    public String getSubtitle() {
        return this.subtitle;
    }

    public DVD setSubtitle(String subtitle) {
        this.subtitle = subtitle;
        return this;
    }

    public Date getReleasedDate() {
        return this.releasedDate;
    }

    public DVD setReleasedDate(Date releasedDate) {
        this.releasedDate = releasedDate;
        return this;
    }

    public String getFilmType() {
        return this.filmType;
    }

    public DVD setFilmType(String filmType) {
        this.filmType = filmType;
        return this;
    }

    @Override
    public String toString() {
        return "{" + super.toString() + " discType='" + discType + "'" + ", director='" + director + "'" + ", runtime='"
                + runtime + "'" + ", studio='" + studio + "'" + ", subtitles='" + subtitle + "'" + ", releasedDate='"
                + releasedDate + "'" + ", filmType='" + filmType + "'" + "}";
    }

    @Override
    public DVD getMediaById(int id) throws SQLException {
        return new DvdDAO().getById(id);
    }

    @Override
    public List getAllMedia() {
        return new DvdDAO().getAll();
    }
}
