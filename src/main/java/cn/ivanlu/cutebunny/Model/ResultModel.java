package cn.ivanlu.cutebunny.Model;

import lombok.Data;

@Data
public class ResultModel {
    private String message;
    private IdCardModel expect;
    private IdCardModel actual;

    public ResultModel(String message) {
        this.message = message;
    }

    public ResultModel(IdCardModel expect, IdCardModel actual) {
        this.message = null;
        this.expect = expect;
        this.actual = actual;
    }
}
