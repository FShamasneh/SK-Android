package com.firas.android.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;



public class Tuple<Params> {

    private List<Object> tupleList;

    public Tuple(Params... tulpe){
        tupleList = new ArrayList<Object>(Arrays.asList(tulpe));
    }


    public List<Object> getTuples(){
        return tupleList;
    }

    public Object getTuple(int index){
        if(tupleList.size()<index+1) return null;
        return tupleList.get(index);
    }

    public Tuple replaceTuple(int index,Object object){
        if(tupleList.size()<index+1) return null;
        tupleList.remove(index);
        tupleList.add(index,object);
        return this;
    }

    public boolean containsSubTuple(Tuple sub){
        for (Object o: sub.getTuples()){
            if(!this.tupleList.contains(o)) return false;
        }
        return true;
    }

    @Override
    public String toString(){
        return this.tupleList+"";
    }
}
