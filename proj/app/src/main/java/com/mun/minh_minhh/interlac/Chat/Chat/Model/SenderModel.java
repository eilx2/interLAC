package com.mun.minh_minhh.interlac.Chat.Chat.Model;

/**
 * Created by Minh_Minhh on 17.05.17.
 */

public class SenderModel {
    public String response;
    public String id;
    public int results;
    public String msg;

    public SenderModel (String response, String id, int results, String msg){
        this.response = response;
        this.id = id;
        this.results = results;
        this.msg = msg;
    }

    public SenderModel(){
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getResults() {
        return results;
    }

    public void setResults(int results) {
        this.results = results;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
