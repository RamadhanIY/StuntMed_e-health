package com.example.myapplication.Explorer;

import java.util.List;

public class ParentModelClassPost {


    String title;
    List<ChildModelClassPost> childModelClassListPost;


    public ParentModelClassPost(String title, List<ChildModelClassPost> childModelClassListPost){
        this.title = title;
        this.childModelClassListPost = childModelClassListPost;
    }


}
