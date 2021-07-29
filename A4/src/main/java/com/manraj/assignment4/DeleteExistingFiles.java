package com.manraj.assignment4;

import java.io.File;

public class DeleteExistingFiles {

    public DeleteExistingFiles()
    {
        recursiveDelete(new File("./Output/Assignment4/"));
        //recursiveDelete(new File(""));
    }

    private void recursiveDelete(File dir){
        File files[] = dir.listFiles();
        if(files==null){
            dir.delete();
            return;
        }
        for(File file : files){
            recursiveDelete(file);
        }
        dir.delete();
        return;
    }

}
