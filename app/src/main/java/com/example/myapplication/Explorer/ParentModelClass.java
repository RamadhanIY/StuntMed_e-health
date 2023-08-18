package com.example.myapplication.Explorer;

import com.example.myapplication.Explorer.ChildModelClass;

import java.util.List;

public class ParentModelClass {

    String title;
    List<ChildModelClass> childModelClassList;

    public ParentModelClass(String title,List<ChildModelClass> childModelClassList){
        this.title = title;
        this.childModelClassList = childModelClassList;
    }


}
