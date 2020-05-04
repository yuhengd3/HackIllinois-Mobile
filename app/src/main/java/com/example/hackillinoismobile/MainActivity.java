package com.example.hackillinoismobile;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
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
import org.w3c.dom.Text;

import java.net.URL;
import java.security.spec.ECField;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<Event> eventList;
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
                    System.out.println("haha");
                    eventList = mapper.readValue(response.toString(), EventList.class).getEvents();
                    eventList.sort((a, b) -> a.getStartTime() - b.getStartTime());
                    System.out.println(eventList.get(1).getDescription());
                    showEvent();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(jsonObjectRequest);
    }

    void showEvent() {
        LinearLayout eventsLinearLayout = findViewById(R.id.events_linear_layout);

        System.out.println("dfjskajfl");
        System.out.println(eventList);

        for (Event event : eventList) {
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
    }
}
