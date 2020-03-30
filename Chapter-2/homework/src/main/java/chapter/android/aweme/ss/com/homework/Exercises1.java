package chapter.android.aweme.ss.com.homework;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 作业1：
 * Logcat在屏幕旋转的时候 #onStop() #onDestroy()会展示出来
 * 但我们的 mLifecycleDisplay 由于生命周期的原因(Tips:执行#onStop()之后，UI界面我们是看不到的)并没有展示
 * 在原有@see Exercises1 基础上如何补全它，让其跟logcat的展示一样?
 * <p>
 * Tips：思考用比Activity的生命周期要长的来存储？  （比如：application、static变量）
 */
public class Exercises1 extends AppCompatActivity {

    private static final String TAG = "lsy";


    private static final String ON_CREATE = "onCreate";
    private static final String ON_START = "onStart";
    private static final String ON_RESUME = "onResume";
    private static final String ON_PAUSE = "onPause";
    private static final String ON_STOP = "onStop";
    private static final String ON_RESTART = "onRestart";
    private static final String ON_DESTROY = "onDestroy";

    private static final List<String> staticList = new CopyOnWriteArrayList<>();


    private TextView mLifecycleDisplay;


    private void logAndAppend(String lifecycleEvent,boolean isNew) {
        Log.d(TAG, "Lifecycle Event: " + lifecycleEvent);
        mLifecycleDisplay.append(lifecycleEvent + "\n");
        if(isNew) {
            // 新状态则添加到list当中
            staticList.add(lifecycleEvent);
        }
    }

    public void resetLifecycleDisplay(View view) {

        mLifecycleDisplay.setText("Lifecycle callbacks:\n");
        staticList.clear();
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lifecycle);
        mLifecycleDisplay = findViewById(R.id.tv_loglifecycle);
        int count = 1;
        for(String status: staticList){
            if(status.equals(ON_CREATE)){
                mLifecycleDisplay.append("No."+String.valueOf(count++)+"rotation"+'\n');
            }
            logAndAppend(status,false);
        }
        mLifecycleDisplay.append(String.valueOf(count++)+'\n');
        logAndAppend(ON_CREATE,true);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        logAndAppend(ON_RESTART,true);
    }

    @Override
    protected void onStart() {
        super.onStart();
        logAndAppend(ON_START,true);
    }

    @Override
    protected void onResume() {
        super.onResume();
        logAndAppend(ON_RESUME,true);
    }


    @Override
    protected void onPause() {
        super.onPause();
        logAndAppend(ON_PAUSE,true);
    }

    @Override
    protected void onStop() {
        super.onStop();
        logAndAppend(ON_STOP,true);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        logAndAppend(ON_DESTROY,true);
    }

}
