package com.manraj.assignment4.utils;

import java.io.File;

public class DeleteExistingFiles {

    public DeleteExistingFiles(String path)
    {
        recursiveDelete(new File(path));
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
