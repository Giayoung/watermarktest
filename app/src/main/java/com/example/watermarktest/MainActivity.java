package com.example.watermarktest;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView srcImg = findViewById(R.id.imageView);
        ImageView watermarkImg = findViewById(R.id.watermarkImage);
        Bitmap bitmap = imgToBitmap(R.drawable.field_6574455__340);
        watermarkImg.setImageBitmap(addWatermark(bitmap));
    }

    // src image to bitmap
    private Bitmap imgToBitmap(int drawable){
        Bitmap bitmap = BitmapFactory.decodeResource(this.getApplication().getResources(),drawable);
        return bitmap;
    }

    // add watermark bottom left
    private Bitmap addWatermark(Bitmap srcBitmap){
        Paint textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setColor(Color.WHITE);
        textPaint.setTextSize(50);
        textPaint.setDither(true);
        textPaint.setFilterBitmap(true);
        String text ="Hello!";
        //create new bitmap
        Bitmap newBitmap = null;
        Canvas canvas =null;
        try{
            Bitmap.Config bitmapConfig = srcBitmap.getConfig();
            if (bitmapConfig == null){
                bitmapConfig = Bitmap.Config.ARGB_8888;
            }
            newBitmap = srcBitmap.copy(bitmapConfig,true);
            canvas = new Canvas(newBitmap);
            float x = newBitmap.getWidth();
            float y = newBitmap.getHeight()-50;
            canvas.drawText(text,10,y,textPaint);
            canvas.save();
            canvas.restore();

        }catch (Exception e){
            Log.i("add watermark",e.toString());
        }

        return newBitmap;
    }
}