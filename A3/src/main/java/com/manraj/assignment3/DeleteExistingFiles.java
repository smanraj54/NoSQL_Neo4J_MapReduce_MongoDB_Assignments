package com.manraj.assignment3;

import java.io.File;

public class DeleteExistingFiles {

    public DeleteExistingFiles()
    {
        recursiveDelete(new File("./Output"));
        recursiveDelete(new File("Neo4j_ParksDataAndQueries/RegionParkQueries/"));
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
