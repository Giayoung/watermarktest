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
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView srcImg = findViewById(R.id.imageView);
        ImageView watermarkImg = findViewById(R.id.watermarkImage);
        Button button = findViewById(R.id.button);
        Button reset = findViewById(R.id.resetButton);
        Bitmap bitmap = imgToBitmap(R.drawable.field_6574455__340);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    watermarkImg.setImageBitmap(addWatermark(bitmap,Color.WHITE,50,"@Gia"));
            }
        });
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                watermarkImg.setImageResource(R.drawable.field_6574455__340);
            }
        });
    }

    // src image to bitmap
    private Bitmap imgToBitmap(int drawable){
        Bitmap bitmap = BitmapFactory.decodeResource(this.getApplication().getResources(),drawable);
        return bitmap;
    }

    // add watermark bottom left
    private Bitmap addWatermark(Bitmap srcBitmap,int color,int size,String text){
        Paint textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setColor(color);
        textPaint.setTextSize(size);
        textPaint.setDither(true);
        textPaint.setFilterBitmap(true);
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