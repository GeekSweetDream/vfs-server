package com.server.vfs.myclasses;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class NodeAndEdge {
    private List<Person> Heroes;
//    private String mName, sName, rel,sRel;
    private List<String> graph;
    private int n;

//    public NodeAndEdge(String mName, String sName, String rel) {
//        this.mName = mName.substring(1);
//        this.sName = sName.substring(1);
//        this.rel = rel.substring(1);
//        hashA = getHash(mName);
//        hashB = getHash(sName);
//        sRel = getAddRel();
//    }

//    public NodeAndEdge(String mName, String sName, String rel, String sRel) {
//        this.mName = mName;
//        this.sName = sName;
//        this.rel = rel;
//        this.sRel = sRel;
//        hashA = getHash(mName);
//        hashB = getHash(sName);
//    }

    public NodeAndEdge(List<Person> heroes){
        this.Heroes = heroes;
        n = 0; // Костыль
    }

    private int getHash(String name){
        int hash = 2139062143;

        for(int i = 0; i < name.length(); i++)
            hash = 37 * hash + name.charAt(i);

        return hash;
    }

    public List<String> getNode() {
        graph = new ArrayList<>();
        for (int i = 0; i < Heroes.size(); ++i) {
            graph.add("{\"data\": {\"id\":  \"" + getHash(Heroes.get(i).getName()) + "\", \"name\": \"" + Heroes.get(i).getName() + "\"}}\n");
        }
        return graph;
    }

    public List<String> getEdge(){
        List<String> edge = new ArrayList<>();
        for(int i = 0; i < Heroes.size();++i) {
            List<Relation> relations = Heroes.get(i).getRel();
            int hashA = getHash(Heroes.get(i).getName());
            for(int j = 0; j < relations.size(); ++j) {
                int hashB = getHash(relations.get(j).getName());
                edge.add("{\"data\": {\"id\": \"" + n + "\",\"source\": \"" + hashA + "\",\"target\":\"" + hashB + "\",\"label\":\"" + relations.get(j).getRel() + "\"}}\n");
                ++n;
                edge.add("{\"data\": {\"id\": \"" + n + "\",\"source\": \"" + hashB + "\",\"target\":\"" + hashA + "\",\"label\":\"" + relations.get(j).getSrel() + "\"}}\n");
                ++n;
            }
        }
        return edge;
    }

//    public List<String> getGraph() {
//        graph = new ArrayList<>();
//        graph.add("{\"data\": {\"id\":  \""+hashA+"\", \"name\": \""+mName+"\"}}\n");
//        graph.add("{\"data\": {\"id\":  \""+hashB+"\", \"name\": \""+sName+"\"}}\n");
//        graph.add("{\"data\": {\"id\": \""+(hashB+hashA)+"\",\"source\": \""+hashA+"\",\"target\":\""+hashB+"\",\"label\":\""+rel+"\"}}\n");
//        graph.add("{\"data\": {\"id\": \""+(hashB+1+hashA)+"\",\"source\": \""+hashB+"\",\"target\":\""+hashA+"\",\"label\":\""+sRel+"\"}}\n");
//        return graph;
//    }

//    private String getAddRel(){
//        Gender gender = new Gender();
//        int ans = gender.getGender(sName.split(" ")[0]);
//        return getStringRel(ans);
//    }
//
//    private String getStringRel(int gender){
//        File file = new File("relation.json");
//        try {
//            FileReader fr = new FileReader(file);
//            BufferedReader reader = new BufferedReader(fr);
//            String line = reader.readLine();
//            while (line != null) {
//                JSONObject js = (JSONObject) JSONValue.parseWithException(line);
//                if(js.get("rel").toString().toUpperCase().equalsIgnoreCase(rel)){
//                    if(gender == 1){
//                        return js.get("rel2").toString();
//                    }else {
//                        return js.get("rel3").toString();
//                    }
//                }
//                line = reader.readLine();
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        return " ";
//    }

//    private boolean equals(String str1,String str2){
//        if(str1.length()!=str2.length()){
//            return false;
//        }
//        boolean ans = true;
//        for(int i = 0; (i < str1.length()) && ans;++i){
//            ans = str1.charAt(i) == str2.charAt(i);
//        }
//        return ans;
//    }


//    public String getmName() {
//        return mName;
//    }
//
//    public String getsName() {
//        return sName;
//    }
//
//    public String getRel() {
//        return rel;
//    }

//    public int getHashA() {
//        return hashA;
//    }

//    public void setHashA(int hashA) {
//        this.hashA = hashA;
//    }
//
//    public int getHashB() {
//        return hashB;
//    }
//
//    public void setHashB(int hashB) {
//        this.hashB = hashB;
//    }

//    public String getsRel() {
//        return sRel;
//    }
//
//    public void setsRel(String sRel) {
//        this.sRel = sRel;
//    }
}
