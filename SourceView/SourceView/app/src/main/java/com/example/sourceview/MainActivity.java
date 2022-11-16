package com.example.sourceview;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.sourceview.databinding.ActivityMainBinding;
import com.example.sourceview.screens.viewPage;
import com.example.sourceview.screens.viewSource;

import java.net.URL;

public class MainActivity extends AppCompatActivity {
    private static final int PICKFILE_RESULT_CODE = 010;
    private ActivityMainBinding binding;
    public static String enteredURL="";
    public static final String TAG = "Rajat";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnShowSource.setBackgroundColor(getResources().getColor(android.R.color.background_light));
        binding.btnShowSource.setTextColor(getResources().getColor(android.R.color.background_dark));
    }

    public boolean checkTextBox(){
        Log.i(TAG,"cHECK TEXT BOX CLICKED");
        if(binding.etUrl.getText().toString().isEmpty()){

            Toast.makeText(this, "Enter Valid URL", Toast.LENGTH_SHORT).show();
            Log.i(TAG,"cHECK TEXT BOX CLICKED false");
            return false;
        }
        /* Try creating a valid URL */

        try {
            Log.i(TAG,"cHECK TEXT BOX CLICKED true");
            this.enteredURL = binding.etUrl.getText().toString();
            return true;
        }

        // If there was an Exception
        // while creating URL object
        catch (Exception e) {
            Toast.makeText(this, "Enter Valid URL", Toast.LENGTH_SHORT).show();
            return false;
        }


    }

    public void showSourceClicked(View view) {
        Log.i(TAG,"show source CLICKED");
        if(checkTextBox()) {

                startActivity(new Intent(MainActivity.this, viewSource.class));
        }

    }

    public void showPageClicked(View view) {
        Log.i(TAG,"show page CLICKED");
        if(checkTextBox()) {

            startActivity(new Intent(MainActivity.this, viewPage.class));
        }
    }

    public void LoadHtmlClicked(View view) {
        Log.i(TAG,"Load HTML CLICKED");
        Intent chooseFile = new Intent(Intent.ACTION_GET_CONTENT);
        chooseFile.setType("text/html|text/htm");
        chooseFile = Intent.createChooser(chooseFile, "Choose a file");
        startActivityForResult(chooseFile, PICKFILE_RESULT_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==PICKFILE_RESULT_CODE){
            Log.i(TAG,"on acitvity result"+data.toString());
            Uri uri = data.getData();
            String src = uri.getPath();
            this.enteredURL=src;
            startActivity(new Intent(MainActivity.this,viewPage.class));

        }
        else{
            Toast.makeText(this,"Choose a valid file",Toast.LENGTH_SHORT);
        }
    }
}