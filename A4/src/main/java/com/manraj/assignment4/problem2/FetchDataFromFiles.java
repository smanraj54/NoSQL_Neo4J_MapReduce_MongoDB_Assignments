package com.manraj.assignment4.problem2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class FetchDataFromFiles {
    private Set<String> positiveWords;
    private Set<String> negativeWords;
    
    private FetchDataFromFiles() throws IOException {
        negativeWords = fetchKeyWords("./Input/NegativeWords/negative-words.txt");
        positiveWords = fetchKeyWords("./Input/PositiveWords/positive-words.txt");


    }

    private Set<String> fetchKeyWords(String path) throws IOException {
        Set<String> keyWordsSet = new HashSet<>();
        FileReader file = new FileReader(path);
        BufferedReader read = new BufferedReader(file);

        String line = "";
        while((line = read.readLine()) != null){
            keyWordsSet.add(line);
        }
        return keyWordsSet;
    }
    
    private static FetchDataFromFiles instance = null;
    
    public static FetchDataFromFiles getInstance() throws IOException {
        if(instance == null){
            instance = new FetchDataFromFiles();
        }
        return instance;
    }

    public Set<String> getPositiveWords() {
        return positiveWords;
    }

    public Set<String> getNegativeWords() {
        return negativeWords;
    }
}
