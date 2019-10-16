package com.majianwei.util.commontools;

public class TypeConversion {

    public static long[] getLong(String str){
        if(str!=null){
            int o = str.lastIndexOf("\"");
            String substring = str.substring(8,o);
            String[] split = substring.split(",");
            long[] id = new long[split.length];
            for(int i = 0;i<split.length;i++){
                id[i] = Long.parseLong(split[i]);
            }
            return id;
        }
       return null;

    }
}
