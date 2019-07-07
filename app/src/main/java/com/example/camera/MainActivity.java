package com.example.camera;

import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    ImageView imageView;
    int IMAGE_REQUEST_CODE = 1000;      //should be unique to handle image capture

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.imageView);
    }

    public void takePhoto(View view) {

        //Creating a new intent to take photos from a Camera App
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        /*Checking if the any Camera app is present to handle taking pictures
        i.e. Take a photo with a camera app*/
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, IMAGE_REQUEST_CODE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == IMAGE_REQUEST_CODE) {
            switch (resultCode) {
                case RESULT_OK:
                    Log.e(TAG, "onActivityResult: Photo taken now handle the photo");

                    //Getting the thumbnail image from the key data
                    Bitmap bitmap = (Bitmap) data.getExtras().get("data");

                    //Setting the Bitmap inside our image view
                    imageView.setImageBitmap(bitmap);
                    break;

                case RESULT_CANCELED:
                    Log.e(TAG, "onActivityResult: User pressed the Cancel button");
                    break;

                default:
                    Log.e(TAG, "onActivityResult: This is default");
            }

        }
    }
}
