package arthur;

import backend.classes.Film;
import backend.classes.Sitz;
import backend.classes.Vorführung;
import backend.classes.Werbung;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class FilmTest {
    ArrayList<Sitz> TestSitze = new ArrayList<Sitz>();
    ArrayList<Werbung> TestWerbung = new ArrayList<Werbung>();
    private ArrayList<String> genres =new ArrayList<String>();
    private ArrayList<String> darsteller =new ArrayList<String>(  );
    private ArrayList<String> regie = new ArrayList<String>(  );

    Vorführung TestVorfuehrung = new Vorführung("1","7",90, 5L, 110L, "15:00", true, TestWerbung, TestSitze);
    ArrayList<Vorführung> Vorfuehrung = new ArrayList<>(  );
    Film filmtest1 = new Film(123,"filmName","theLink","Beschreibung",150,16,5,genres,darsteller,regie,Vorfuehrung,"link");
    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void getFilmID() {
        Assert.assertEquals(123,filmtest1.getFilmID());
    }

    @Test
    public void setFilmID() {
        filmtest1.setFilmID( 222 );
        Assert.assertEquals( 222,filmtest1.getFilmID() );
    }

    @Test
    public void getFsk() {
        Assert.assertEquals( 16,filmtest1.getFsk() );
    }

    @Test
    public void setFsk() {
        filmtest1.setFsk( 18 );
        Assert.assertEquals( 18,filmtest1.getFsk() );
    }

    @Test
    public void getDauer() {
        Assert.assertEquals( 150,filmtest1.getDauer() );
    }

    @Test
    public void setDauer() {
        filmtest1.setDauer( 200 );
        Assert.assertEquals( 200,filmtest1.getDauer() );
    }

    @Test
    public void getBewertung() {
        Assert.assertEquals( 5,filmtest1.getBewertung() );
    }

    @Test
    public void setBewertung() {
        filmtest1.setBewertung( 8 );
        Assert.assertEquals( 8,filmtest1.getBewertung() );
    }

    @Test
    public void getTitel() {
        Assert.assertEquals( "filmName",filmtest1.getTitel() );
    }

    @Test
    public void setTitel() {
        filmtest1.setTitel( "title" );
        Assert.assertEquals( "title",filmtest1.getTitel() );
    }

    @Test
    public void getBeschreibung() {
        Assert.assertEquals( "Beschreibung",filmtest1.getBeschreibung() );
    }

    @Test
    public void setBeschreibung() {
        filmtest1.setBeschreibung( "good" );
        Assert.assertEquals( "good",filmtest1.getBeschreibung() );
    }

    @Test
    public void getBildLink() {
        Assert.assertEquals( "theLink",filmtest1.getBildLink() );

    }

    @Test
    public void setBildLink() {
        filmtest1.setLink( "newLink" );
        Assert.assertEquals( "newLink",filmtest1.getLink() );
    }

    @Test
    public void getGenres() {
        Assert.assertEquals( genres,filmtest1.getGenres() );
    }

    @Test
    public void setGenres() {
         ArrayList<String> genres2 =new ArrayList<String>();
         filmtest1.setGenres( genres2 );
         Assert.assertEquals( genres2,filmtest1.getGenres() );
    }

    @Test
    public void getDarsteller() {
        Assert.assertEquals( darsteller,filmtest1.getDarsteller() );
    }

    @Test
    public void setDarsteller() {
         ArrayList<String> darsteller2 =new ArrayList<String>(  );
        filmtest1.setDarsteller( darsteller2 );
        Assert.assertEquals( darsteller2,filmtest1.getDarsteller() );

    }

    @Test
    public void getRegie() {
        Assert.assertEquals( regie,filmtest1.getRegie() );
    }

    @Test
    public void setRegie() {
         ArrayList<String> regie2 = new ArrayList<String>(  );
         filmtest1.setRegie( regie2 );
         Assert.assertEquals( regie2,filmtest1.getRegie() );
    }

    @Test
    public void getVorführungen() {
        Assert.assertEquals( Vorfuehrung,filmtest1.getVorführungen() );
    }

    @Test
    public void setVorführungen() {
        ArrayList<Vorführung> Vorfuehrung2 = new ArrayList<>(  );
        filmtest1.setVorführungen( Vorfuehrung2 );
        Assert.assertEquals( Vorfuehrung2,filmtest1.getVorführungen() );

    }

    @Test
    public void getVorführung() {
    }

    @Test
    public void addVorführung() {
    }

    @Test
    public void removeVorführung() {
    }

    @Test
    public void getLink() {
        Assert.assertEquals( "link",filmtest1.getLink() );
    }

    @Test
    public void setLink() {
        filmtest1.setLink( "newLink" );
        Assert.assertEquals( "newLink",filmtest1.getLink() );
    }

    @Test
    public void equals() {
    }

    @Test
    public void set() {
    }
}