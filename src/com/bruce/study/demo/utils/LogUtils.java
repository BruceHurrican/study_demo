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

package com.bruce.study.demo.utils;

import android.os.Environment;
import android.text.TextUtils;
import com.bruce.study.demo.log.Logs;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Log 工具，类似 android.util.Log. tag 自动产生，格式：
 * customTagPrefix:className.methodName(Line:lineNumber),
 * customTagPrefix 为空时只输出：className.methodName(Line:lineNumber).
 * 参考自 http://blog.csdn.net/finddreams
 * Created by BruceHurrican on 2015/12/9.
 */
public class LogUtils {
    public static String customTagPrefix = "kk";
    private static final String ROOT = Environment.getExternalStorageDirectory().getPath()+"/kk/";// SD卡中的根目录
    public static final String PATH_LOG_INFO = ROOT +"log/";

    private LogUtils() {
    }
    private static boolean ISDEBUG = true; // 日志开关


    public static AndroidCustomLog androidCustomLog;
    public interface AndroidCustomLog{
        void v(String tag,String content);
        void v(String tag,String content,Throwable throwable);
        void d(String tag,String content);
        void d(String tag,String content,Throwable throwable);
        void i(String tag,String content);
        void i(String tag,String content,Throwable throwable);
        void w(String tag,String content);
        void w(String tag,Throwable throwable);
        void w(String tag,String content,Throwable throwable);
        void e(String tag,String content);
        void e(String tag,String content,Throwable throwable);
        void wtf(String tag,String content);
        void wtf(String tag,Throwable throwable);
        void wtf(String tag,String content,Throwable throwable);
    }

    public static void v(String content){
        if (!ISDEBUG){
            return;
        }
        StackTraceElement callerStackTraceElement = getCallerStackTraceElement();
        String tag = generateTag(callerStackTraceElement);
        if (null != androidCustomLog){
            androidCustomLog.v(tag,content);
        } else {
            Logs.v(tag,content);
        }
    }
    public static void v(String content,Throwable throwable){
        if (!ISDEBUG){
            return;
        }
        StackTraceElement callerStackTraceElement = getCallerStackTraceElement();
        String tag = generateTag(callerStackTraceElement);
        if (null != androidCustomLog){
            androidCustomLog.v(tag, content, throwable);
        } else {
            Logs.v(tag, content);
        }
    }
    public static void d(String content){
        if (!ISDEBUG){
            return;
        }
        StackTraceElement callerStackTraceElement = getCallerStackTraceElement();
        String tag = generateTag(callerStackTraceElement);
        if (null != androidCustomLog){
            androidCustomLog.d(tag, content);
        } else {
            Logs.d(tag, content);
        }
    }
    public static void d(String content,Throwable throwable){
        if (!ISDEBUG){
            return;
        }
        StackTraceElement callerStackTraceElement = getCallerStackTraceElement();
        String tag = generateTag(callerStackTraceElement);
        if (null != androidCustomLog){
            androidCustomLog.d(tag, content, throwable);
        } else {
            Logs.d(tag, content);
        }
    }
    public static void i(String content){
        if (!ISDEBUG){
            return;
        }
        StackTraceElement callerStackTraceElement = getCallerStackTraceElement();
        String tag = generateTag(callerStackTraceElement);
        if (null != androidCustomLog){
            androidCustomLog.i(tag, content);
        } else {
            Logs.i(tag, content);
        }
    }
    public static void i(String content,Throwable throwable){
        if (!ISDEBUG){
            return;
        }
        StackTraceElement callerStackTraceElement = getCallerStackTraceElement();
        String tag = generateTag(callerStackTraceElement);
        if (null != androidCustomLog){
            androidCustomLog.i(tag, content, throwable);
        } else {
            Logs.i(tag, content);
        }
    }
    public static void w(String content){
        if (!ISDEBUG){
            return;
        }
        StackTraceElement callerStackTraceElement = getCallerStackTraceElement();
        String tag = generateTag(callerStackTraceElement);
        if (null != androidCustomLog){
            androidCustomLog.w(tag, content);
        } else {
            Logs.w(tag, content);
        }
    }
    public static void w(Throwable throwable){
        if (!ISDEBUG){
            return;
        }
        StackTraceElement callerStackTraceElement = getCallerStackTraceElement();
        String tag = generateTag(callerStackTraceElement);
        if (null != androidCustomLog){
            androidCustomLog.w(tag, throwable);
        } else {
            Logs.w(tag, throwable.toString());
        }
    }
    public static void w(String content,Throwable throwable){
        if (!ISDEBUG){
            return;
        }
        StackTraceElement callerStackTraceElement = getCallerStackTraceElement();
        String tag = generateTag(callerStackTraceElement);
        if (null != androidCustomLog){
            androidCustomLog.w(tag, content, throwable);
        } else {
            Logs.w(tag, content);
        }
    }
    public static void wtf(String content){
        if (!ISDEBUG){
            return;
        }
        StackTraceElement callerStackTraceElement = getCallerStackTraceElement();
        String tag = generateTag(callerStackTraceElement);
        if (null != androidCustomLog){
            androidCustomLog.wtf(tag, content);
        } else {
            Logs.w(tag, content);
        }
    }
    public static void wtf(Throwable throwable){
        if (!ISDEBUG){
            return;
        }
        StackTraceElement callerStackTraceElement = getCallerStackTraceElement();
        String tag = generateTag(callerStackTraceElement);
        if (null != androidCustomLog){
            androidCustomLog.wtf(tag, throwable);
        } else {
            Logs.w(tag, throwable.toString());
        }
    }
    public static void wtf(String content,Throwable throwable){
        if (!ISDEBUG){
            return;
        }
        StackTraceElement callerStackTraceElement = getCallerStackTraceElement();
        String tag = generateTag(callerStackTraceElement);
        if (null != androidCustomLog){
            androidCustomLog.wtf(tag, content, throwable);
        } else {
            Logs.w(tag, content);
        }
    }


    private static String generateTag(StackTraceElement callStackTraceElement){
        String tag = "%s.%s(Line:%d)";
        String stackTraceElementClassName = callStackTraceElement.getClassName(); // 获取到类名
        stackTraceElementClassName = stackTraceElementClassName.substring(stackTraceElementClassName.lastIndexOf(".") + 1);
        tag = String.format(tag, stackTraceElementClassName, callStackTraceElement.getMethodName(), callStackTraceElement.getLineNumber());
        tag = TextUtils.isEmpty(customTagPrefix) ? tag : (customTagPrefix+":"+tag);
        return tag;
    }

    private static StackTraceElement getCallerStackTraceElement(){
        return Thread.currentThread().getStackTrace()[4];
    }

    /**
     * 将日志写入本地文件
     * @param path  文件路径
     * @param tag  日志内容标签
     * @param msg
     */
    public static void log2file(String path, String tag, String msg){
        if (isSDAva()){
            Date date = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("", Locale.SIMPLIFIED_CHINESE);
            dateFormat.applyPattern("yyyyMMddHHmm");
            path += dateFormat.format(date)+".log";
            dateFormat.applyPattern("[yyyy-MM-dd HH:mm:ss]");
            String time = dateFormat.format(date);
            File file = new File(path);
            if (!file.exists()){
                createDipPath(path);
            }
            BufferedWriter out = null;
            try {
                out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file,true)));
//                out.write(time+" "+tag+" "+msg+"\r\n");
                out.write(time+" "+tag+" "+msg);
                out.newLine();
                out.flush();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (null != out){
                    try {
                        out.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    /**
     * 根据文件路径，递归创建文件
     * @param file
     */
    public static void createDipPath(String file){
        String parentFile = file.substring(0,file.lastIndexOf("/"));
        File file1 = new File(file);
        File parent = new File(parentFile);
        if (!file1.exists()){
            parent.mkdirs();
            try {
                file1.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static boolean isSDAva(){
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED) || Environment.getExternalStorageDirectory().exists()){
            return true;
        } else {
            return false;
        }
    }
}
