package com.pjatk.s16281;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;

public class Chart extends AppCompatActivity {
    private Button backToMain;
    private WebView chartWV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chart);

        // BTN, WebView initialize
        backToMain = findViewById(R.id.chart_back);
        chartWV = findViewById(R.id.chart_webView);

        // Set JS to true
        WebSettings chartWebSetting = chartWV.getSettings();
        chartWebSetting.setJavaScriptEnabled(true);

        // display
        //chartWV.loadUrl("https://www.journaldev.com");
        String unencodedHtml = "<html>\n" +
                "  <head>\n" +
                "    <script type=\"text/javascript\" src=\"https://www.gstatic.com/charts/loader.js\"></script>\n" +
                "    <script type=\"text/javascript\">\n" +
                "      google.charts.load('current', {'packages':['corechart']});\n" +
                "      google.charts.setOnLoadCallback(drawChart);\n" +
                "\n" +
                "      function drawChart() {\n" +
                "\n" +
                "        var data = google.visualization.arrayToDataTable([\n" +
                "          ['Task', 'Hours per Day'],\n" +
                "          ['Work',     11],\n" +
                "          ['Eat',      2],\n" +
                "          ['Commute',  2],\n" +
                "          ['Watch TV', 2],\n" +
                "          ['Sleep',    7]\n" +
                "        ]);\n" +
                "\n" +
                "        var options = {\n" +
                "          title: 'My Daily Activities'\n" +
                "        };\n" +
                "\n" +
                "        var chart = new google.visualization.PieChart(document.getElementById('piechart'));\n" +
                "\n" +
                "        chart.draw(data, options);\n" +
                "      }\n" +
                "    </script>\n" +
                "  </head>\n" +
                "  <body>\n" +
                "    <div id=\"piechart\" style=\"width: 900px; height: 500px;\"></div>\n" +
                "  </body>\n" +
                "</html>";
        String encodedHtml = Base64.encodeToString(unencodedHtml.getBytes(),
                Base64.NO_PADDING);
        chartWV.loadData(encodedHtml, "text/html", "base64");

        //back to main activity listener
        backToMain.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                finish();
            }
        });
    }
}
