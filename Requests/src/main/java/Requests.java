import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import org.json.JSONObject;

public class Requests {


  public void getNutzerbyName(String name) {
    try {
      URL url = new URL("http://localhost:8080/getNutzerbyName");
      HttpURLConnection con = (HttpURLConnection) url.openConnection();
      con.setRequestMethod("GET");
      con.setRequestProperty("name",name);
      con.connect();
      int status = con.getResponseCode();
      BufferedReader in = new BufferedReader(
          new InputStreamReader(con.getInputStream()));
      String inputLine;
      StringBuffer content = new StringBuffer();
      while ((inputLine = in.readLine()) != null) {
        content.append(inputLine);
      }
      in.close();
      String json=content.toString();
      json=json.replace("[","");
      json=json.replace("]","");
      JSONObject root = new JSONObject(json);
      String vorname= root.getString("vorname");
     // System.out.println(json.toString());
      System.out.println(vorname);
    }catch(Exception e){}
  }



  public void getNutzerbyID(String id) {
    try {
      URL url = new URL("http://localhost:8080/getNutzerbyID");
      HttpURLConnection con = (HttpURLConnection) url.openConnection();
      con.setRequestMethod("GET");
      con.setRequestProperty("id",id);
      con.connect();
      int status = con.getResponseCode();
      BufferedReader in = new BufferedReader(
          new InputStreamReader(con.getInputStream()));
      String inputLine;
      StringBuffer content = new StringBuffer();
      while ((inputLine = in.readLine()) != null) {
        content.append(inputLine);
      }
      in.close();
      String json=content.toString();
      json=json.replace("[","");
      json=json.replace("]","");
      JSONObject root = new JSONObject(json);
      String vorname= root.getString("vorname");
      // System.out.println(json.toString());
      System.out.println(vorname);
    }catch(Exception e){}
  }

  public void setNutzer(){
    URL url = null;
    try {
      url = new URL("http://localhost:8080/setNutzer");
      HttpURLConnection con = (HttpURLConnection) url.openConnection();
      con.setRequestMethod("GET");
      con.setRequestProperty("id","5");
      con.connect();
      Thread.sleep(100);
      System.out.println(con.getResponseCode());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void getAllData(String head) {
    try {
      URL url = new URL("http://localhost:8080/getAllData");
      HttpURLConnection con = (HttpURLConnection) url.openConnection();
      con.setRequestMethod("GET");
      con.setRequestProperty("head",head);
      con.connect();
      int status = con.getResponseCode();
      System.out.println(status);
      BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
      String inputLine;
      StringBuffer content = new StringBuffer();
      while ((inputLine = in.readLine()) != null) {
        content.append(inputLine);
      }
      in.close();
      String json=content.toString();
      HashMap<String,Object> result =
          new ObjectMapper().readValue(json, HashMap.class);
      //JSONObject root = new JSONObject(json);
      // System.out.println(root.toString(5));
      System.out.println(result.toString());
    }catch(Exception e){}
  }


  public void CalculationEngine(){
    int a = 1000;
    boolean[] f = new boolean[a + 1];
//		Set all to true
    for (int i = 1; i < f.length; i++) {
      f[i] = true;
    }
//		Filter false values
    for (int j = 1; j < f.length; j++) {
      if (f[j] && j != 1) {
        int k = j;
        while (k + j < f.length) {
          k = k + j;
          f[k] = false;
        }
      }
    }
//		print
    for (int k = 1; k < f.length; k++) {
      if (f[k]) {
        System.out.println(k);
      }
      ;
    }
  }
}
