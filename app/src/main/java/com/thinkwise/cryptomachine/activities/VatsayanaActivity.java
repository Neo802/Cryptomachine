package com.thinkwise.cryptomachine.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.thinkwise.cryptomachine.*;
import com.thinkwise.cryptomachine.algorithms.Vatsayana;

public class VatsayanaActivity extends AppCompatActivity {

    private Vatsayana vatsayana;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            // Navigate back to MainActivity
            Intent intent = new Intent(VatsayanaActivity.this, MainActivity.class);
            //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            //finish(); // Close the current activity
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vatsayana);

        // Enable the back button in the ActionBar
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        vatsayana = new Vatsayana("QWERTYUIOPLKJHGFDSAZXCVBNM");

        EditText inputText = findViewById(R.id.input_text);
        TextView resultText = findViewById(R.id.result_text);

        Button encryptButton = findViewById(R.id.button_encrypt);
        Button decryptButton = findViewById(R.id.button_decrypt);
        Button cryptoButton = findViewById(R.id.button_cryptoanalysis);

        encryptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = inputText.getText().toString();
                String result = vatsayana.encrypt(text, "");
                resultText.setText("Encrypted Text: " + result);
            }
        });

        decryptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = inputText.getText().toString();
                String result = vatsayana.decrypt(text, "");
                resultText.setText("Decrypted Text: " + result);
            }
        });

        cryptoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resultText.setText("Cryptoanalysis for Vatsayana Cipher is context-dependent.");
            }
        });
    }
}
