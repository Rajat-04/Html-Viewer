package com.example.sourceview.screens;

import static com.example.sourceview.MainActivity.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.sourceview.MainActivity;
import com.example.sourceview.databinding.ActivityViewPageBinding;

public class viewPage extends AppCompatActivity {
   private ActivityViewPageBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityViewPageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Log.i(TAG,MainActivity.enteredURL);
        try{
            binding.wbPageView.loadUrl(MainActivity.enteredURL);
        }
        catch (Exception e){
            Toast.makeText(this, "Enter A Valid Url", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(viewPage.this,MainActivity.class));
        }
    }
}