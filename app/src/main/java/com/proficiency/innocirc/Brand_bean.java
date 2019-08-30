package com.proficiency.innocirc;

public class Brand_bean {
    public String getImage() {
        return image;
    }

    public Brand_bean(String image, String name) {
        this.image = image;
        this.name = name;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private  String image;
    private  String name;


}
