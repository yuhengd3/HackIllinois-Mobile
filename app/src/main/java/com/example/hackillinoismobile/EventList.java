package com.example.hackillinoismobile;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "events"
})
public class EventList {

    @JsonProperty("events")
    private List<Event> events = null;

    /**
     * No args constructor for use in serialization
     *
     */
    public EventList() {
    }

    /**
     *
     * @param events
     */
    public EventList(List<Event> events) {
        super();
        this.events = events;
    }

    @JsonProperty("events")
    public List<Event> getEvents() {
        return events;
    }

    @JsonProperty("events")
    public void setEvents(List<Event> events) {
        this.events = events;
    }

    public EventList withEvents(List<Event> events) {
        this.events = events;
        return this;
    }

}