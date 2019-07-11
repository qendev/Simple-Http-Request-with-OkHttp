package com.example.simplehttprequestwithokhttp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    //create a member variable for the textView thta displays the resuslts.
    private TextView mTextViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //asign the result textView
        mTextViewResult=findViewById(R.id.text_view_result);

        //instance of Okhttp Client.
        OkHttpClient client = new OkHttpClient();

        //url from where we are goin to download the Json file from.
        String url= "https://reqres.in/api/users?page=2";

        //we need a request
        Request request=new Request.Builder()
                .url(url)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                if(response.isSuccessful()){
                    final String myResponse=response.body().string();

                    MainActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mTextViewResult.setText(myResponse);
                        }
                    });
                }

            }
        });
    }
}
