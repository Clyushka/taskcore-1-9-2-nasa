import java.text.*;
import java.util.Date;

public class NasaObject {
    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    private String copyright;
    private Date date;
    private String explanation;
    private String hdurl;
    private String media_type;
    private String service_version;
    private String title;
    private String url;

    public NasaObject() {
        //empty
    }

    public NasaObject(
            String copyright, Date date, String explanation, String hdurl,
            String media_type, String service_version, String title, String url) {
        this.copyright = copyright;
        this.date = date;
        this.explanation = explanation;
        this.hdurl = hdurl;
        this.media_type = media_type;
        this.service_version = service_version;
        this.title = title;
        this.url = url;
    }

    public NasaObject(
            String copyright, String date, String explanation, String hdurl,
            String media_type, String service_version, String title, String url)
            throws ParseException {
        this.copyright = copyright;
        this.date = DATE_FORMAT.parse(date);
        this.explanation = explanation;
        this.hdurl = hdurl;
        this.media_type = media_type;
        this.service_version = service_version;
        this.title = title;
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
    //copyright
    //"Ignacio Javier Diaz Bobillo"
    //
    //date
    //"2022-04-25"
    //
    //explanation
    //"In one of the brightest parts of Milky Way lies a nebula where some of the oddest things occur. NGC 3372, known as the Great Nebula in Carina, is home to massive stars and changing nebulas. The Keyhole Nebula (NGC 3324), the bright structure just below the image center, houses several of these massive stars. The entire Carina Nebula, captured here, spans over 300 light years and lies about 7,500 light-years away in the constellation of Carina.  Eta Carinae, the most energetic star in the nebula, was one of the brightest stars in the sky in the 1830s, but then faded dramatically. While Eta Carinae itself maybe on the verge of a supernova explosion, X-ray images indicate that much of the Great Nebula in Carina has been a veritable supernova factory."
    //
    //hdurl
    //"https://apod.nasa.gov/apod/image/2204/CarinaMosaic_Bobillo_1600.jpg"
    //
    //media_type
    //"image"
    //
    //service_version
    //"v1"
    //
    //title
    //"The Great Nebula in Carina"
    //
    //url
    //"https://apod.nasa.gov/apod/image/2204/CarinaMosaic_Bobillo_960.jpg"
}