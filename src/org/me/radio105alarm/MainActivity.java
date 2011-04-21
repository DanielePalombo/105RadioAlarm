/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.me.radio105alarm;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Gallery.LayoutParams;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ViewSwitcher;
/**
 *
 * @author ksemuldie
 */
public class MainActivity extends Activity {

    private Button streamButton;

 private ImageButton playButton;

 private TextView textStreamed;

 private boolean isPlaying;

 private StreamingMediaPlayer audioStreamer;

 @Override
 public void onCreate(Bundle savedInstanceState)
 {
  super.onCreate(savedInstanceState);
  // requestWindowFeature(Window.FEATURE_NO_TITLE);

  setContentView(R.layout.main);
  initControls();


 }
 protected void onDestroy()
 {
  super.onDestroy();

  Toast.makeText(MainActivity.this, "...exiting application..." ,Toast.LENGTH_SHORT).show();
  if ( audioStreamer != null)
  {
   audioStreamer.interrupt();
  }

 }

 private void initControls()
 {
  textStreamed = (TextView) findViewById(R.id.text_kb_streamed);
  streamButton = (Button) findViewById(R.id.button_stream);
  streamButton.setOnClickListener(new View.OnClickListener()
  {
   public void onClick(View view)
   {

    String urlstring2 = "http://shoutcast.unitedradio.it:1101";


    Toast
    .makeText(
      MainActivity.this,
       "The following stream is about to start" + urlstring2,
      Toast.LENGTH_LONG).show();
    startStreamingAudio(urlstring2);
   }
  });

  playButton = (ImageButton) findViewById(R.id.button_play);
  playButton.setEnabled(false);
  playButton.setOnClickListener(new View.OnClickListener()
  {
   public void onClick(View view)
   {
    if (audioStreamer.getMediaPlayer().isPlaying())
    {
     audioStreamer.getMediaPlayer().pause();
     playButton.setImageResource(R.drawable.button_play);
    } else
    {
     audioStreamer.getMediaPlayer().start();
     audioStreamer.startPlayProgressUpdater();
     playButton.setImageResource(R.drawable.button_pause);
    }
    isPlaying = !isPlaying;
   }
  });
 }
 private void startStreamingAudio(String urlstring) {
  try {
   final ProgressBar progressBar = (ProgressBar) findViewById(R.id.progress_bar);
   if ( audioStreamer != null) {
    audioStreamer.interrupt();
   }
   audioStreamer = new StreamingMediaPlayer(this,textStreamed, playButton, streamButton,progressBar);
   audioStreamer.startStreaming(urlstring,5208, 216);
   streamButton.setEnabled(false);
  } catch (Exception e)
  {
   Log.e(getClass().getName(), "Error starting to stream audio.", e);
  }

 }

 public void onItemSelected(AdapterView parent, View v, int position, long id)
 {
  mSwitcher.setImageResource(mImageIds[position]);
 }

 public void onNothingSelected(AdapterView parent)
 {
 }

 public View makeView()
 {
  ImageView i = new ImageView(this);
  i.setBackgroundColor(0xFF000000);
  i.setScaleType(ImageView.ScaleType.FIT_CENTER);
  i.setLayoutParams(new ImageSwitcher.LayoutParams(
    LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
  return i;
 }

 private ImageSwitcher mSwitcher;

 public class ImageAdapter extends BaseAdapter
 {
  public ImageAdapter(Context c)
  {
   mContext = c;
  }

  public int getCount()
  {
   return mThumbIds.length;
  }

  public Object getItem(int position)
  {
   return position;
  }

  public long getItemId(int position)
  {
   return position;
  }

  public View getView(int position, View convertView, ViewGroup parent)
  {
   ImageView i = new ImageView(mContext);

   i.setImageResource(mThumbIds[position]);
   i.setAdjustViewBounds(true);
   i.setLayoutParams(new Gallery.LayoutParams(
     LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
   i.setBackgroundResource(R.drawable.picture_frame);
   return i;
  }

  private Context mContext;

 }

 private Integer[] mThumbIds =
 { R.drawable.calculator, R.drawable.calendar, R.drawable.camera };

 private Integer[] mImageIds =
 { R.drawable.calculator, R.drawable.calendar, R.drawable.camera };

}
