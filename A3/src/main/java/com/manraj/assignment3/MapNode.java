package com.manraj.assignment3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapNode {

    public static List<MapNode> nodesList = null;
    public Map<String, Integer> MapCount = null;


    public MapNode(){
        if(nodesList==null)nodesList = new ArrayList<>();
        MapCount = new HashMap<>();
        nodesList.add(this);
    }


}
