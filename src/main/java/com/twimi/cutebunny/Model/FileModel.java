package com.twimi.cutebunny.Model;

import lombok.Data;

@Data
public class FileModel {
    private int id;
    private String username;
    private String filename;
    private String filehash;
    private String blockhash;

    public FileModel() {

    }

    public FileModel(String name, String hash, String blockHash) {
        this.filename = name;
        this.filehash = hash;
        this.blockhash = blockHash;
    }

    public FileModel(String username, String filename, String filehash, String blockhash) {
        this.username = username;
        this.filename = filename;
        this.filehash = filehash;
        this.blockhash = blockhash;
    }

}
