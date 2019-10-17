package lukas.junit_tests;

import lukas.classes.Film;
import lukas.java_classes.Kino;
import lukas.java_classes.Kinosaal;
import lukas.java_classes.Sitz;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class SitzTest {
    Sitz sitz;
    Kinosaal kinosaal;
    Kino kino;
    Film film=new Film()
            ;

    List<Kinosaal> säle = new ArrayList<>(  );
    List<String> genres =new ArrayList<>(  );
    List<String> darsteller = new ArrayList<>(  );
    List<String> regie= new ArrayList<>(  );
    List<Film> filme =new ArrayList<>(  );

    @org.junit.Before
    public void setUp() throws Exception {

        säle.add(kinosaal);
        genres.add("Comedy");
        darsteller.add("Marven");
        regie.add("name");
        filme.add(film);
        //film =new Film(123,"filmTitle","Beschreibung",135,18,10,genres,darsteller,regie  );
        kino =new Kino( 123,"name","ort",säle,filme);
        kinosaal =new Kinosaal(  );
        sitz=new Sitz("122",kinosaal,true, true);
    }

    @org.junit.After
    public void tearDown() throws Exception {
    }

    @org.junit.Test
    public void getSitzID() {
        assertEquals( 122,sitz.getSitzID() );
    }

    @org.junit.Test
    public void getSaal() {
        assertEquals( kinosaal,sitz.getSaal() );
    }

    @org.junit.Test
    public void getBarrierefrei() {
        assertEquals(true,sitz.getBarrierefrei()  );
    }

    @org.junit.Test
    public void isLoge() {
        assertEquals( true,sitz.isLoge() );
    }

    @org.junit.Test
    public void equals() {
    }
}