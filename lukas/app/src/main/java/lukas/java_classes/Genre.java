package lukas.java_classes;

public class Genre {
  private int genreID;
  private String name;

  public Genre (int genreID, String name){
    this.genreID = genreID;
    this.name = name;
  }//Konstruktor

  public Genre () {}

  public int getGenreID() {
    return genreID;
  }

  public String getName() {
    return name;
  }

  public boolean equals (Genre genre){
    if(this.genreID == genre.getGenreID())return true;
    else return false;
  }//equals

}//class
