package com.example.blueooth;

import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
        ListView list; Button btn; TextView txt;
        BluetoothAdapter bluetoothAdapter;
        ArrayList<String> bluetoothDevices = new ArrayList<>();
        ArrayAdapter arrayAdapter;

        private final BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                Log.i("Status : ", action);

                if(BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action)){    // If device discovery is finished
                    btn.setEnabled(true);
                    txt.setText("Finished");
                } else if(BluetoothDevice.ACTION_FOUND.equals(action)){           // If any device is found during device discovery
                    BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                    String name = device.getName();
                    String address = device.getAddress();
                    String RSSI = Integer.toString(intent.getShortExtra(BluetoothDevice.EXTRA_RSSI,Short.MIN_VALUE));

                    Log.i("Device found" , "Name : " + name + "Address : " +address + "RSSI : " + RSSI);

                    if(name == null || name == ""){
                        bluetoothDevices.add("Address : " +address + "RSSI : " + RSSI);
                    } else {
                        bluetoothDevices.add("Name : " + name + "Address : " +address + "RSSI : " + RSSI);
                    }
                    arrayAdapter.notifyDataSetChanged();
                }

            }
        };

        public void searchClicked(View view) {
            txt.setText("Searching...");
            btn.setEnabled(false);
            bluetoothAdapter.startDiscovery();
        }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list = findViewById(R.id.listView);
        btn = findViewById(R.id.btn);
        txt = findViewById(R.id.txt);

        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, bluetoothDevices);
        list.setAdapter(arrayAdapter);

        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(BluetoothAdapter.ACTION_STATE_CHANGED);      // Bluetooth ON/OFF
        intentFilter.addAction(BluetoothDevice.ACTION_FOUND);               // To keep track when any device found
        intentFilter.addAction(BluetoothAdapter.ACTION_DISCOVERY_STARTED);  // To keep track when discovery started
        intentFilter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED); // To keep track when discovery finished

        registerReceiver(broadcastReceiver,intentFilter);

    }
}