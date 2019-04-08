package com.ttn.linksharing.co;

import com.ttn.linksharing.enums.VisibilityEnum;

public class TopicsCo {
    
    String topicName;
    
    VisibilityEnum visibility;
    
    
    public String getTopicName() {
        return topicName;
    }
    
    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }
    
    public VisibilityEnum getVisibility() {
        return visibility;
    }
    
    public void setVisibility(VisibilityEnum visibility) {
        this.visibility = visibility;
    }
    
    @Override
    public String toString() {
        return "TopicsCo{" +
                "topicName='" + topicName + '\'' +
                ", visibility='" + visibility + '\'' +
                '}';
    }
}
