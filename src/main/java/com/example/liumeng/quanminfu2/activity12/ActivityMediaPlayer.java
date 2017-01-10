package com.example.liumeng.quanminfu2.activity12;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.SeekBar;
import android.widget.Toast;

import com.example.liumeng.quanminfu2.R;
import com.example.liumeng.quanminfu2.Utils.LogUtils;
import com.example.liumeng.quanminfu2.Utils.ToastUtil;

import java.io.File;
import java.io.IOException;

public class ActivityMediaPlayer extends AppCompatActivity {

    private SeekBar mMedia_player_sb;
    private MusicState mState = MusicState.IDLE;
    private MediaPlayer mMediaPlayer;
    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_player);
        init();
    }

    private void init() {
        mMedia_player_sb = (SeekBar) findViewById(R.id.media_player_sb);
        mMedia_player_sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                LogUtils.d("seekbar正在滑动");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                //当开始滑动的时候清楚所有消息
                LogUtils.d("seekbar开始滑动");
                mHandler.removeCallbacksAndMessages(null);
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                LogUtils.d("seekbar停止滑动");
                //将seekbar对应进度设置到音乐上,当mstate为play的时候继续发送消息
                mMediaPlayer.seekTo(seekBar.getProgress());
                if (mState == MusicState.PALY) {
                    startHandlerTask();
                }
            }
        });
        //初始化MediaPlayer播放器
        File file = new File(Environment.getExternalStorageDirectory(),"test.mp3");
        LogUtils.d(file.exists()+"");
        mMediaPlayer = new MediaPlayer();
        //如何初始化mediaPlayer
        try {
            mMediaPlayer.setDataSource(this, Uri.fromFile(file));
            mMediaPlayer.prepare();
            mState = MusicState.PREPARED;
            //设置播放完成监听
            mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    //当曲循环
                    mp.seekTo(0);
                    mp.start();
                }
            });
            //给进度条设置最大值
            //获取音乐总长度毫秒值
            int duration = mMediaPlayer.getDuration();
            mMedia_player_sb.setMax(duration);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void play(View view) throws IOException {
        switch (mState) {
            case IDLE:
                ToastUtil.showToast(this,"还未初始化完毕!");
                break;
            case PREPARED:
                mMediaPlayer.start();
                mState = MusicState.PALY;

                break;
            case PALY:
                ToastUtil.showToast(this,"已经在播放了");
                break;
            case PAUSE:
                //暂停的时候再次播放也是调用start方法吗  是的
                mMediaPlayer.start();
                mState = MusicState.PALY;
                ToastUtil.showToast(this,"音乐马上开始");
                break;
            case STOP:
                mMediaPlayer.prepare();
                mMediaPlayer.seekTo(0);
                mMediaPlayer.start();
                break;
            default:
                break;
        }
         //实时更新seekbar的进度  发消息更新seekbar
        startHandlerTask();
    }

    private void startHandlerTask() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                LogUtils.d("线程名:"+Thread.currentThread().getName());
                //设置seekbar的进度
                int currentPosition = mMediaPlayer.getCurrentPosition();
                mMedia_player_sb.setProgress(currentPosition);
                //再次发送消息形成循环  如何发送这个消息
                mHandler.postDelayed(this, 1000);
            }
        },1000);
    }

    public void pause(View view) {
        switch (mState) {
            case PALY:
                mMediaPlayer.pause();
                Toast.makeText(this, "已经暂停了", Toast.LENGTH_SHORT).show();
                mState = MusicState.PAUSE;
                break;
            case PAUSE:
                Toast.makeText(this, "已经暂停过了，不要点了！", Toast.LENGTH_SHORT).show();
                break;
            case STOP:
                Toast.makeText(this, "都停止了，还点啥暂停，点点开始可以重新播放！", Toast.LENGTH_SHORT).show();
                break;

            default:
                break;
        }
        //把当前handler所有任务清空
        mHandler.removeCallbacksAndMessages(null);

    }

    public void stop(View view) {
        switch (mState) {
            case PALY:
                mMediaPlayer.stop();
                Toast.makeText(this, "已经停止了", Toast.LENGTH_SHORT).show();
                mState = MusicState.STOP;
                break;
            case PAUSE:
                mMediaPlayer.stop();
                mState = MusicState.STOP;
                Toast.makeText(this, "已经停止了！", Toast.LENGTH_SHORT).show();
                break;
            case STOP:
                Toast.makeText(this, "都停止了，还点啥停止！", Toast.LENGTH_SHORT).show();
                break;

            default:
                break;
        }
        //把当前handler所有任务清空
        mHandler.removeCallbacksAndMessages(null);
    }
    //用枚举定义5种状态
    //如何定义5种状态枚举,都是int 需要初始化吗
    enum MusicState{
       IDLE,PREPARED,PALY,PAUSE,STOP ;
    }
}
