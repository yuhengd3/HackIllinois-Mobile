package com.example.hackillinoismobile;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "name",
        "description",
        "startTime",
        "endTime",
        "locations",
        "sponsor",
        "eventType"
})
public class Event {

    @JsonProperty("id")
    private String id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("description")
    private String description;
    @JsonProperty("startTime")
    private int startTime;
    @JsonProperty("endTime")
    private int endTime;
    @JsonProperty("locations")
    private List<Location> locations = null;
    @JsonProperty("sponsor")
    private String sponsor;
    @JsonProperty("eventType")
    private String eventType;

    /**
     * No args constructor for use in serialization
     *
     */
    public Event() {
    }

    /**
     *
     * @param sponsor
     * @param name
     * @param description
     * @param startTime
     * @param locations
     * @param id
     * @param endTime
     * @param eventType
     */
    public Event(String id, String name, String description, int startTime, int endTime, List<Location> locations, String sponsor, String eventType) {
        super();
        this.id = id;
        this.name = name;
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
        this.locations = locations;
        this.sponsor = sponsor;
        this.eventType = eventType;
    }

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    public Event withId(String id) {
        this.id = id;
        return this;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    public Event withName(String name) {
        this.name = name;
        return this;
    }

    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    @JsonProperty("description")
    public void setDescription(String description) {
        this.description = description;
    }

    public Event withDescription(String description) {
        this.description = description;
        return this;
    }

    @JsonProperty("startTime")
    public int getStartTime() {
        return startTime;
    }

    @JsonProperty("startTime")
    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public Event withStartTime(int startTime) {
        this.startTime = startTime;
        return this;
    }

    @JsonProperty("endTime")
    public int getEndTime() {
        return endTime;
    }

    @JsonProperty("endTime")
    public void setEndTime(int endTime) {
        this.endTime = endTime;
    }

    public Event withEndTime(int endTime) {
        this.endTime = endTime;
        return this;
    }

    @JsonProperty("locations")
    public List<Location> getLocations() {
        return locations;
    }

    @JsonProperty("locations")
    public void setLocations(List<Location> locations) {
        this.locations = locations;
    }

    public Event withLocations(List<Location> locations) {
        this.locations = locations;
        return this;
    }

    @JsonProperty("sponsor")
    public String getSponsor() {
        return sponsor;
    }

    @JsonProperty("sponsor")
    public void setSponsor(String sponsor) {
        this.sponsor = sponsor;
    }

    public Event withSponsor(String sponsor) {
        this.sponsor = sponsor;
        return this;
    }

    @JsonProperty("eventType")
    public String getEventType() {
        return eventType;
    }

    @JsonProperty("eventType")
    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public Event withEventType(String eventType) {
        this.eventType = eventType;
        return this;
    }

}