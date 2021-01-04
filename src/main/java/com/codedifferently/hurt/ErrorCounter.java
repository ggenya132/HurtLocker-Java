package com.codedifferently.hurt;

public class ErrorCounter {
    private static ErrorCounter instance;
    private int errorCount;

    public int getErrorCount() {
        return errorCount;
    }

    public static ErrorCounter getInstance(){
        if(instance == null){
            instance = new ErrorCounter();
        }
        return instance;
    }
    public void incrementErrors(){
        errorCount++;
    }
}
