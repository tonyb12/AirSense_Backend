package de.tonyb12.AirSense_Backend.managers;

import org.springframework.http.*;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


import java.util.HashMap;
import java.util.Map;

@Component
public class DiscordMessegingManager {

    // yup it sucks thats hardcoded i know application.properties and injection from spring boot doesnt work correctly maybe have to do that manually
    private String channelId = "1297894829954437190";
    private String clientId = "1297896535693987902";
    private String permissions = "283467841536";
    private String scope = "bot";
    private String createWebHookUrl = "https://discordapp.com/api/channels/1297894829954437190/webhooks";
    private String webhookUrl = "https://discord.com/api/webhooks/1297894889815675021/aDjlGkdzPZiO5njdY2l7ktY2_c8URhLYwPpEIyo76Hsct8c2RXhIJVCWwN7xTZZ0s5PM";


    // the bot toke has to be in the properties file same as above
    public String createDiscordWebhook(){

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", "Bot " + "MTI5Nzg5NjUzNTY5Mzk4NzkwMg.GhGyz9.QD1st4fJbkBvHwfhYPWRLbPY8hKBNcKU5wyx0s");
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
