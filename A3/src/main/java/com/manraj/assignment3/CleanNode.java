package com.manraj.assignment3;

import java.util.ArrayList;
import java.util.List;

public class CleanNode {

    private String title = null;
    private String content = null;
    private static List<CleanNode> cleanNodesList = null;

    public CleanNode(String title, String content) {
        this.title = title;
        this.content = content;
        if(cleanNodesList==null){
            cleanNodesList = new ArrayList<>();
        }
        cleanNodesList.add(this);
    }

    public String getTitle() {
        return title;
    }

    public static List<CleanNode> getCleanNodesList() {
        return cleanNodesList;
    }

    public static void setCleanNodesList(List<CleanNode> cleanNodesList) {
        CleanNode.cleanNodesList = cleanNodesList;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
