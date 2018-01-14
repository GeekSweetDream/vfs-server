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
    private List<String> graph;
    private int n;

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

}
