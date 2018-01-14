package com.server.vfs.parsing;

import com.server.vfs.myclasses.RelationGraph;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TextParser {

    private String name;

    public TextParser(String name){
        this.name = name;
    }

    public void processing(){
        createFile(readText());
        createFileJson(createNodeAndEdge(readRel()));
    }

    public List<String> readText(){
        List<String> arr = new ArrayList<>();
        try{
            File file = new File(name+"-uploaded.txt");
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);
            String line = reader.readLine();
            while (line != null) {
                if(line.equalsIgnoreCase("\tRelative")) {
                    line = reader.readLine();
                    for(int i = 0; i <3;++i ) {
                        line = reader.readLine();
                        if(line != null) {
                            arr.add(line.replace("\t",""));
                        }
                    }
                }
                line = reader.readLine();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return arr;
    }


    public void createFile(List<String> arr){
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(name+".txt"), "utf-8"))) {
            for(int i =0; i < arr.size(); ++i){
                writer.write(arr.get(i)+"\n");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public List<String> readRel(){
        List<String> arr = new ArrayList<>();
        try{
            File file = new File(name+".txt");
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);
            String line = reader.readLine();
            while (line != null) {
                arr.add(line);
                line = reader.readLine();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return arr;
    }

    public List<List<String>> createNodeAndEdge(List<String> arr){
        RelationGraph graph = new RelationGraph(arr);
        return graph.processing();
    }

    private void createFileJson(List<List<String>> list){
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(name.split("-")[0]+".json"), "utf-8"))) {
            for(int i =0; i<list.size(); ++i){
                for (int j=0;j<list.get(i).size();++j) {
                    writer.write(list.get(i).get(j));
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
