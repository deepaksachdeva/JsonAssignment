package com.assignment.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ListResponse {

    @SerializedName("title")
    public String title;
    @SerializedName("rows")
    public List<Row> rows = null;
}
