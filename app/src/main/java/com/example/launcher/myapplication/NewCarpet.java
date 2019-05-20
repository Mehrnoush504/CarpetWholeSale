package com.example.launcher.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class NewCarpet extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_carpet);

        Button changeBtn = findViewById(R.id.changebtn);
        changeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ChangeNewCarpet.class);
                startActivity(intent);
            }
        });

        Button designBtn = findViewById(R.id.designbtn);
        designBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), DesignNewCarpet.class);
                startActivity(intent);
            }
        });
    }
}
