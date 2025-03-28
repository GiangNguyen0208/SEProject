package com.se_project.be.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserQuestionRequestDTO {
    int id;
    String yourJob;
    String levelNow;
    String target;
    String moreInfo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getYourJob() {
        return yourJob;
    }

    public void setYourJob(String yourJob) {
        this.yourJob = yourJob;
    }

    public String getLevelNow() {
        return levelNow;
    }

    public void setLevelNow(String levelNow) {
        this.levelNow = levelNow;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getMoreInfo() {
        return moreInfo;
    }

    public void setMoreInfo(String moreInfo) {
        this.moreInfo = moreInfo;
    }
}
