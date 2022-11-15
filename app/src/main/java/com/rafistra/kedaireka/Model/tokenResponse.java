package com.rafistra.kedaireka.Model;

import com.google.gson.annotations.SerializedName;

public class tokenResponse {

    @SerializedName("access_token")
    private String access_token;

    @SerializedName("token_type")
    private String token_type;

    @SerializedName("name")
    private String name;

    @SerializedName("nodeId")
    private String nodeId;

    public tokenResponse(){
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public void setToken_type(String token_type) {
        this.token_type = token_type;
    }

    public void setUserName(String userName) {this.name = userName;}

    public String getAccess_token() {
        return access_token;
    }

    public String getToken_type() {
        return token_type;
    }

    public String getUserName() {return name;}

    public String getName() {
        return name;
    }

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }
}
