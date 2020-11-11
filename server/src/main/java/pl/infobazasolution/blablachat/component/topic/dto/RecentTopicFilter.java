package pl.infobazasolution.blablachat.component.topic.dto;

public class RecentTopicFilter {

    private Integer numberOfTopics;
    private Integer startIndex;

    public Integer getNumberOfTopics() {
        return numberOfTopics;
    }

    public void setNumberOfTopics(Integer numberOfTopics) {
        this.numberOfTopics = numberOfTopics;
    }

    public Integer getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(Integer startIndex) {
        this.startIndex = startIndex;
    }
}
