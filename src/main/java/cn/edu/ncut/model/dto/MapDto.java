package cn.edu.ncut.model.dto;

import cn.edu.ncut.model.view.VMap;

import java.util.List;

/**
 * Created by Ocean Lin on 2017/6/6.
 */
public class MapDto {

    private List<VMap> pressDemonstration;
    private List<VMap> pressParticipate;
    private List<VMap> issueDemonstration;
    private List<VMap> issueParticipate;

    public MapDto() {
    }

    public MapDto(List<VMap> pressDemonstration, List<VMap> pressParticipate, List<VMap> issueDemonstration, List<VMap> issueParticipate) {
        this.pressDemonstration = pressDemonstration;
        this.pressParticipate = pressParticipate;
        this.issueDemonstration = issueDemonstration;
        this.issueParticipate = issueParticipate;
    }

    public List<VMap> getPressDemonstration() {
        return pressDemonstration;
    }

    public void setPressDemonstration(List<VMap> pressDemonstration) {
        this.pressDemonstration = pressDemonstration;
    }

    public List<VMap> getPressParticipate() {
        return pressParticipate;
    }

    public void setPressParticipate(List<VMap> pressParticipate) {
        this.pressParticipate = pressParticipate;
    }

    public List<VMap> getIssueDemonstration() {
        return issueDemonstration;
    }

    public void setIssueDemonstration(List<VMap> issueDemonstration) {
        this.issueDemonstration = issueDemonstration;
    }

    public List<VMap> getIssueParticipate() {
        return issueParticipate;
    }

    public void setIssueParticipate(List<VMap> issueParticipate) {
        this.issueParticipate = issueParticipate;
    }
}
