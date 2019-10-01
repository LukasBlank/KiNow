package com.example.demo;

import com.sun.tools.javac.jvm.Gen;

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

}//class
