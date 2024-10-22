package de.tonyb12.AirSense_Backend.managers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


import java.util.HashMap;
import java.util.Map;

@Component
public class DiscordMessegingManager {

    @Value("${discord.api.channelId}")
    private String channelId;


    @Value("${discord.api.clientId}")
    private String clientId;
    @Value("${discord.api.permissions}")
    private String permissions;
    @Value("${discord.api.scope}")
    private String scope;
    @Value("${discord.api.createwebhookurl}")
    private String createWebHookUrl;
    @Value("${discord.api.webhookurl}")
    private String webhookUrl;
    @Value("${discord.api.headervalue}")
    private String headerValue;

    @Autowired
    public DiscordMessegingManager(){


    }

    // the bot toke has to be in the properties file same as above
    public String createDiscordWebhook(){

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", headerValue);
        headers.add("Accept", "application/json");


        Map<String,String> requestBody = new HashMap<>();
        requestBody.put("name","webhookTest");

        HttpEntity<Map<String,String>> httpRequest = new HttpEntity<>(requestBody,headers);

        var response = restTemplate.exchange(createWebHookUrl, HttpMethod.POST,httpRequest,String.class);

        return response.getBody();

    }

    // sends a http Request to the webhook of discord
    public String sendDiscordAlert(String body){

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("content", body);
        HttpEntity<Map<String,String>> httpRequest = new HttpEntity<>(requestBody,headers);

        var response = restTemplate.exchange(webhookUrl, HttpMethod.POST,httpRequest,String.class);


        return response.getBody();
    }


    public String getCreateWebHookUrl() {
        return createWebHookUrl;
    }

    public void setCreateWebHookUrl(String createWebHookUrl) {
        this.createWebHookUrl = createWebHookUrl;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getPermissions() {
        return permissions;
    }

    public void setPermissions(String permissions) {
        this.permissions = permissions;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }
}
