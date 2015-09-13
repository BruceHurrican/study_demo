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

package com.bruce.study.java.exercise;

/**
 * java method snippet
 * Created by BruceHurrican on 2015/5/24.
 */
public class KKtest01 {
    public static void main(String[] args) {
        KKmethod k1 = new KKmethod();
        k1.method5();
    }

    private static class KKmethod {
        private  void method5(){
            String aa = "";
            System.out.println(aa.length());
            int b = 10;
            System.out.println(b<<10);
        }
        private void method4() {
            String TAG = KKmethod.class.getSimpleName();
            System.out.println(TAG);
        }

        private void method3() {
            Float aa = 12.23F;
            System.out.println(aa.intValue());
        }

        private void method2() {
            int aa = 2048;
            System.out.println(aa >> 10);
        }

        private void method1() {
            String str = "";
            str = String.format("adbc  %S jj @ %s", 222, "234");
            System.out.println(str);
        }
    }
}
