/*
 * Copyright 2015 JoinLinking company
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.bruce.study.demo.studydata.fragment.shake;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.bruce.study.demo.base.BaseFragment;

/**
 * 摇一摇 功能
 * Created by BruceHurrican on 2015/11/21.
 */
public class ShakeFragment extends BaseFragment {
    private SensorManager sensorManager;
    private Vibrator vibrator;

    @Override
    public String getTAG() {
        return "ShakeFragment->";
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        TextView tv = new TextView(getActivity());
        tv.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        tv.setText("摇一摇");
        tv.setGravity(Gravity.CENTER);
        return tv;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        sensorManager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);
        vibrator = (Vibrator) getActivity().getSystemService(Context.VIBRATOR_SERVICE);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (null != sensorManager) {
            sensorManager.registerListener(myListener, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    public void onPause() {
        if (null != sensorManager) {
            sensorManager.unregisterListener(myListener);
        }
        super.onPause();
    }

    private SensorEventListener myListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            int sensorType = event.sensor.getType();
            float[] values = event.values;
            if (sensorType == Sensor.TYPE_ACCELEROMETER) {
                if (Math.abs(values[0]) > 14 || Math.abs(values[1]) > 14 || Math.abs(values[2]) > 14) {
                    vibrator.vibrate(200);
                    showToastShort("手机摇一摇");
                }
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };
}
