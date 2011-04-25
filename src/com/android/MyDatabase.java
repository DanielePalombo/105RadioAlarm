package com.android;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.util.DebugUtils;
import android.util.Log;

public class MyDatabase {

    SQLiteDatabase mDb;
    DbHelper mDbHelper;
    Context mContext;
    private static final String DB_NAME="Radio105WakeUpAlarm";//nome del db
    private static final int DB_VERSION=1; //numero di versione del nostro db
    
    /*
    public static MyDatabase getInstance(Context ctx){
      if(INSTANCE == null ){
        this.INSTANCE = new MyDatabase(ctx) ;
      }

      return INSTANCE ;
    }
     *
     */

    public MyDatabase(Context ctx){
            mContext=ctx;
            mDbHelper=new DbHelper(ctx, DB_NAME, null, DB_VERSION);   //quando istanziamo questa classe, istanziamo anche l'helper (vedi sotto)
    }

    public void open(){  //il database su cui agiamo è leggibile/scrivibile
            mDb=mDbHelper.getWritableDatabase();

    }

    public void close(){ //chiudiamo il database su cui agiamo
            mDb.close();
    }


    //i seguenti 2 metodi servono per la lettura/scrittura del db. aggiungete e modificate a discrezione
   // consiglio:si potrebbe creare una classe Prodotto, i quali oggetti verrebbero passati come parametri dei seguenti metodi, rispettivamente ritornati. Lacio a voi il divertimento

    public int getHour(){
      Cursor c = mDb.query(ProfileMetaData.ALARM_TABLE,new String[]{ProfileMetaData.ALARM_HOUR}, ProfileMetaData.ALARM_ID+"=1",null,null,null,null);
      c.moveToFirst();
      return c.getInt(0) ;
    }

    public int getMinute(){
      Cursor c = mDb.query(ProfileMetaData.ALARM_TABLE,new String[]{ProfileMetaData.ALARM_MINUTE}, ProfileMetaData.ALARM_ID+"=1",null,null,null,null);
      c.moveToFirst();
      return c.getInt(0) ;
    }

    public boolean isEnabled(){
      Cursor c = mDb.query(ProfileMetaData.ALARM_TABLE,new String[]{ProfileMetaData.ALARM_ENABLED}, ProfileMetaData.ALARM_ID+"=1",null,null,null,null);
      c.moveToFirst();
      return ((c.getInt(0) == 1)?true:false) ;
    }

    public boolean isSundayEnabled(){
      Cursor c = mDb.query(ProfileMetaData.ALARM_TABLE,new String[]{ProfileMetaData.ALARM_DAYS_SUNDAY}, ProfileMetaData.ALARM_ID+"=1",null,null,null,null);
      c.moveToFirst();
      return ((c.getInt(0) == 1)?true:false) ;
    }

    public boolean isMondayEnabled(){
      Cursor c = mDb.query(ProfileMetaData.ALARM_TABLE,new String[]{ProfileMetaData.ALARM_DAYS_MONDAY}, ProfileMetaData.ALARM_ID+"=1",null,null,null,null);
      c.moveToFirst();
      return ((c.getInt(0) == 1)?true:false) ;
    }

    public boolean isTuesdayEnabled(){
      Cursor c = mDb.query(ProfileMetaData.ALARM_TABLE,new String[]{ProfileMetaData.ALARM_DAYS_TUESDAY}, ProfileMetaData.ALARM_ID+"=1",null,null,null,null);
      c.moveToFirst();
      return ((c.getInt(0) == 1)?true:false) ;
    }

    public boolean isWednesdayEnabled(){
      Cursor c = mDb.query(ProfileMetaData.ALARM_TABLE,new String[]{ProfileMetaData.ALARM_DAYS_WEDNESDAY}, ProfileMetaData.ALARM_ID+"=1",null,null,null,null);
      c.moveToFirst();
      return ((c.getInt(0) == 1)?true:false) ;
    }

    public boolean isThursdayEnabled(){
      Cursor c = mDb.query(ProfileMetaData.ALARM_TABLE,new String[]{ProfileMetaData.ALARM_DAYS_THURSDAY}, ProfileMetaData.ALARM_ID+"=1",null,null,null,null);
      c.moveToFirst();
      return ((c.getInt(0) == 1)?true:false) ;
    }

    public boolean isFridayEnabled(){
      Cursor c = mDb.query(ProfileMetaData.ALARM_TABLE,new String[]{ProfileMetaData.ALARM_DAYS_FRIDAY}, ProfileMetaData.ALARM_ID+"=1",null,null,null,null);
      c.moveToFirst();
      return ((c.getInt(0) == 1)?true:false) ;
    }

    public boolean isSaturdayEnabled(){
      Cursor c = mDb.query(ProfileMetaData.ALARM_TABLE,new String[]{ProfileMetaData.ALARM_DAYS_SATURDAY}, ProfileMetaData.ALARM_ID+"=1",null,null,null,null);
      c.moveToFirst();
      return ((c.getInt(0) == 1)?true:false) ;
    }

    public void setEnabled(boolean enable) {
      ContentValues cv=new ContentValues();
      //cv.put(ProfileMetaData.REPEAT_SESSION_ID, id_session);
      cv.put(ProfileMetaData.ALARM_ENABLED, enable);
      //cv.put(ProfileMetaData.PRODUCT_TEAM_KEY, team);
      mDb.update(ProfileMetaData.ALARM_TABLE , cv, ProfileMetaData.ALARM_ID+"= ?" , new String[]{"1"});
    }

    public void setHour(int hour ) {
      ContentValues cv=new ContentValues();
      //cv.put(ProfileMetaData.REPEAT_SESSION_ID, id_session);
      cv.put(ProfileMetaData.ALARM_HOUR, hour);
      //cv.put(ProfileMetaData.PRODUCT_TEAM_KEY, team);
      mDb.update(ProfileMetaData.ALARM_TABLE , cv, ProfileMetaData.ALARM_ID+"= ?" , new String[]{"1"});
    }

    public void setMinute(int minute ) {
      ContentValues cv=new ContentValues();
      //cv.put(ProfileMetaData.REPEAT_SESSION_ID, id_session);
      cv.put(ProfileMetaData.ALARM_MINUTE, minute);
      //cv.put(ProfileMetaData.PRODUCT_TEAM_KEY, team);
      mDb.update(ProfileMetaData.ALARM_TABLE , cv, ProfileMetaData.ALARM_ID+"= ?" , new String[]{"1"});
    }

    public void setWeekDays(boolean sunday , boolean monday, boolean tuesday, boolean wednesday, boolean thursday, boolean friday, boolean saturday) {
      ContentValues cv=new ContentValues();
      //cv.put(ProfileMetaData.REPEAT_SESSION_ID, id_session);
      cv.put(ProfileMetaData.ALARM_DAYS_SUNDAY, sunday);
      cv.put(ProfileMetaData.ALARM_DAYS_MONDAY, monday);
      cv.put(ProfileMetaData.ALARM_DAYS_TUESDAY, tuesday);
      cv.put(ProfileMetaData.ALARM_DAYS_WEDNESDAY, wednesday);
      cv.put(ProfileMetaData.ALARM_DAYS_THURSDAY, thursday);
      cv.put(ProfileMetaData.ALARM_DAYS_FRIDAY, friday);
      cv.put(ProfileMetaData.ALARM_DAYS_SATURDAY, saturday);
      //cv.put(ProfileMetaData.PRODUCT_TEAM_KEY, team);
      mDb.update(ProfileMetaData.ALARM_TABLE , cv, ProfileMetaData.ALARM_ID+"= " , new String[]{"1"});
    }

    public void setDays(String dayName ,boolean enable) {
      ContentValues cv=new ContentValues();
      //cv.put(ProfileMetaData.REPEAT_SESSION_ID, id_session);
      cv.put(dayName, enable);
      mDb.update(ProfileMetaData.ALARM_TABLE , cv, ProfileMetaData.ALARM_ID+"= ?" , new String[]{"1"});
    }

    public void setSundayDays(boolean enable) {
      setDays(ProfileMetaData.ALARM_DAYS_SUNDAY , enable);
    }

    public void setMondayDays(boolean enable) {
      setDays(ProfileMetaData.ALARM_DAYS_MONDAY , enable);
    }

    public void setTuesdayDays(boolean enable) {
      setDays(ProfileMetaData.ALARM_DAYS_TUESDAY , enable);
    }

    public void setWednesdayDays(boolean enable) {
      setDays(ProfileMetaData.ALARM_DAYS_WEDNESDAY , enable);
    }

    public void setThuesdayDays(boolean enable) {
      setDays(ProfileMetaData.ALARM_DAYS_THURSDAY , enable);
    }

    public void setFridayDays(boolean enable) {
      setDays(ProfileMetaData.ALARM_DAYS_FRIDAY , enable);
    }

    public void setSaturdayDays(boolean enable) {
      setDays(ProfileMetaData.ALARM_DAYS_SATURDAY , enable);
    }
   
    public static class ProfileMetaData {  // i metadati della tabella, accessibili ovunque
            static final String ALARM_TABLE = "alarm";
            static final String ALARM_ID = "_id";
            public static final String ALARM_DAYS_SUNDAY = "week_day_sunday";
            public static final String ALARM_DAYS_MONDAY = "week_day_monday";
            public static final String ALARM_DAYS_TUESDAY = "week_day_tuesday";
            public static final String ALARM_DAYS_WEDNESDAY = "week_day_wednesday";
            public static final String ALARM_DAYS_THURSDAY = "week_day_thursday";
            public static final String ALARM_DAYS_FRIDAY = "week_day_friday";
            public static final String ALARM_DAYS_SATURDAY = "week_day_saturday";
            public static final String ALARM_HOUR = "hour";
            public static final String ALARM_MINUTE = "minute";
            public static final String ALARM_ENABLED = "enabled_alarm";

            //static final String PRODUCT_TEAM_KEY = "team";
    }

    private static final String ALARM_TABLE_CREATE = "CREATE TABLE IF NOT EXISTS "  //codice sql di creazione della tabella
                    + ProfileMetaData.ALARM_TABLE + " ("
                    + ProfileMetaData.ALARM_ID+ " integer primary key autoincrement, "
                    + ProfileMetaData.ALARM_DAYS_SUNDAY + " integer not null ,"
                    + ProfileMetaData.ALARM_DAYS_MONDAY + " integer not null ,"
                    + ProfileMetaData.ALARM_DAYS_TUESDAY + " integer not null ,"
                    + ProfileMetaData.ALARM_DAYS_WEDNESDAY + " integer not null ,"
                    + ProfileMetaData.ALARM_DAYS_THURSDAY + " integer not null ,"
                    + ProfileMetaData.ALARM_DAYS_FRIDAY + " integer not null ,"
                    + ProfileMetaData.ALARM_DAYS_SATURDAY + " integer not null ,"
                    + ProfileMetaData.ALARM_HOUR + " integer not null ,"
                    + ProfileMetaData.ALARM_MINUTE + " integer not null ,"
                    + ProfileMetaData.ALARM_ENABLED + " integer not null );";

    
    private class DbHelper extends SQLiteOpenHelper { //classe che ci aiuta nella creazione del db

      public DbHelper(Context context, String name, CursorFactory factory,int version) {
        super(context, name, factory, version);
      }

      @Override
      public void onCreate(SQLiteDatabase _db) { //solo quando il db viene creato, creiamo la tabella
        try {
          _db.execSQL(ALARM_TABLE_CREATE);
          
          ContentValues cv=new ContentValues();
          cv.put(ProfileMetaData.ALARM_ID, "1");
          cv.put(ProfileMetaData.ALARM_DAYS_SUNDAY, 0);
          cv.put(ProfileMetaData.ALARM_DAYS_MONDAY, 0);
          cv.put(ProfileMetaData.ALARM_DAYS_TUESDAY, 0);
          cv.put(ProfileMetaData.ALARM_DAYS_WEDNESDAY, 0);
          cv.put(ProfileMetaData.ALARM_DAYS_THURSDAY, 0);
          cv.put(ProfileMetaData.ALARM_DAYS_FRIDAY, 0);
          cv.put(ProfileMetaData.ALARM_DAYS_SATURDAY, 0);
          cv.put(ProfileMetaData.ALARM_HOUR, 0);
          cv.put(ProfileMetaData.ALARM_MINUTE, 0);
          cv.put(ProfileMetaData.ALARM_ENABLED, 0);
          //cv.put(ProfileMetaData.PRODUCT_TEAM_KEY, team);
          try{
            _db.insertOrThrow(ProfileMetaData.ALARM_TABLE, null, cv);
          } catch(SQLException e){
            Log.e("DBERROR", e.getMessage()) ;
          }
          
        } catch(SQLException e) {
          Log.e("DATABASE", e.toString()) ;
        }

         onUpgrade(_db, 1, DB_VERSION);
      }

      @Override
      public void onUpgrade(SQLiteDatabase _db, int oldVersion, int newVersion) {
        //qui mettiamo eventuali modifiche al db, se nella nostra nuova versione della app, il db cambia numero di versione
        for(int appVersion = oldVersion+1 ; appVersion <= newVersion ; appVersion++ ){
          switch(appVersion){
            case 1:
              //insertInit();
            case 2:
              
            break ;
          }
        }
      }
    }
}