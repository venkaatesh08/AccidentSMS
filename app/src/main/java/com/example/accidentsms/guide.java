package com.example.accidentsms;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class guide extends AppCompatActivity {

    private int currentImage = 0;

    Button next;
    Button prev;

    TextView dt;

    ImageView imageView;


    int[] images = { R.drawable.p3, R.drawable.p4, R.drawable.p5, R.drawable.p6, R.drawable.p7, R.drawable.p8, R.drawable.p9,R.drawable.p13,  R.drawable.p14 ,R.drawable.p15};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);

        Toolbar toolbar = findViewById(R.id.toolbar);

        toolbar.setTitle("Guide");

        setSupportActionBar(toolbar);


        next=findViewById(R.id.next_id);

        prev=findViewById(R.id.prev_id);

        imageView=findViewById(R.id.images);

        dt=findViewById(R.id.hints);

        prev.setEnabled(false);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(currentImage<10 && currentImage>-1)
                {
                    imageView.setImageResource(images[currentImage]);

                    currentImage++;

                    dt.setText(1+currentImage+"/11");

                    prev.setEnabled(true);
                }
                else
                {
                    next.setEnabled(false);
                }
            }
        });

        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(currentImage<10 && currentImage>0) {

                    imageView.setImageResource(images[currentImage]);

                    currentImage--;
                    dt.setText(1+currentImage + "/11");

                    next.setEnabled(true);
                }
                else {
                    prev.setEnabled(false);
                }

            }
        });






    }
}
