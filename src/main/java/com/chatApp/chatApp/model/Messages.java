package com.chatApp.chatApp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document
@Data
public class Messages {
    private String username;
    private String message;
    @JsonIgnoreProperties(ignoreUnknown = true)
    private Date date=new Date();
}
