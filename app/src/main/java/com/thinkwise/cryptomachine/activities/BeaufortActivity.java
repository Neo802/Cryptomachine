package com.thinkwise.cryptomachine.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.thinkwise.cryptomachine.R;
import com.thinkwise.cryptomachine.algorithms.Beaufort;

public class BeaufortActivity extends AppCompatActivity {

    private Beaufort beaufort;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beaufort);

        beaufort = new Beaufort();

        EditText inputText = findViewById(R.id.input_text);
        EditText inputKey = findViewById(R.id.input_key);
        TextView resultText = findViewById(R.id.result_text);

        Button encryptButton = findViewById(R.id.button_encrypt);
        Button decryptButton = findViewById(R.id.button_decrypt);
        Button cryptoButton = findViewById(R.id.button_cryptoanalysis);

        encryptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = inputText.getText().toString();
                String key = inputKey.getText().toString();
                String result = beaufort.encrypt(text, key);
                resultText.setText("Encrypted Text: " + result);
            }
        });

        decryptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = inputText.getText().toString();
                String key = inputKey.getText().toString();
                String result = beaufort.decrypt(text, key);
                resultText.setText("Decrypted Text: " + result);
            }
        });

        cryptoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resultText.setText("Cryptoanalysis for Beaufort Cipher is context-dependent.");
            }
        });

        // Enable back button
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(android.view.MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}