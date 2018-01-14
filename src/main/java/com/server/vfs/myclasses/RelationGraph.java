package com.server.vfs.myclasses;

import java.util.ArrayList;
import java.util.List;

public class RelationGraph {

    private List<String> arr, allHeroes;
    private List<Person> persons;


    public RelationGraph(List<String> arr){
        this.arr = arr;
        this.persons = new ArrayList<>();
        this.allHeroes = new ArrayList<>();
    }

    public List<List<String>> processing(){
        List<List<String>> list = createBaseGraph();
        return list;
    }

    private List<List<String>> createBaseGraph(){
        createPersons();
        normalizeHeroes();
        List<List<String>> lists = new ArrayList<>();
        NodeAndEdge nAe = new NodeAndEdge(persons);
        lists.add(nAe.getNode());
        lists.add(nAe.getEdge());
        return lists;
    }

    private void createPersons(){
        int length = arr.size();
        for(int i = 0; i<length;i+=3){
            String  name = arr.get(i).split("=")[1].toUpperCase().substring(1),
                    sname = arr.get(i+1).split("=")[1].toUpperCase().substring(1),
                    rel = arr.get(i+2).split("=")[1].toUpperCase().substring(1);

            int numb = getNumbPerson(name);
            if(numb==-1){
                Person p = new Person(name);
                p.addRel(rel,sname);
                if(!isExistHero(sname)){
                    Person sp = new Person(sname);
                    persons.add(sp);
                }
                persons.add(p);
            }else{
                Person p = persons.get(numb);
                p.addRel(rel, sname);
            }
        }
    }

    private boolean isExistHero(String name){
        boolean exist=false;
        for (Person p:persons) {
            if(p.getName().equalsIgnoreCase(name)){
                exist = true;
            }
        }
        return exist;
    }

//    private void normalizeHeroes(){
//        for(int i = 0; i < allHeroes.size();++i){
//            String s = allHeroes.get(i);
//            if(s.split(" ").length == 1){
//                for(int j = 0; j<allHeroes.size();++j){
//                    String s2 = allHeroes.get(j);
//                    if(j!=i && s2.split(" ").length == 2 && s.equalsIgnoreCase(s2.split(" ")[0])){
//                        allHeroes.remove(i);
//                    }
//                }
//            }
//        }
//    }

    private void normalizeHeroes(){
        for(int i = 0; i < persons.size();++i){
            Person p = persons.get(i);
            if(p.getName().split(" ").length == 1){
                for(int j = 0; j<persons.size();++j){
                    Person p2 = persons.get(j);
                    if(j!=i && p2.getName().split(" ").length == 2 && p.getName().equalsIgnoreCase(
                            p2.getName().split(" ")[0])){
                        copyRel(p2, p);
                        persons.remove(i);
                    }
                }
            }
        }
    }

    private void copyRel(Person one, Person two){
        List<Relation>  l = one.getRel(),
                        l2 = two.getRel();
        for(int i =0; i < l2.size(); ++i){
            boolean exist = false;
            for(int j = 0; !exist && j < l.size();++j){
                if(l.get(j).getName().equalsIgnoreCase(l2.get(i).getName())){
                    if(l.get(j).getRel().equalsIgnoreCase(l2.get(i).getRel())){
                       exist = true;
                    }
                }
            }
            if(!exist) l.add(new Relation(l2.get(i).getName(),l2.get(i).getRel(),l2.get(i).getSrel()));
        }
    }


    private int getNumbPerson(String e){
        if(!persons.isEmpty()) {
            for (int i = 0; i < persons.size(); ++i) {
                if (persons.get(i).getName().equalsIgnoreCase(e)) {
                    return i;
                }
            }
        }
        return -1;
    }

}
