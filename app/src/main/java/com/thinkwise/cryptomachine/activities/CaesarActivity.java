package com.thinkwise.cryptomachine.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.thinkwise.cryptomachine.MainActivity;
import com.thinkwise.cryptomachine.R;
import com.thinkwise.cryptomachine.algorithms.Caesar;

public class CaesarActivity extends AppCompatActivity {

    private Caesar caesar;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            // Navigate back to MainActivity
            Intent intent = new Intent(CaesarActivity.this, MainActivity.class);
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
        setContentView(R.layout.activity_caesar);

        // Enable the back button in the ActionBar
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        caesar = new Caesar();

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
                try {
                    String encryptedText = caesar.encrypt(text, key);
                    resultText.setText("Encrypted Text: " + encryptedText);
                } catch (NumberFormatException e) {
                    resultText.setText("Invalid key. Please enter a number.");
                }
            }
        });

        decryptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = inputText.getText().toString();
                String key = inputKey.getText().toString();
                try {
                    String decryptedText = caesar.decrypt(text, key);
                    resultText.setText("Decrypted Text: " + decryptedText);
                } catch (NumberFormatException e) {
                    resultText.setText("Invalid key. Please enter a number.");
                }
            }
        });

        cryptoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = inputText.getText().toString();
                StringBuilder analysis = new StringBuilder("Cryptoanalysis Results:\n");
                for (int key = 1; key < 26; key++) {
                    String decryptedText = caesar.decrypt(text, String.valueOf(key));
                    analysis.append("Key ").append(key).append(": ").append(decryptedText).append("\n");
                }
                resultText.setText(analysis.toString());
            }
        });
    }
}
