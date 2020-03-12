package com.example.accidentsms;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

   Button add;
   Button show;
   Button direction_s;
   Button notifi;
   Button help;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);

        toolbar.setTitle("Dashboard");

        setSupportActionBar(toolbar);


        add=findViewById(R.id.add_id);

        show=findViewById(R.id.show_id);

        direction_s=findViewById(R.id.direction_id);

        notifi=findViewById(R.id.notif_id);

        help=findViewById(R.id.picker_id);


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,AddAccident.class));
            }
        });

        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,Accident.class));
            }
        });

        direction_s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,Directions.class));
            }
        });

        notifi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,notification.class));
            }
        });

        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,guide.class));
            }
        });
    }


}
