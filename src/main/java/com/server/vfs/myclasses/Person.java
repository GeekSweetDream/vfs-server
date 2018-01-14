package com.server.vfs.myclasses;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;

public class Person {

    private String Name;
    private List<Relation>rel;

    Person(String Name){
        this.Name = Name;
        rel = new ArrayList<>();
    }

    public void addRel(String relation, String name){
        boolean exist = false;
        for(int i =0; !exist && i<rel.size();++i) {
            if(rel.get(i).getName().equalsIgnoreCase(name)){
                exist = true;
                rel.get(i).setSrel(getAddRel(name,relation));
            }
        }
        if(!exist){
            rel.add(new Relation(name,relation));
            rel.get(rel.size()-1).setSrel(getAddRel(name,relation));
        };
    }

    private String getAddRel(String sName, String relation){
        Gender gender = new Gender();
        int ans = gender.getGender(sName.split(" ")[0]);
        return getStringRel(ans,relation);
    }

    private String getStringRel(int gender, String relation){
        File file = new File("relation.json");
        try {
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);
            String line = reader.readLine();
            while (line != null) {
                JSONObject js = (JSONObject) JSONValue.parseWithException(line);
                if(js.get("rel").toString().toUpperCase().equalsIgnoreCase(relation)){
                    if(gender == 1){
                        return js.get("rel2").toString();
                    }else {
                        return js.get("rel3").toString();
                    }
                }
                line = reader.readLine();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return " ";
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public List<Relation> getRel() {
        return rel;
    }

    public void setRel(List<Relation> rel) {
        this.rel = rel;
    }
}
