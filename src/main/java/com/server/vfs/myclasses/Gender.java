package com.server.vfs.myclasses;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class Gender {

    private File file;
    public Gender(){
        file = new File("russian_names.txt");
    }

    public int getGender(String name){
        try {
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);
            String line = reader.readLine();
            line = reader.readLine();
            while (line != null) {
                try {
                    JSONObject js = (JSONObject) JSONValue.parseWithException(line);
                    if(equals(js.get("Name").toString().toUpperCase(),name)){
                        if(equals(js.get("Sex").toString(),"М")){
                            return 1;
                        }else {
                            return 0;
                        }
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
                line = reader.readLine();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return -1;
    }

    private boolean equals(String str1,String str2){
        if(str1.length()!=str2.length()){
            return false;
        }
        boolean ans = true;
        for(int i = 0; (i < str1.length()) && ans;++i){
            ans = str1.charAt(i) == str2.charAt(i);
        }
        return ans;
    }
}
