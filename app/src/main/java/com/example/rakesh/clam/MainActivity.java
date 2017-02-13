package com.example.rakesh.clam;

import java.util.ArrayList;

import android.app.Activity;
import android.gesture.Gesture;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.gesture.GestureOverlayView;
import android.gesture.GestureOverlayView.OnGesturePerformedListener;
import android.gesture.Prediction;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends Activity {

    GestureLibrary gestureLibrary = null;
    GestureOverlayView gestureOverlayView;
    TextView gestureResult;


    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gestureResult = (TextView)findViewById(R.id.gestureresult);
        gestureOverlayView = (GestureOverlayView)findViewById(R.id.gestures);

        gestureLibrary = GestureLibraries.fromRawResource(this, R.raw.gestures);
        gestureLibrary.load();

        gestureOverlayView.addOnGesturePerformedListener(gesturePerformedListener);
    }

    OnGesturePerformedListener gesturePerformedListener
            = new OnGesturePerformedListener(){

        @Override
        public void onGesturePerformed(GestureOverlayView view, Gesture gesture) {
            // TODO Auto-generated method stub
            ArrayList<Prediction> prediction = gestureLibrary.recognize(gesture);
            if(prediction.size() > 0){
                gestureResult.setText(prediction.get(0).name);
            }

        }};
}