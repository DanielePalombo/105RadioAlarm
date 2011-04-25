/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.me.radio105alarm;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

/**
 *
 * @author ksemuldie
 */
public class AlarmService extends Service {

  @Override
  public void onCreate() {
    // TODO Auto-generated method stub
    Toast.makeText(this, "MyAlarmService.onCreate()", Toast.LENGTH_LONG).show();
  }

  @Override
  public IBinder onBind(Intent intent) {
    // TODO Auto-generated method stub
    Toast.makeText(this, "MyAlarmService.onBind()", Toast.LENGTH_LONG).show();
    return null;
  }

  @Override
  public void onDestroy() {
    // TODO Auto-generated method stub
    super.onDestroy();
    Toast.makeText(this, "MyAlarmService.onDestroy()", Toast.LENGTH_LONG).show();
  }

  @Override
  public void onStart(Intent intent, int startId) {
    // TODO Auto-generated method stub
    super.onStart(intent, startId);
    Log.i("AA", "MyAlarmService.onStart()") ;
    
    Toast.makeText(this, "MyAlarmService.onStart()", Toast.LENGTH_LONG).show();
    
    Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
    myIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK) ;
    startActivity(myIntent);
  }

  @Override
  public boolean onUnbind(Intent intent) {
    // TODO Auto-generated method stub
    Toast.makeText(this, "MyAlarmService.onUnbind()", Toast.LENGTH_LONG).show();
    return super.onUnbind(intent);
  }
}
