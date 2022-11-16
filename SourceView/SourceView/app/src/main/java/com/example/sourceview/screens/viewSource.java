package com.example.sourceview.screens;

import static com.example.sourceview.MainActivity.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.widget.Toast;

import com.example.sourceview.MainActivity;
import com.example.sourceview.databinding.ActivityViewSourceBinding;
import com.example.sourceview.interfaces.OnTaskFinished;
import com.example.sourceview.utilities.RetrieveSiteData;

public class viewSource extends AppCompatActivity {
   private ActivityViewSourceBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityViewSourceBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.tvSourceCode.setMovementMethod(new ScrollingMovementMethod());
        try {
            new RetrieveSiteData(new OnTaskFinished() {
                @Override
                public void onFeedRetrieved(String feeds) {
                    Log.i(TAG, feeds);
                    binding.tvSourceCode.setText(feeds);
                }
            }).execute(MainActivity.enteredURL);
        }
        catch (Exception e){
            Toast.makeText(this, "Enter A Valid Url", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(viewSource.this,MainActivity.class));
        }
    }
}
