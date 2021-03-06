package com.github.camille.client.core.cmd;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Create by yster@foxmail.com 2018/11/9 0009 22:32
 */
class InputStreamRunnable implements Runnable {
    private BufferedReader bReader = null;

    InputStreamRunnable(InputStream is, String type) {
        try {
            bReader = new BufferedReader(new InputStreamReader(new BufferedInputStream(is), "UTF-8"));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void run() {
        try {
            // 错误输出流直接不处理
            bReader.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
