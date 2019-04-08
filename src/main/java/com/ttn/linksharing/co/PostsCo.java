package com.ttn.linksharing.co;

import com.ttn.linksharing.entity.Topics;

public class PostsCo {
    
    String linkName;
    String linkDescription;
    String location;
    String type;
    Topics topic;
    
    public Topics getTopic() {
        return topic;
    }
    
    public void setTopic(Topics topic) {
        this.topic = topic;
    }
    
    public String getLinkName() {
        return linkName;
    }
    
    public void setLinkName(String linkName) {
        this.linkName = linkName;
    }
    
    public String getLinkDescription() {
        return linkDescription;
    }
    
    public void setLinkDescription(String linkDescription) {
        this.linkDescription = linkDescription;
    }
    
    public String getLocation() {
        return location;
    }
    
    
    
    public void setLocation(String location) {
        this.location = location;
    }
    
    public String getType() {
        return type;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    
    @Override
    public String toString() {
        return "PostsCo{" +
                "linkName='" + linkName + '\'' +
                ", linkDescription='" + linkDescription + '\'' +
                ", location='" + location + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
