package com.codedifferently.hurt;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ItemPrinter {
    private final int requiredPadding = 8;
    ItemDataTransformationService itemDataTransformationService;
    public ItemPrinter(){
        itemDataTransformationService = new ItemDataTransformationService();
    }
    public String getOutput(){
        StringBuilder stringBuilder = new StringBuilder();
        for(Map.Entry<String, ArrayList<String>> itemCost : itemDataTransformationService.getCostMap().entrySet()){
                stringBuilder.append(getRow( itemCost.getKey(), itemCost.getValue().size(), true));
            HashMap<String, Integer> numberOfOccurances = new HashMap<>();
            for(String cost : itemCost.getValue()){
                numberOfOccurances.put(cost, numberOfOccurances.get(cost) == null ? 1 : numberOfOccurances.get(cost) +1 );
            }
            for(Map.Entry<String, Integer> itemFrequency : numberOfOccurances.entrySet()){
                stringBuilder.append(getRow(itemFrequency.getKey(), itemFrequency.getValue(), false));
            }
        };
        return stringBuilder.toString();
    }
    private String getRowSeperator(boolean isTopLevel){
        return isTopLevel? "============= \t \t =============" : "-------------\t\t -------------";
    }
    private String getPaddedName(String name){
        StringBuilder stringBuilder = new StringBuilder();
        int amountToPad = requiredPadding - name.length();
        for(int i = 0; i < amountToPad; i++){
            stringBuilder.append(" ");
        }
        stringBuilder.append(name);
        return stringBuilder.toString();
    }
    private  String getRow(String name, int numberOfOccurances, boolean isTopLevel){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(isTopLevel ? "name:" : "Price");
        stringBuilder.append(getPaddedName(name));
        stringBuilder.append(" \t\t ");
        stringBuilder.append("seen: ");
        stringBuilder.append(numberOfOccurances);
        stringBuilder.append(" times");
        stringBuilder.append("\n");
        stringBuilder.append(getRowSeperator(isTopLevel));
        stringBuilder.append("\n");
        return stringBuilder.toString();
    }
}
