package com.example.liumeng.quanminfu2.activity12;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import com.example.liumeng.quanminfu2.R;

public class ActivityHearBeat extends AppCompatActivity {

    private ImageView mHear_beat_iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hear_beat);
        init();
    }

    private void init() {
        mHear_beat_iv = (ImageView) findViewById(R.id.hear_beat_iv);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.up);
        //获取屏幕的宽高
        Display defaultDisplay = getWindow().getWindowManager().getDefaultDisplay();
        final int width = defaultDisplay.getWidth();
        final int height = defaultDisplay.getHeight();
        final Bitmap newBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(newBitmap);
        canvas.drawBitmap(bitmap, new Matrix(), null);
        //将newBitmap显示到iv上
        mHear_beat_iv.setImageBitmap(newBitmap);
        mHear_beat_iv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_MOVE) {
                    int currentX = (int) event.getX();
                    int currentY = (int) event.getY();

                    int startX = currentX - 10 < 0 ? 0 : currentX - 10;
                    int endX = currentX + 10 > width ? width : currentX + 10;

                    int startY = currentY - 10 < 0 ? 0 : currentY - 10;
                    int endY = currentY + 10 > height ? height : currentY + 10;

                    //让上述区间内的像素点透明
                    for (int i = startX; i<endX; i++) {
                        for (int j = startY; j < endY ; j++) {
                            newBitmap.setPixel(i,j, Color.TRANSPARENT);
                        }
                    }
                    //将修改的结果设置到iv上
                    mHear_beat_iv.setImageBitmap(newBitmap);
                }
                return true;
            }
        });
    }
}
