package lukas.classes;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ThreadRequest extends Thread {

    private String erg;
    private String url;
    private boolean fertig;
    private Request request;

    public void run (){
        fertig = false;
        if (request!=null){
            OkHttpClient client = new OkHttpClient();
            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(@NotNull Call call, @NotNull IOException e) {
                    e.printStackTrace();
                    erg = e.getMessage();
                    fertig = true;
                }//onFailure

                @Override
                public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                    if(response.isSuccessful()) {
                        erg = response.body().string();
                        fertig = true;
                        response.close();
                    }//then
                }//onResponse;
            });
        }//then
        else{
            fertig = true;
            System.out.println("No Request found.");
        }//else
    }//run

    public void setRequest (Request request){
        this.request = request;
    }//setRequest

    public String getErg () {return erg;}

    public boolean isFertig () {return fertig;}

}//class
