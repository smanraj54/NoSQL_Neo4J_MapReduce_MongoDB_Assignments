package com.manraj.assignment4.problem3;

import java.util.ArrayList;
import java.util.List;

public class FrequencyNode {

    public static int canadaCount = 0;
    public static int monctonCount = 0;
    public static int torontoCount = 0;

    public static int totalArticles = 0;

    public int canadaCountPerArticle = 0;
    public int articleWordCount = 0;

    private static List<FrequencyNode> articles = new ArrayList<>();

    public FrequencyNode(String article){
        //this.totalArticles = totalArticles;

        if(processArticle(article, "canada")){
            canadaCount++;
        }else if(processArticle(article, "moncton")){
            monctonCount++;
        }else if(processArticle(article, "toronto")){
            torontoCount++;
        }

        articles.add(this);

    }

    private boolean processArticle(String article, String keyword){
        boolean keywordPresent = false;
        String[] values = article.replaceAll("^[.,\\s]+", "").split("[.,\\s]+");

        for(int iter = 0; iter < values.length; iter++){
            if(values[iter].equalsIgnoreCase(keyword)){
                keywordPresent = true;
                if(keyword.equalsIgnoreCase("canada")){
                    canadaCountPerArticle++;
                }
            }
            articleWordCount++;
        }

        return keywordPresent;
    }


    public static int getCanadaCount() {
        return canadaCount;
    }

    public static int getMonctonCount() {
        return monctonCount;
    }

    public static int getTorontoCount() {
        return torontoCount;
    }

    public int getCanadaCountPerArticle() {
        return canadaCountPerArticle;
    }

    public static List<FrequencyNode> getArticles() {
        return articles;
    }

    public static int getTotalArticles() {
        return totalArticles;
    }

    public int getArticleWordCount() {
        return articleWordCount;
    }

    public static void setTotalArticles(int totalArticles) {
        FrequencyNode.totalArticles = totalArticles;
    }
}
