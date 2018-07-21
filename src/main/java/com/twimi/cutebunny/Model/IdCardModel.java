package com.twimi.cutebunny.Model;

import lombok.Data;
import org.json.JSONObject;

@Data
public class IdCardModel {
    private String id;
    private String name;
    private String sex;
    private String address;
    private String nation;
    private String hash;
    private String blockHash;

    public IdCardModel() {

    }

    public IdCardModel(JSONObject object) {
        this.name = object.optString("name");
        this.id = object.getString("id");
        this.sex = object.getString("sex");
        this.nation = object.getString("nation");
        this.address = object.getString("address");
        this.hash = object.getString("hash");
    }

    public String toJSON() {
        return String.format("{\"id\":\"%s\",\"name\":\"%s\",\"sex\":\"%s\",\"nation\":\"%s\",\"address\":\"%s\",\"hash\":\"%s\"}", id, name, sex, nation, address, hash);
    }
}
