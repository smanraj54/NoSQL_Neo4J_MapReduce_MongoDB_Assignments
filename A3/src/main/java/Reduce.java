import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class Reduce {

    public static Map<String, Integer> mapFinalCount = null;

    public Reduce(){
        mapFinalCount = new HashMap<>();

        for(String keyword : ProcessngKeywords.MapReduceKeyWords){
            if(!mapFinalCount.containsKey(keyword)){
                mapFinalCount.put(keyword, 0);
            }
            for(MapNode node : MapNode.nodesList){
                int currentVal = node.MapCount.containsKey(keyword) ? node.MapCount.get(keyword) : 0;
                mapFinalCount.put(keyword, mapFinalCount.get(keyword) + currentVal);
            }

        }
    }

}
