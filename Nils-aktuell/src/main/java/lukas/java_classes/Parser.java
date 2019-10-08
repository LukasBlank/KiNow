package lukas.java_classes;

import com.google.gson.Gson;
import java.util.ArrayList;
import org.apache.catalina.LifecycleState;
import org.springframework.http.ResponseEntity;

public class Parser {



  public Parser (){

  }//K

  //Bekannte Probleme: Funktioniert nicht, wenn in Filmbeschreibungen geschweifte Klammern vorkommen.

  public ArrayList<Film> toFilmList (String filmliste){
    if (filmliste.length()==0){
      return null;
    }//then
    else {
      try {
        ArrayList<Film> filmList = new ArrayList<Film>();
        String film,attribut;
        String tmp = filmliste.substring(0, filmliste.indexOf(','));
        //wenn die Anfrage korrekt gestellt wird, dann beginnt der String immer mit der selben Zeichenkette, diese muss also vorhanden sein
        if (tmp.equals("<202 ACCEPTED Accepted")) {
          //dann wird der gesamte String über geschweifte Klammern zu einzelnen Filmen gesplittet
          filmliste = filmliste.substring(filmliste.indexOf(',') + 2);
          while (filmliste.length() > 0 && filmliste.indexOf('}') != -1) {
            Film akt = new Film();
            film = filmliste.substring(1, filmliste.indexOf('}'));
            filmliste = filmliste.substring(filmliste.indexOf('}') + 3);
            //jeder Film geht alle attribute durch und erzeugt so ein Objekt des Film Typens
            while (film.length()>0 && film.indexOf('=')!=-1){
              attribut = film.substring(0,film.indexOf('='));
              tmp = film.substring(film.indexOf('=')+1);
              if (tmp.indexOf('=')!=-1){
                tmp = tmp.substring(0,tmp.indexOf('='));
                tmp = tmp.substring(0,tmp.lastIndexOf(','));
                int pos = tmp.length() + attribut.length() + 3;
                film = film.substring(pos);
              }//then
              else film = "";
              switch (attribut){
                case "FilmID":
                  if (akt.getFilmID()!=0)return null;
                  else {
                    int t = Integer.parseInt(tmp);
                    akt.setFilmID(t);
                  }//else
                  break;
                case "Titel":
                  if (akt.getTitel()!=null)return null;
                  else akt.setTitel(tmp);
                  break;
                case "Beschreibung":
                  if (akt.getBeschreibung()!=null)return null;
                  else akt.setBeschreibung(tmp);
                  break;
                case "Dauer":
                  if (akt.getDauer()!=0)return null;
                  else {
                    int t = Integer.parseInt(tmp);
                    akt.setDauer(t);
                  }//else
                  break;
                case "Bewertung":
                  if (akt.getBewertung()!=0)return null;
                  else {
                    int t = Integer.parseInt(tmp);
                    akt.setBewertung(t);
                  }//else
                  break;
                case "FSK":
                  if (akt.getFsk()!=0)return null;
                  else {
                    int t = Integer.parseInt(tmp);
                    akt.setFsk(t);
                  }//else
                  break;
                  default: return null;
              }//switch
            }//while
            filmList.add(akt);
          }//while
          return filmList;
        }//then
        else return null;
      } catch (Exception e) {
        e.printStackTrace();
        return null;
      }//catch

    }//else

  }//toFilmList

  public Film toFilm (String film){


    return null;
  }//Film




}//class
