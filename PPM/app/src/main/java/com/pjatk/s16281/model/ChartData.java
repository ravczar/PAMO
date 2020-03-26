package com.pjatk.s16281.model;

import java.text.MessageFormat;

public class ChartData {
    private String unencodedHtml;
    private String width;
    private String height;

    public ChartData(String width, String height){
        this.width = width;
        this.height = height;
        unencodedHtml = getSiteHtmlData();
    }

    public void setChartWidth(String width){
        this.width = width;
    };

    public void setChartHeight(String height){
        this.height = height;
    };

    public String getChartWith(){
        return width;
    }

    public String getChartHeight(){
        return height;
    }


    public String getUnencodedHtml(){
        return unencodedHtml;
    }

    private String getSiteHtmlData(){
        String loadingData = "<html>\n" +
                "  <head>\n" +
                "    <script type=\"text/javascript\" src=\"https://www.gstatic.com/charts/loader.js\"></script>\n" +
                "    <script type=\"text/javascript\">\n" +
                "      google.charts.load(\"current\", {packages:[\"corechart\"]});\n" +
                "      google.charts.setOnLoadCallback(drawChart);\n" +
                "      function drawChart() {\n";

        StringBuilder sB = new StringBuilder(loadingData);


        sB.append( "var data = google.visualization.arrayToDataTable([\n" +
                        "          ['Country', 'Total Cases'],\n" +
                        "          ['Italy',       74386],\n" +
                        "          ['Spain',       56188],\n" +
                        "          ['France',      25233],\n" +
                        "          ['Switzerland', 11575],\n" +
                        "          ['Netherlands', 7432],\n" +
                        "          ['Austria',     6398],\n" +
                        "          ['Germany',     3686],\n" +
                        "          ['Portugal',    3544],\n" +
                        "          ['Norway',      3279],\n" +
                        "          ['Denmark',     1851],\n" +
                        "          ['Czechia',     1775],\n" +
                        "          ['Poland',      1120],\n" +
                        "          ['Finland',     958],\n" +
                        "          ['Russia',      840],\n" +
                        "          ['Slovenia',    528],\n" +
                        "          ['Sweden',      314],\n" +
                        "          ['Hungary',     261],\n" +
                        "          ['Slovakia',    226],\n" +
                        "          ['Ukraine',     162],\n" +
                        "          ['Belarus',     86]\n" +
                        "        ]);\n"
        );
        sB.append("\n" +
                "      var options = {\n" +
                "        legend: 'none',\n" +
                "        pieSliceText: 'label',\n" +
                "        title: 'Total cases in selected European Countries (1 [unit] is 1 [person])',\n" +
                "        pieStartAngle: 100,\n" +
                "      };\n" +
                "\n" +
                "        var chart = new google.visualization.PieChart(document.getElementById('piechart'));\n" +
                "        chart.draw(data, options);\n" +
                "      }\n" +
                "    </script>\n" +
                "  </head>\n" +
                "  <body>\n");
        sB.append( MessageFormat.format("<div id=\"piechart\" style=\"width: {0}; height: {1};\"></div>\n", width, height));
        sB.append("</body>\n" +
                "</html>");

        return sB.toString();

    }

}

