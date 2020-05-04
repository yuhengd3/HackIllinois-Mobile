package com.example.hackillinoismobile;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    static final int FRIDAY_EPOCH = 1582869600; // Feb 28 2020 00:00:00 CDT
    static final int DAY_EPOCH = 24 * 60 * 60;
    private List<List<Event>> eventListByDay = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RequestQueue queue = Volley.newRequestQueue(this);

        String url = "https://api.hackillinois.org/";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url
                + "event/", null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                ObjectMapper mapper = new ObjectMapper();
                try {
                    List<Event> eventList = mapper.readValue(
                            response.toString(), EventList.class).getEvents();
                    separateEventListByDay(eventList);
                    addButtons();
                    findViewById(R.id.button_0).performClick();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }, error -> {});
        queue.add(jsonObjectRequest);
    }

    void separateEventListByDay(List<Event> eventList) {
        eventList.sort((a, b) -> a.getStartTime() - b.getStartTime());

        for (int i = 0; i < 3; i++) {
            List<Event> events = new ArrayList<>();
            for (Event event : eventList) {
                if (event.getStartTime() < FRIDAY_EPOCH + i * DAY_EPOCH) {
                    continue;
                } else if (event.getStartTime() >= FRIDAY_EPOCH + (i + 1) * DAY_EPOCH) {
                    break; // because eventList has been sorted
                }
                events.add(event);
            }
            eventListByDay.add(events);
        }
    }

    void addButtons() {
        LinearLayout eventsLinearLayout = findViewById(R.id.events_linear_layout);

        for (int i = 0; i < 3; i++) {
            int dayIndex = i; // effectively final
            Button button = findViewById(
                    getResources().getIdentifier("button_" + i, "id", getPackageName()));
            button.setOnClickListener(v -> {
                eventsLinearLayout.removeAllViews();
                
                for (Event event : eventListByDay.get(dayIndex)) {
                    View eventChunk = getLayoutInflater().inflate(R.layout.chunk_event,
                            eventsLinearLayout, false);
                    // name
                    ((TextView) eventChunk.findViewById(R.id.event_name)).setText(event.getName());
                    // location
                    StringBuilder builder = new StringBuilder();
                    for (Location location : event.getLocations()) {
                        builder.append(location.getDescription());
                    }
                    ((TextView) eventChunk.findViewById(R.id.event_location)).setText(builder.toString());
                    // description
                    ((TextView) eventChunk.findViewById(R.id.event_description))
                            .setText(event.getDescription());

                    eventsLinearLayout.addView(eventChunk);
                }
            });
        }
    }
}
