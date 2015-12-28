/*
 * Copyright (c) 2015.
 *   This document is just for Bruce's personal study.
 *   Some resources come from the Internet. Everyone can download and use it for study, but can
 *   not be used for commercial purpose. The author does not bear the
 *   corresponding disputes arising therefrom.
 *   Please delete within 24 hours after download.
 *   If you have good suggestions for this code, you can contact BurrceHurrican@foxmail.com.
 *   本文件为Bruce's个人学习android的demo, 其中所用到的代码来源于互联网，仅作为学习交流使用。
 *   任和何人可以下载并使用, 但是不能用于商业用途。
 *   作者不承担由此带来的相应纠纷。
 *   如果对本代码有好的建议，可以联系BurrceHurrican@foxmail.com
 */

package com.bruce.study.demo.studydata.demos60.mediaplayer_project;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import com.bruce.study.demo.R;
import com.bruce.study.demo.log.Logs;

import java.io.IOException;

/**
 * Created by BruceHurrican on 2015/9/4.
 */
public class MyMediaPlayerView extends SurfaceView implements SurfaceHolder.Callback, Runnable {
    public static final String TAG = "MyMediaPlayerView -->";
    // 声明音乐的状态常量
    private final int MEDIA_PLAYER_PAUSE = 0; // 暂停
    private final int MEDIA_PLAYER_PLAY = 1; // 播放中
    private final int MEDIA_PLAYER_STOP = 2; // 停止
    private SurfaceHolder surfaceHolder;
    private Paint paint;
    private boolean flag;
    private int mediaState = 0; // 音乐的当前状态
    private MediaPlayer mediaPlayer; // 声明一个音乐播放器
    private int currentTime; // 当前音乐播放的时间点
    private int musicMaxTime; // 当前音乐播放的总时间
    private int currentVol; // 当前音乐的音量大小
    private int setTime = 5000; // 快进、快退时间戳
    private AudioManager audioManager; // 播放器管理类

    public MyMediaPlayerView(Context context) {
        super(context);
        surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);
        paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setAntiAlias(true);
        setFocusable(true);
        // 实例化音乐播放器
        mediaPlayer = MediaPlayer.create(context, R.raw.demos60_music);
        // 设置循环播放（设置了循环，“OnCompleteListener”监听器无法监听音乐是否播放完成）
//        mediaPlayer.setLooping(true); // 设置循环播放
        musicMaxTime = mediaPlayer != null ? mediaPlayer.getDuration() : 0; // 获取音乐文件的总时间
        audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE); // 实例化管理器
        // 设置当前调整音量大小只是针对媒体音乐进行调整
        ((Activity) context).setVolumeControlStream(AudioManager.STREAM_MUSIC);
        try {
            if (null != mediaPlayer) {
                mediaPlayer.stop();
            }
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 绑定音乐完成监听器
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                if (mediaPlayer == mp) {
                    Logs.d(TAG, "音乐播放完成");
                }
            }
        });
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        flag = true;
        Thread thread = new Thread(this);
        thread.start();
    }

    private void myDraw() {
        Canvas canvas = null;
        try {
            canvas = surfaceHolder.lockCanvas();
            if (null != canvas) {
                canvas.drawColor(Color.GREEN);
                paint.setColor(Color.RED);
                paint.setTextSize(30);
                canvas.drawText("当前音量：" + currentVol, 50, 40, paint);
                canvas.drawText("当前播放的时间（毫秒）/总时间（毫秒）", 50, 70, paint);
                canvas.drawText(currentTime + "/" + musicMaxTime, 50, 100, paint);
                canvas.drawText("方向键中间按钮切换 暂停/开始", 50, 150, paint);
                canvas.drawText("方向键←键快退" + setTime / 1000 + "秒 ", 50, 180, paint);
                canvas.drawText("方向键→键快进" + setTime / 1000 + "秒 ", 50, 210, paint);
                canvas.drawText("方向键↑键增加音量 ", 50, 240, paint);
                canvas.drawText("方向键↓键减小音量", 50, 270, paint);
            }
        } catch (Exception e) {
            Logs.e(TAG, e.toString());
        } finally {
            if (null != canvas) {
                surfaceHolder.unlockCanvasAndPost(canvas);
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return true;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // 导航中键播放/暂停操作
        if (keyCode == KeyEvent.KEYCODE_DPAD_CENTER || keyCode == KeyEvent.KEYCODE_MENU) {
            try {
                switch (mediaState) {
                    // 当前处于播放的状态
                    case MEDIA_PLAYER_PLAY:
                        mediaPlayer.pause();
                        mediaState = MEDIA_PLAYER_PAUSE;
                        break;
                    // 当前处于暂停状态
                    case MEDIA_PLAYER_PAUSE:
                        mediaPlayer.start();
                        mediaState = MEDIA_PLAYER_PLAY;
                        break;
                    // 当前处于停止的状态
                    case MEDIA_PLAYER_STOP:
                        /*
                        使用android MediaPlayer 播放一段音乐时，有时会出现prepareasync called
                        in state 8 错误。以下方法可以避免这个异常出现，在播放之前先判断playerMusic是否
                        被占用，这样就不会报错了
                         */
                        if (mediaPlayer != null) {
                            mediaPlayer.pause();
                            mediaPlayer.stop();
                            mediaPlayer.prepare();
                            mediaPlayer.start();
                        }
                        mediaState = MEDIA_PLAYER_PLAY;
                        break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (keyCode == KeyEvent.KEYCODE_DPAD_UP) {
            audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, currentVol + 1, AudioManager.FLAG_PLAY_SOUND);
            //导航下键调整音乐播放声音变小
        } else if (keyCode == KeyEvent.KEYCODE_DPAD_DOWN) {
            audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, currentVol - 1, AudioManager.FLAG_PLAY_SOUND);
            //导航左键调整音乐播放时间倒退五秒
        } else if (keyCode == KeyEvent.KEYCODE_DPAD_LEFT) {
            if (currentTime - setTime <= 0) {
                mediaPlayer.seekTo(0);
            } else {
                mediaPlayer.seekTo(currentTime - setTime);
            }
            //导航右键调整音乐播放时间快进五秒
        } else if (keyCode == KeyEvent.KEYCODE_DPAD_RIGHT) {
            if (currentTime + setTime >= musicMaxTime) {
                mediaPlayer.seekTo(musicMaxTime);
            } else {
                mediaPlayer.seekTo(currentTime + setTime);
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    private void logic() {
        if (mediaPlayer != null) {
            // 获取当前音乐播放的时间
            currentTime = mediaPlayer.getCurrentPosition();
//            Logs.d(TAG, "当前音乐播放的时间-->" + currentTime);
            // 获取当前的音量值
            currentVol = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
//            Logs.d(TAG, "当前音量-->" + currentVol);
            // 获取当前的音量最大值
//            int valueMax = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
//            Logs.d(TAG, "当前音量最大值-->" + valueMax);
        } else {
            currentTime = 0;
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        flag = false;
        if (null != mediaPlayer) {
            mediaPlayer.stop();
            mediaPlayer.release();
        }
    }

    @Override
    public void run() {
        while (flag) {
            long start = System.currentTimeMillis();
            myDraw();
            logic();
            long end = System.currentTimeMillis();
            try {
                if (end - start < 50) {
                    Thread.sleep(50 - (end - start));
                }
            } catch (InterruptedException e) {
                Logs.e(TAG, e.toString());
            }
        }
    }
}
