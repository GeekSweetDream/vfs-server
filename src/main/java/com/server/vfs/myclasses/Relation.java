package com.server.vfs.myclasses;

public class Relation {
    private String name,rel,srel;

    public Relation(String name, String rel) {
        this.name = name;
        this.rel = rel;
    }

    public Relation(String name, String rel,String srel) {
        this.name = name;
        this.rel = rel;
        this.srel = srel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRel() {
        return rel;
    }

    public void setRel(String rel) {
        this.rel = rel;
    }

    public String getSrel() {
        return srel;
    }

    public void setSrel(String srel) {
        this.srel = srel;
    }
}
