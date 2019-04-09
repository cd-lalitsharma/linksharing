package com.ttn.linksharing.co;

import com.ttn.linksharing.entity.Topics;

public class PostsCo {
    
    String postName;
    String postDescription;
    String location;
    String type;
    Topics topic;
    
    public Topics getTopic() {
        return topic;
    }
    
    public void setTopic(Topics topic) {
        this.topic = topic;
    }
    
    public String getPostName() {
        return postName;
    }
    
    public void setPostName(String postName) {
        this.postName = postName;
    }
    
    public String getPostDescription() {
        return postDescription;
    }
    
    public void setPostDescription(String postDescription) {
        this.postDescription = postDescription;
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
                "postName='" + postName + '\'' +
                ", postDescription='" + postDescription + '\'' +
                ", location='" + location + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
