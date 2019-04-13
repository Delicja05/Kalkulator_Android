package com.example.aliszja.kalkulator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button but_simple = findViewById(R.id.button_simple);
        Button but_advanced = findViewById(R.id.button_advanced);
        Button but_about = findViewById(R.id.button_about);
        Button but_exit = findViewById(R.id.button_exit);

        View.OnClickListener handler = new View.OnClickListener(){
            public void onClick(View v) {

                switch (v.getId()) {
                    case R.id.button_simple:
                        startActivity(new Intent(v.getContext(), SimpleCalculator.class));
                        break;
                    case R.id.button_advanced:
                        startActivity(new Intent(v.getContext(), AdvancedCalculator.class));
                        break;
                    case R.id.button_about:
                        startActivity(new Intent(v.getContext(), AboutCalculator.class));
                        break;
                    case R.id.button_exit:
                        finish();
                        System.exit(0);
                        break;
                }
            }
        };

        but_simple.setOnClickListener(handler);
        but_advanced.setOnClickListener(handler);
        but_about.setOnClickListener(handler);
        but_exit.setOnClickListener(handler);
    }
}
