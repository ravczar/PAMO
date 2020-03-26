package com.pjatk.s16281;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;

import com.pjatk.s16281.model.ChartData;

public class Chart extends AppCompatActivity {
    private Button backToMain;
    private WebView chartWV;
    private ChartData data;
    private String chartWidth,chartHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chart);

        // BTN, WebView initialize
        backToMain = findViewById(R.id.chart_back);
        chartWV = findViewById(R.id.chart_webView);
        chartWidth = "900";
        chartHeight = "500";
        data = new ChartData(chartWidth, chartHeight);

        // Set JS to true
        WebSettings chartWebSetting = chartWV.getSettings();
        chartWebSetting.setJavaScriptEnabled(true);

        String unencodedHtml = data.getUnencodedHtml();
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

        //Back to main activity listener
        chartWV.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //refreshChart();
            }
        });


    }

}
