package com.vharsh.quoterialist;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView Inspiration, Model;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Inspiration = (TextView) findViewById(R.id.lines);
        Model = (TextView) findViewById(R.id.speaker);
        final String url = getResources().getString(com.vharsh.quoterialist.R.string.quote_url);

        if (findNet()) {
            // Call the function onlineQuote()
        } else {
            offlineQuote();
        }
    }

    /**
     * Checks for internet connectivity each time the app is loaded.
     *
     * @return false if the mobile is disconnected
     */
    private boolean findNet() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }

    /**
     * A single interface which writes sayings to the TextViews
     */
    private void writeOut(String message, String author) {
        Inspiration.setMovementMethod(new ScrollingMovementMethod());
        Inspiration.setText(message);
        Model.setText(author);
    }

    /**
     * Changes the value of the quote[] to a (hopefully)new quote and it's speaker, i.e. 'quote-mouth'.
     * <i>Picks up a random pop saying, from an array.</i>
     */
    void offlineQuote() {
        int i = (int) (Math.random() * 10);
        String quotNot[] = {"The Internet is so big, so powerful and pointless that for some people it is a complete substitute for life.",
                "Andrew Brown",
                "We are all now connected by the Internet, like neurons in a giant brain.",
                "Stephen Hawking",
                "Big stories need human stakes.",
                "Aaron Swartz",
                "Information is power. But like all power, there are those who want to keep it for themselves.",
                "Aaron Swartz",
                "It's far better to buy a wonderful company at a fair price than a fair company at a wonderful price.",
                "Warren Buffett",
                "The first rule is not to lose. The second rule is not to forget the first rule.",
                "Warren Buffett",
                "Price is what you pay. Value is what you get.",
                "Warren Buffett",
                "There are three constants in life... change, choice and principles.",
                "Stephen Covey",
                "You can retire from a job, but don't ever retire from making extremely meaningful contributions in life.",
                "Stephen Covey",
                "Chance favors the connected mind.",
                "Steven Johnson",
                "Google can bring you back 100,000 answers. A librarian can bring you back the right one.",
                "Neil Gaiman"
        };
        writeOut(quotNot[(2 * i)], quotNot[(2 * i + 1)]);
    }

    public void OnClick(View view) {
        if (findNet()) {
            // Call the function onlineQuote()
        } else {
            offlineQuote();
        }
    }

}