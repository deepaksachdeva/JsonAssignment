package com.assignment.pojo;

import com.google.gson.annotations.SerializedName;

public class Row {
    @SerializedName("title")
    public String title;
    @SerializedName("description")
    public String description;
    @SerializedName("imageHref")
    public String imageHref;
}
