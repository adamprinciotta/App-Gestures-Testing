//Authors: Adam Princiotta, John Zhu

package com.example.gesturenavigation
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import androidx.core.view.GestureDetectorCompat
import androidx.core.view.MotionEventCompat
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.math.absoluteValue

private const val DEBUG_TAG = "Gestures"

class MainActivity : AppCompatActivity(),  View.OnTouchListener, GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener {
        // This example shows an Activity, but you would use the same approach if
        // you were subclassing a View.

    private lateinit var mDetector: GestureDetectorCompat

    // Called when the activity is first created.
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //on touch listener for a single view rather than the whole app
        MainView.setOnTouchListener(this)

        // Instantiate the gesture detector with the
        // application context and an implementation of
        // GestureDetector.OnGestureListener
        mDetector = GestureDetectorCompat(this, this)

        // Set the gesture detector as the double tap
        // listener.
        mDetector.setOnDoubleTapListener(this)
    }

    //onTouchListener code for single view
    override fun onTouch(view : View, motionEvent: MotionEvent) : Boolean{

        when (motionEvent.getAction()) {
            MotionEvent.ACTION_DOWN -> {
                Log.d("TAG", "Action was DOWN")
                Toast.makeText(getApplicationContext(), "Pressed Down on Logo", Toast.LENGTH_SHORT).show();
                true
            }
            MotionEvent.ACTION_MOVE -> {
                Log.d("TAG", "Action was MOVE")
                Toast.makeText(getApplicationContext(), "Moving", Toast.LENGTH_SHORT).show()
                true
            }
            MotionEvent.ACTION_UP -> {
                Log.d("TAG", "Action was UP")
                Toast.makeText(getApplicationContext(), "Let Go of Logo", Toast.LENGTH_SHORT).show()
                true
            }
            else -> return false
        }
        return true
    }


    override fun onTouchEvent(event: MotionEvent): Boolean {
        return if (mDetector.onTouchEvent(event)) {
            true
        } else {
            super.onTouchEvent(event)
        }
    }

    //the screen is pressed
    override fun onDown(event: MotionEvent): Boolean {
        Log.d(DEBUG_TAG, "onDown: $event")
        Toast.makeText(getApplicationContext(), "Down", Toast.LENGTH_SHORT).show();
        return true
    }

    //occurs with motion event, takes both motion events and measures the pixels per second along
    //each axis
    override fun onFling(
        event1: MotionEvent,
        event2: MotionEvent,
        velocityX: Float,
        velocityY: Float
    ): Boolean {
        Log.d(DEBUG_TAG, "onFling: $event1 $event2")

        //Determines which direction is being swiped
        if (velocityX.absoluteValue > velocityY.absoluteValue) {
                if (velocityX > 0) {
                    Toast.makeText(getApplicationContext(), "Swipe Right", Toast.LENGTH_SHORT).show();
                }
                else if (velocityX < 0){
                    Toast.makeText(getApplicationContext(), "Swipe Left", Toast.LENGTH_SHORT).show();
                }
            }
        else if (velocityX.absoluteValue < velocityY.absoluteValue){
            if (velocityY < 0) {
                Toast.makeText(getApplicationContext(), "Swipe Up", Toast.LENGTH_SHORT).show();
            }
            else if (velocityY > 0) {
                Toast.makeText(getApplicationContext(), "Swipe Down", Toast.LENGTH_SHORT).show();
            }

        }
        else {
            Toast.makeText(getApplicationContext(), "Fling", Toast.LENGTH_SHORT).show();
        }
        return true
    }

    //screen is pressed for 500ms or more
    override fun onLongPress(event: MotionEvent) {
        Log.d(DEBUG_TAG, "onLongPress: $event")
        Toast.makeText(getApplicationContext(), "Long Press", Toast.LENGTH_SHORT).show();
    }

    //takes initial motion event and current motion event, then gives the distance in x and y axis
    override fun onScroll(
        event1: MotionEvent,
        event2: MotionEvent,
        distanceX: Float,
        distanceY: Float
    ): Boolean {
        Log.d(DEBUG_TAG, "onScroll: $event1 $event2")
        Toast.makeText(getApplicationContext(), "Scrolling", Toast.LENGTH_SHORT).show();
        return true
    }

    //Shows between onDown and onLongPress, occurs too quickly to display
    //normally used for providing visual feedback to let users know their action has been recognized
    //i.e. highlighting elements
    override fun onShowPress(event: MotionEvent) {
        Log.d(DEBUG_TAG, "onShowPress: $event")
    }

    //Shows between onDown and onSingleTapConfirmed, occurs too quickly to display
    //up motion of the first tap (could be a second one)
    override fun onSingleTapUp(event: MotionEvent): Boolean {
        Log.d(DEBUG_TAG, "onSingleTapUp: $event")
        return true
    }

    //Shows between onDown and onDoubleTapEvent, occurs too quickly to display
    //two taps are done
    override fun onDoubleTap(event: MotionEvent): Boolean {
        Log.d(DEBUG_TAG, "onDoubleTap: $event")
        return true
    }

    //Recognizes when an event occurs within the double tap
    //could be directional events such as down, move and up events
    override fun onDoubleTapEvent(event: MotionEvent): Boolean {
        Log.d(DEBUG_TAG, "onDoubleTapEvent: $event")
        Toast.makeText(getApplicationContext(), "Double Tap", Toast.LENGTH_SHORT).show();
        return true
    }

    //only called when it is clear the first tap is not followed by a second tap
    //prevents an accidental double tap gesture
    override fun onSingleTapConfirmed(event: MotionEvent): Boolean {
        Log.d(DEBUG_TAG, "onSingleTapConfirmed: $event")
        Toast.makeText(getApplicationContext(), "Confirmed single tap", Toast.LENGTH_SHORT).show();
        return true
    }

}

