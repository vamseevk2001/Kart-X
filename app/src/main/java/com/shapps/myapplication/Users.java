package com.shapps.myapplication;

public class Users {
   private String uid;
   private String name;
   private String img;

    public Users(String uid, String name, String img) {
        this.uid = uid;
        this.name = name;
        this.img = img;
    }


    public String getUid(){
       return this.uid;
   }

    public String getName() {
        return name;
    }

    public String getImg() {
        return img;
    }



    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImg(String img) {
        this.img = img;
    }

}
