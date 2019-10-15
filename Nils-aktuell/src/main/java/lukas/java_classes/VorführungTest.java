package main.java.lukas.java_classes;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class VorführungTest {
    List<Sitz> testFreieSitze = new List<Sitz>() {
        @Override
        public int size() {
            return 0;
        }

        @Override
        public boolean isEmpty() {
            return false;
        }

        @Override
        public boolean contains(Object o) {
            return false;
        }

        @Override
        public Iterator<Sitz> iterator() {
            return null;
        }

        @Override
        public Object[] toArray() {
            return new Object[0];
        }

        @Override
        public <T> T[] toArray(T[] a) {
            return null;
        }

        @Override
        public boolean add(Sitz sitz) {
            return false;
        }

        @Override
        public boolean remove(Object o) {
            return false;
        }

        @Override
        public boolean containsAll(Collection<?> c) {
            return false;
        }

        @Override
        public boolean addAll(Collection<? extends Sitz> c) {
            return false;
        }

        @Override
        public boolean addAll(int index, Collection<? extends Sitz> c) {
            return false;
        }

        @Override
        public boolean removeAll(Collection<?> c) {
            return false;
        }

        @Override
        public boolean retainAll(Collection<?> c) {
            return false;
        }

        @Override
        public void clear() {

        }

        @Override
        public Sitz get(int index) {
            return null;
        }

        @Override
        public Sitz set(int index, Sitz element) {
            return null;
        }

        @Override
        public void add(int index, Sitz element) {

        }

        @Override
        public Sitz remove(int index) {
            return null;
        }

        @Override
        public int indexOf(Object o) {
            return 0;
        }

        @Override
        public int lastIndexOf(Object o) {
            return 0;
        }

        @Override
        public ListIterator<Sitz> listIterator() {
            return null;
        }

        @Override
        public ListIterator<Sitz> listIterator(int index) {
            return null;
        }

        @Override
        public List<Sitz> subList(int fromIndex, int toIndex) {
            return null;
        }
    };
    List<Kinosaal> testKinosaalListe = new List<Kinosaal>() {
        @Override
        public int size() {
            return 0;
        }

        @Override
        public boolean isEmpty() {
            return false;
        }

        @Override
        public boolean contains(Object o) {
            return false;
        }

        @Override
        public Iterator<Kinosaal> iterator() {
            return null;
        }

        @Override
        public Object[] toArray() {
            return new Object[0];
        }

        @Override
        public <T> T[] toArray(T[] a) {
            return null;
        }

        @Override
        public boolean add(Kinosaal kinosaal) {
            return false;
        }

        @Override
        public boolean remove(Object o) {
            return false;
        }

        @Override
        public boolean containsAll(Collection<?> c) {
            return false;
        }

        @Override
        public boolean addAll(Collection<? extends Kinosaal> c) {
            return false;
        }

        @Override
        public boolean addAll(int index, Collection<? extends Kinosaal> c) {
            return false;
        }

        @Override
        public boolean removeAll(Collection<?> c) {
            return false;
        }

        @Override
        public boolean retainAll(Collection<?> c) {
            return false;
        }

        @Override
        public void clear() {

        }

        @Override
        public Kinosaal get(int index) {
            return null;
        }

        @Override
        public Kinosaal set(int index, Kinosaal element) {
            return null;
        }

        @Override
        public void add(int index, Kinosaal element) {

        }

        @Override
        public Kinosaal remove(int index) {
            return null;
        }

        @Override
        public int indexOf(Object o) {
            return 0;
        }

        @Override
        public int lastIndexOf(Object o) {
            return 0;
        }

        @Override
        public ListIterator<Kinosaal> listIterator() {
            return null;
        }

        @Override
        public ListIterator<Kinosaal> listIterator(int index) {
            return null;
        }

        @Override
        public List<Kinosaal> subList(int fromIndex, int toIndex) {
            return null;
        }
    };
    List<Film> testFilmListe = new List<Film>() {
        @Override
        public int size() {
            return 0;
        }

        @Override
        public boolean isEmpty() {
            return false;
        }

        @Override
        public boolean contains(Object o) {
            return false;
        }

        @Override
        public Iterator<Film> iterator() {
            return null;
        }

        @Override
        public Object[] toArray() {
            return new Object[0];
        }

        @Override
        public <T> T[] toArray(T[] a) {
            return null;
        }

        @Override
        public boolean add(Film film) {
            return false;
        }

        @Override
        public boolean remove(Object o) {
            return false;
        }

        @Override
        public boolean containsAll(Collection<?> c) {
            return false;
        }

        @Override
        public boolean addAll(Collection<? extends Film> c) {
            return false;
        }

        @Override
        public boolean addAll(int index, Collection<? extends Film> c) {
            return false;
        }

        @Override
        public boolean removeAll(Collection<?> c) {
            return false;
        }

        @Override
        public boolean retainAll(Collection<?> c) {
            return false;
        }

        @Override
        public void clear() {

        }

        @Override
        public Film get(int index) {
            return null;
        }

        @Override
        public Film set(int index, Film element) {
            return null;
        }

        @Override
        public void add(int index, Film element) {

        }

        @Override
        public Film remove(int index) {
            return null;
        }

        @Override
        public int indexOf(Object o) {
            return 0;
        }

        @Override
        public int lastIndexOf(Object o) {
            return 0;
        }

        @Override
        public ListIterator<Film> listIterator() {
            return null;
        }

        @Override
        public ListIterator<Film> listIterator(int index) {
            return null;
        }

        @Override
        public List<Film> subList(int fromIndex, int toIndex) {
            return null;
        }
    };
    Kino testkino = new Kino(1,"testkino","ort",testKinosaalListe,testFilmListe);
    Date testdate = new Date(2000,1,1);
    Film testFilm = new Film(1,"testfilm",null,"beschreibung",1,12,1,null,null);
    Kinosaal testKinosaal = new Kinosaal();

    Vorführung testVorführung = new Vorführung(1,testKinosaal,1,testFilm,testdate,true,null);

    @Test
    public void getVid() {
        Assert.assertEquals(1,testVorführung.getVid());
    }

    @Test
    public void getSaal() {
        Assert.assertEquals(testKinosaal,testVorführung.getSaal());
    }

    @Test
    public void getGesamtdauer() {
        Assert.assertEquals(1,testVorführung.getGesamtdauer());
    }

    @Test
    public void getFreieSitze() {
        Assert.assertEquals(testKinosaal.getSitze(),testVorführung.getFreieSitze());
    }

    @Test
    public void getFilm() {
        Assert.assertEquals(testFilm, testVorführung.getFilm());
    }

    @Test
    public void getZeitpunkt() {
        Assert.assertEquals(testdate, testVorführung.getZeitpunkt());
    }

    @Test
    public void isDreiD() {
        Assert.assertEquals(true, testVorführung.isDreiD());
    }

    @Test
    public void getGrundpreis() {
        Assert.assertEquals(1, testVorführung.getGrundpreis());
    }

    @Test
    public void getWerbungen() {
        Assert.assertEquals(null, testVorführung.getWerbungen());
    }

    @Test
    public void buchePlätze() {
        //ToDo
    }

    @Test
    public void buchungMöglich() {
        //ToDo

    }

    @Test
    public void equals() {
        //ToDo
    }
}