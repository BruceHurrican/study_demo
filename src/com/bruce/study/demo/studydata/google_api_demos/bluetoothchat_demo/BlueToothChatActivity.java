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

package com.bruce.study.demo.studydata.google_api_demos.bluetoothchat_demo;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.*;
import android.view.inputmethod.EditorInfo;
import android.widget.*;
import com.bruce.study.demo.R;
import com.bruce.study.demo.base.BaseActivity;

/**
 * 谷歌api-10包下的蓝牙demo练习
 * Created by BruceHurrican on 2015/10/7.
 */
public class BlueToothChatActivity extends BaseActivity {
    // Message types send from the BluetoothChatService Handler
    public static final int MESSAGE_STATE_CHANGE = 1;
    public static final int MESSAGE_READ = 2;
    public static final int MESSAGE_WRITE = 3;
    public static final int MESSAGE_DEVICE_NAME = 4;
    public static final int MESSAGE_TOAST = 5;
    // Key names received from the BluetoothChatService Handler
    public static final String DEVICE_NAME = "device_name";
    public static final String TOAST = "toast";
    // Debugging
    private static final boolean D = true;
    // Intent request codes
    private static final int REQUEST_CONNECT_DEVICE_SECURE = 1;
    private static final int REQUEST_CONNECT_DEVICE_INSECURE = 2;
    private static final int REQUEST_ENABLE_BT = 3;

    // Layout Views
    private TextView tv_bt_title_left;
    private ListView mConversationView;
    private EditText mOutEditText;
    private Button mSendButton;

    // Name of the connected device
    private String mConnectedDeviceName = null;
    // Array adapter for the conversation thread
    private ArrayAdapter<String> mConversationArrayAdapter;
    // The Handler that gets information back from the BluetoothChatService
    private final Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MESSAGE_STATE_CHANGE:
                    if (D) {
                        logD("MESSAGE_STATE_CHANGE:" + msg.arg1);
                    }
                    switch (msg.arg1) {
                        case BlueToothChatService.STATE_CONNECTED:
                            tv_bt_title_left.setText(R.string.title_connected_to);
                            tv_bt_title_left.append(mConnectedDeviceName);
                            mConversationArrayAdapter.clear();
                            break;
                        case BlueToothChatService.STATE_CONNECTING:
                            tv_bt_title_left.setText(R.string.title_connecting);
                            break;
                        case BlueToothChatService.STATE_LISTEN:
                        case BlueToothChatService.STATE_NONE:
                            tv_bt_title_left.setText(R.string.title_not_connected);
                            break;
                    }
                    break;
                case MESSAGE_WRITE:
                    byte[] writeBuf = (byte[]) msg.obj;
                    // construct a string from the buffer
                    String writeMessage = new String(writeBuf);
                    mConversationArrayAdapter.add("Me: " + writeMessage);
                    break;
                case MESSAGE_READ:
                    byte[] readBuf = (byte[]) msg.obj;
                    // construct a string from the valid bytes in the buffer
                    String readMessage = new String(readBuf, 0, msg.arg1);
                    mConversationArrayAdapter.add(mConnectedDeviceName + ": " + readMessage);
                    break;
                case MESSAGE_DEVICE_NAME:
                    // save the connected device's name
                    mConnectedDeviceName = msg.getData().getString(DEVICE_NAME);
                    showToastShort("Connected to " + mConnectedDeviceName);
                    break;
                case MESSAGE_TOAST:
                    showToastShort(msg.getData().getString(TOAST));
                    break;
            }
        }
    };
    // String buffer for outgoing messages
    private StringBuffer mOutStringBuffer;
    // Local Bluetooth adapter
    private BluetoothAdapter mBluetoothAdapter = null;
    // Member object for the chat services
    private BlueToothChatService mChatService = null;
    // The action listener for the EditText widget, to listen for return key
    private TextView.OnEditorActionListener mWriteListener = new TextView.OnEditorActionListener() {
        @Override
        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
            // If the action is a key-up event on the return key, send the message
            if (actionId == EditorInfo.IME_NULL && event.getAction() == KeyEvent.ACTION_UP) {
                String message = v.getText().toString();
                sendMessage(message);
            }
            if (D) {
                logD("END onEditorAction");
            }
            return true;
        }
    };

    @Override
    public String getTAG() {
        return "BlueToothChatActivity -->";
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (D) {
            logE("+++ ON CREATE +++");
        }

        // Set up the window layout
//        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
//        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.bt_custom_title);
        setContentView(R.layout.bluetooth_activity);


        // Set up the custom title
        tv_bt_title_left = (TextView) findViewById(R.id.tv_bt_title_left);
        tv_bt_title_left.setText(R.string.app_name);
        tv_bt_title_left = (TextView) findViewById(R.id.tv_bt_title_right);

        // Get local Bluetooth adapter
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        // If the adapter is null, then Bluetooth is not supported
        if (mBluetoothAdapter == null) {
            showToastShort("当前设备不支持蓝牙");
            finish();
            return;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (D) {
            logE("++ ON START ++");
        }

        // If BT is not on, request that it be enabled.
        // setupChat() will then be called during onActivityResult
        if (!mBluetoothAdapter.isEnabled()) {
            Intent enableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableIntent, REQUEST_ENABLE_BT);
        } else if (mChatService == null) {
            // Otherwise, setup the chat session
            setupChat();
        }
    }

    @Override
    public synchronized void onResume() {
        super.onResume();
        if (D) {
            logE("++ ON RESUME ++");
        }

        // Performing this check in onResume() covers the case in which BT was
        // not enabled during onStart(), so we were paused to enable it...
        // onResume() will be called when ACITON_REQUEST_ENABLE activity returns.
        if (mChatService != null) {
            // Only if the state is STATE_NONE, do we know that we haven't started already
            if (mChatService.getState() == BlueToothChatService.STATE_NONE) {
                // Start the Bluetooth chat services
                mChatService.start();
            }
        }
    }

    private void setupChat() {
        logD("setupChat()方法执行");

        // Initialize the array adapter for the conversation thread
        mConversationArrayAdapter = new ArrayAdapter<String>(this, R.layout.bt_message);
        mConversationView = (ListView) findViewById(R.id.lv_bt);
        mConversationView.setAdapter(mConversationArrayAdapter);

        // Initialize the compose field with a listener for return key
        mOutEditText = (EditText) findViewById(R.id.et_bt_text_out);
        mOutEditText.setOnEditorActionListener(mWriteListener);


        // Initalize the second button with a listener that for click events
        mSendButton = (Button) findViewById(R.id.btn_bt_send);
        mSendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Send a message using content of the edit text widget
                TextView view = (TextView) findViewById(R.id.et_bt_text_out);
                String message = view.getText().toString();
                sendMessage(message);
            }
        });

        // Initialize the BluetoothChatService to perform bluetooth connections
        mChatService = new BlueToothChatService(this, mHandler);

        // Initalize the buffer for outgoing message
        mOutStringBuffer = new StringBuffer("");
    }

    @Override
    public synchronized void onPause() {
        super.onPause();
        if (D) {
            logE("-- ON PAUSE --");
        }
    }

    @Override
    public synchronized void onStop() {
        super.onStop();
        if (D) {
            logE("-- ON STOP --");
        }
    }

    @Override
    public synchronized void onDestroy() {
        super.onDestroy();
        if (D) {
            logE("-- ON DESTROY --");
        }
    }

    private void ensureDiscoverable() {
        if (D) {
            logD("ensure discoverable");
        }
        if (mBluetoothAdapter.getScanMode() != BluetoothAdapter.SCAN_MODE_CONNECTABLE_DISCOVERABLE) {
            Intent discoverableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
            discoverableIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 300);
            startActivity(discoverableIntent);
        }
    }

    /**
     * Sends a message
     *
     * @param messgae A string of text to send
     */
    private void sendMessage(String messgae) {
        // Check that we're actually connected before trying anything
        if (mChatService.getState() != BlueToothChatService.STATE_CONNECTED) {
            showToastShort(getResources().getString(R.string.not_connected));
            return;
        }

        // Check that there's actually something to send
        if (messgae.length() > 0) {
            // Get the message bytes and tell the BluetoothChatService to write
            byte[] send = messgae.getBytes();
            mChatService.write(send);

            // Reset out string buffer to zero and clear the edit text field
            mOutStringBuffer.setLength(0);
            mOutEditText.setText(mOutStringBuffer);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (D) {
            logD("onActivityResult requestCode:" + requestCode + ",resultCode:" + resultCode);
        }
        switch (requestCode) {
            case REQUEST_CONNECT_DEVICE_SECURE:
                // When BlueToothChatListActivity returns with a device to connect
                if (resultCode == Activity.RESULT_OK) {
                    connectDevice(data, true);
                }
                break;
            case REQUEST_CONNECT_DEVICE_INSECURE:
                if (resultCode == Activity.RESULT_OK) {
                    connectDevice(data, false);
                }
                break;
            case REQUEST_ENABLE_BT:
                // When the request to enable Bluetooth returns
                if (resultCode == Activity.RESULT_OK) {
                    // Bluetooth is now enabled, so set up a chat session
                    setupChat();
                } else {
                    // User did not enable Bluetooth or an error occured
                    logD("蓝牙设备打开失败");
                    showToastShort(getResources().getString(R.string.bt_not_enabled_leaving));
                    finish();
                }
        }
    }

    private void connectDevice(Intent data, boolean secure) {
        // Get the device MAC address
        String address = data.getExtras().getString(BlueToothChatListActivity.EXTRA_DEVICE_ADDRESS);
        // Get the BluetoothDevice object
        BluetoothDevice device = mBluetoothAdapter.getRemoteDevice(address);
        // Attempt to connecct to the device
        mChatService.connect(device, secure);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.bt_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent serverIntent = null;
        switch (item.getItemId()) {
            case R.id.secure_connect_scan:
                // Launch the BlueToothChatListActivity to see devcies and do scan
                serverIntent = new Intent(this, BlueToothChatListActivity.class);
                startActivityForResult(serverIntent, REQUEST_CONNECT_DEVICE_SECURE);
                return true;
            case R.id.insecure_connect_scan:
                serverIntent = new Intent(this, BlueToothChatListActivity.class);
                startActivityForResult(serverIntent, REQUEST_CONNECT_DEVICE_INSECURE);
                return true;
            case R.id.discoverable:
                // Ensure this device is discoverable by others
                ensureDiscoverable();
                return true;
        }
        return false;
    }
}
