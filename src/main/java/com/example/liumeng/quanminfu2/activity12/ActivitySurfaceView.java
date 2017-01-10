package com.example.liumeng.quanminfu2.activity12;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.liumeng.quanminfu2.R;

import java.io.IOException;

/**
 * 需要权限吗
 *
 */
public class ActivitySurfaceView extends AppCompatActivity {

    private SurfaceView mSurfaceview_sv;
    private Camera mOpen;
    private ImageView mSurfaceview_iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_surface_view);
        mSurfaceview_sv = (SurfaceView) findViewById(R.id.surfaceview_sv);
        mSurfaceview_iv = (ImageView) findViewById(R.id.surfaceview_iv);
        SurfaceHolder holder = mSurfaceview_sv.getHolder();
        holder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                if (mOpen == null) {
                    mOpen = Camera.open();

                }
                //开启预览
                try {
                    mOpen.setPreviewDisplay(holder);
                    mOpen.startPreview();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                if (mOpen != null) {
                    mOpen.stopPreview();
                    //关闭摄像头
                    mOpen.release();
                    mOpen = null;
                }
            }
        });
    }

    public void takePicpure(View view) {
        if (mOpen==null) {
            Toast.makeText(this, "照相机没有打开，拍啥照！", Toast.LENGTH_SHORT).show();
            return;
        }
        mOpen.takePicture(null, null, new Camera.PictureCallback() {
            @Override
            public void onPictureTaken(byte[] data, Camera camera) {
                int length = data.length;
                Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, length);
                mSurfaceview_iv.setImageBitmap(bitmap);
                //重新开启预览
                mOpen.startPreview();
            }
        });
    }
}
