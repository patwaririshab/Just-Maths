package com.rishabapps.justmaths2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //This will find the button by ID
        ImageButton btnGame = findViewById(R.id.btnGame);
        ImageButton btnShare = findViewById(R.id.btnShare);
        ImageButton btnRate = findViewById(R.id.btnRate);

        //This places an onClick Listener on the button, which upon click
        //will execute onClick (method)
        btnGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, GameActivity.class);
                startActivity(i);

            }
         });

        btnShare.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(intent.EXTRA_TEXT, "Just Maths - Learn Maths. https://www.play.google.com");
                startActivity(intent);
            }
        });

        btnRate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"Open Google Play landing page",Toast.LENGTH_LONG).show();
            }
        });


    }
}
