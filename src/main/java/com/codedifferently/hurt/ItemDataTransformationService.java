package com.codedifferently.hurt;

import java.sql.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ItemDataTransformationService {
    private HashMap<String, ArrayList<String>> costMap;
    private ItemParser itemParser;
    public ItemDataTransformationService(){
        itemParser = new ItemParser();
        costMap = new HashMap<>();
        generateCostMap();
    }

    public HashMap<String, ArrayList<String>> getCostMap() {
        return costMap;
    }

    public void generateCostMap(){
        for(Item item : itemParser.getItemList()){

            if(costMap.get(item.getName()) == null){
               costMap.put(item.getName(), new ArrayList<>());
            }
             ArrayList<String> costs = costMap.get(item.getName());
            costs.add(item.getPrice());
        }
    }

}
