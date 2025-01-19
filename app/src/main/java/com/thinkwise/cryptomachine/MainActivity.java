package com.thinkwise.cryptomachine;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.thinkwise.cryptomachine.activities.*;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("Cryptomachine");

        Button caesarButton = findViewById(R.id.button_caesar);
        Button vatsayanaButton = findViewById(R.id.button_vatsayana);
        Button vigenereButton = findViewById(R.id.button_vigenere);
        Button beaufortButton = findViewById(R.id.button_beaufort);
        Button autokeyButton = findViewById(R.id.button_autokey);
        Button playfairButton = findViewById(R.id.button_playfair);
        Button twoSquareButton = findViewById(R.id.button_two_square);
        Button fourSquareButton = findViewById(R.id.button_four_square);

        caesarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CaesarActivity.class);
                startActivity(intent);
            }
        });

        vatsayanaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, VatsayanaActivity.class);
                startActivity(intent);
            }
        });

        vigenereButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, VigenereActivity.class);
                startActivity(intent);
            }
        });

        beaufortButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, BeaufortActivity.class);
                startActivity(intent);
            }
        });

        autokeyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AutokeyActivity.class);
                startActivity(intent);
            }
        });

        playfairButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PlayfairActivity.class);
                startActivity(intent);
            }
        });

        twoSquareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TwoSquareActivity.class);
                startActivity(intent);
            }
        });

        fourSquareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FourSquareActivity.class);
                startActivity(intent);
            }
        });


    }
}
