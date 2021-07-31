package com.manraj.assignment4.problem2;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DocumentNode {

    private int articleNumber = 0;
    private String content = null;
    private int polarityNumber = 0;

    private String polarityStatus = null;
    private String match = null;

    public static List<DocumentNode> articles = new ArrayList<>();
    public static int articleCounter = 0;

    public DocumentNode(String content) throws IOException {
        processArticle(content);
        articles.add(this);
    }

    private void processArticle(String content) throws IOException {
        articleNumber = ++articleCounter;
        this.content = content;
        String[] values = content.replaceAll("^[.,\\s]+", "").split("[.,\\s]+");
        for(int iter = 0; iter < values.length; iter++){
            if(iter != 0){
                this.match = this.match + ", ";
            }
            this.match = this.match + values[iter];

            if(FetchDataFromFiles.getInstance().getNegativeWords().contains(values[iter].toLowerCase())){
                polarityNumber--;
            }
            else if(FetchDataFromFiles.getInstance().getPositiveWords().contains(values[iter].toLowerCase())){
                polarityNumber++;
            }
        }

        if(polarityNumber < 0){
            polarityStatus = "Negative";
        }else if(polarityNumber > 0){
            polarityStatus = "Positive";
        }else{
            polarityStatus = "Neutral";
        }

    }

    public int getArticleNumber() {
        return articleNumber;
    }

    public String getContent() {
        return content;
    }

    public int getPolarityNumber() {
        return polarityNumber;
    }

    public String getPolarityStatus() {
        return polarityStatus;
    }

    public String getMatch() {
        return match;
    }

    public static List<DocumentNode> getArticles() {
        return articles;
    }

    public static int getArticleCounter() {
        return articleCounter;
    }



}
