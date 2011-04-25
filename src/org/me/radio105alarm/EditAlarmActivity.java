/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.me.radio105alarm;

import android.app.Activity;
import android.app.AlarmManager;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView.*;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.util.Log;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import org.me.radio105alarm.AlarmService;

import com.android.MyDatabase;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author ksemuldie
 */
public class EditAlarmActivity extends Activity {

  static final int TIME_DIALOG_ID = 0;

  @Override
  //protected void onStart(Bundle savedInstanceState) {
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Override
  protected void onResume() {
    super.onResume();

    setContentView(R.layout.edit_alarm);
    ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice, getResources().getStringArray(R.array.week_days_array));
    final ListView list = (ListView) findViewById(R.id.WeekDays);
    list.setAdapter(adapter);
    list.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
    list.setSelected(true);
    list.setClickable(true);

    MyDatabase db = new MyDatabase(getApplicationContext());
    db.open();  //apriamo il db

    for (int position = 0; position < 8; position++) {
      switch (position) {
        case 0:
          list.setItemChecked(position, db.isSundayEnabled());
          break;
        case 1:
          list.setItemChecked(position, db.isMondayEnabled());
          break;
        case 2:
          list.setItemChecked(position, db.isTuesdayEnabled());
          break;
        case 3:
          list.setItemChecked(position, db.isWednesdayEnabled());
          break;
        case 4:
          list.setItemChecked(position, db.isThursdayEnabled());
          break;
        case 5:
          list.setItemChecked(position, db.isFridayEnabled());
          break;
        case 6:
          list.setItemChecked(position, db.isSaturdayEnabled());
          break;
        case 7:
          list.setItemChecked(position, db.isEnabled());
          break;
      }
    }


    list.setOnItemClickListener(new OnItemClickListener() {

      public void onItemClick(AdapterView<?> parent, View view,
              int position, long id) {
        // When clicked, show a toast with the TextView text
        //long[] checkedIDs = list.getCheckItemIds();

        MyDatabase db = new MyDatabase(getApplicationContext());
        db.open();  //apriamo il db

        if (position == 7) {
          db.setEnabled(list.isItemChecked(position));

        }

        if (db.isEnabled()) {
          switch (position) {
            case 0:
              db.setSundayDays(list.isItemChecked(position));
              break;
            case 1:
              db.setMondayDays(list.isItemChecked(position));
              break;
            case 2:
              db.setTuesdayDays(list.isItemChecked(position));
              break;
            case 3:
              db.setWednesdayDays(list.isItemChecked(position));
              break;
            case 4:
              db.setTuesdayDays(list.isItemChecked(position));
              break;
            case 5:
              db.setFridayDays(list.isItemChecked(position));
              break;
            case 6:
              db.setSaturdayDays(list.isItemChecked(position));
              break;
            case 7:
              showDialog(TIME_DIALOG_ID);
              break;
          }
        } else {
          if (position != 7) {
            list.setItemChecked(position, !list.isItemChecked(position));
          }
        }

        

        /* FUNZIONANTE
        Intent contentIntent = new Intent(EditAlarmActivity.this ,AlarmService.class);
        PendingIntent pendingIntent = PendingIntent.getService(EditAlarmActivity.this, 0,contentIntent, PendingIntent.FLAG_UPDATE_CURRENT );
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        Calendar cal = Calendar.getInstance() ;

        Date cDate = new Date() ;
        cDate.setHours(17) ;
        cDate.setMinutes(04) ;
        cDate.setSeconds(0) ;
        cal.setTimeInMillis(cDate.getTime()) ;

        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis() , 1000 * 30 , pendingIntent);
         */

        Toast.makeText(EditAlarmActivity.this, Integer.toString(position),
                Toast.LENGTH_SHORT).show();
      }
    });
  }
  private TimePickerDialog.OnTimeSetListener mTimeSetListener =
          new TimePickerDialog.OnTimeSetListener() {

            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
              MyDatabase db = new MyDatabase(getApplicationContext());
              db.open();  //apriamo il db

              db.setHour(hourOfDay) ;
              db.setMinute(minute) ;

              db.close() ;

              final ListView list = (ListView) findViewById(R.id.WeekDays);
              CheckedTextView listItem = (CheckedTextView)list.getChildAt(/*list.getItemIdAtPosition(7)*/7) ;
              //TextView txt = (TextView)listItem.findViewById(R.id.text1) ;
              listItem.setText(hourOfDay+":"+minute) ;

              Toast.makeText(EditAlarmActivity.this, "Time is=" + hourOfDay + ":" + minute, Toast.LENGTH_SHORT).show();
            }
          };

  @Override
  protected Dialog onCreateDialog(int id) {
    switch (id) {
      case TIME_DIALOG_ID:
        return new TimePickerDialog(this, mTimeSetListener, 0, 0, false);
    }
    return null;
  }
}
