package com.example.adrobnych.geocurr.model.entities;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "currencies")
public class GCCurrency {
    @DatabaseField(id = true)
    private String country;
    @DatabaseField(columnName = "code", canBeNull = false)
    private String code;
    @DatabaseField(columnName = "name", canBeNull = false, index = true, indexName = "name_index")
    private String name;
    @DatabaseField(columnName = "collected", indexName = "collected_flag_index")
    private boolean collected;

    public GCCurrency(){
    }

    public GCCurrency(String code, String name, String country, boolean collected) {
        this.code = code;
        this.name = name;
        this.country = country;
        this.collected = collected;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public boolean isCollected() {
        return collected;
    }

    public void setCollected(boolean collected) {
        this.collected = collected;
    }
}
