package com.codedifferently.hurt;

import java.util.ArrayList;
import java.util.List;

public class ItemParser {
    private String[] cleanItems;

    public List<Item> getItemList() {
        return itemList;
    }

    private List<Item> itemList;
    private RawDataService rawDataService;

    public ItemParser() {
        itemList = new ArrayList<>();
        rawDataService = new RawDataService();
        initializeData();
        parseItems();
    }

    private void initializeData() {
        String cleanItemRaw = rawDataService.getRawData().replaceAll("\\^|\\*|@|%|!", ";");
        cleanItems = cleanItemRaw.split("##");
    }

    private void parseItems() {
        for (String rawItem : cleanItems) {
            try {
                itemList.add(parseItem(rawItem));
            } catch (Exception e) {
                ErrorCounter.getInstance().incrementErrors();
            }
        }
    }

    private Item parseItem(String rawItemData) throws Exception {
        String[] fields = rawItemData.split(";");

        for (String field : fields) {
            String[] keyValue = field.split(":");
            if (keyValue.length < 2) {
                throw new Exception("Item requires all fields to be parsed");
            }
        }

        int indexOfName = 0;
        int indexOfPrice = 1;
        int indexOfType = 2;
        int indexOfExpiration = 3;

        String name = fields[indexOfName].split(":")[1];
        name = normalizeName(name);
        String price = fields[indexOfPrice].split(":")[1];
        String type = fields[indexOfType].split(":")[1];
        String expiration = fields[indexOfExpiration].split(":")[1];

        return new Item(name,price,type,expiration);
    }
    private String normalizeName(String name){
        return name.toLowerCase().replaceAll("0", "o");
    }
}
