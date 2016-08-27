package com.vharsh.quoterialist;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
	private static final String url = "http://api.forismatic.com/api/1.0/?method=getQuote&jsonp=parseQuote&format=jsonp&lang=en";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView Inspiration = (TextView) findViewById(R.id.lines);
        TextView Model = (TextView) findViewById(R.id.speaker);
        String quote[] = new String[2];
        quote = GetQuote();
        // Populate quote with one of the 12 quotes ! Yay
        if (quote == null) {
            quote = OfflineQuote();
        }

    }
        /**
         * Requests an english Quote from forismatic API 1.0 as a string array using HTTPURLConnection
    	 * <i> This is called only when the device is Online </i>
         * @return {"quote", "author"}, or <b>null</b> if HTTP connection fails
         */
        public static String[] GetQuote(){
                try {
                    URL obj = new URL(url);
                    int dum;
                    String inputLine;
                    StringBuffer response = new StringBuffer();
                    HttpURLConnection con = (HttpURLConnection) obj.openConnection();
                    con.setRequestMethod("GET");
                    con.setRequestProperty("User-Agent", "Mozilla/5.0");
                    int responseCode = con.getResponseCode();
                    //System.out.println("Response Code : " + responseCode);
                    if (responseCode != 200)
                        return null;
                    BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                    System.err.println("HERE IT GOES");
                    //System.err.println(new String(response));
                    response = (response.replace(0, 11, ""));
                    response.setLength(Math.max(response.length() - 1, 0));
                    String reply[] = new String[2];
                    reply[0] = response.substring(14, response.indexOf("\",", 14));
                    dum = response.indexOf(", \"quoteAuthor\":\"") + 17;
                    reply[1] = response.substring(dum, response.indexOf("\",", dum));
                    in.close();
                    return reply;
                }
                catch (Exception e){
                    // Can you even do something?
                    return null;
                }
	}
    private static String[] OfflineQuote(){
        int i = int(Math.random() * 12);
        File f = new File("offlineQuotes");
        FileInputStream fis = new FileInputStream(f);
        BufferedReader brr = new BufferedReader(fis);
        for (int j = 0; j < i; j++){

        }
    }
}
