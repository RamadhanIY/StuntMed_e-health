package com.stuntmed.stuntmed.Explorer;

import java.util.List;

public class ParentModelClass {


    String title;
    List<ChildModelClass> childModelClassList;


    public ParentModelClass(String title,List<ChildModelClass> childModelClassList){
        this.title = title;
        this.childModelClassList = childModelClassList;
    }


}
