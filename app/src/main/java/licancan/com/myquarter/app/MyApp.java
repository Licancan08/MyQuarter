package licancan.com.myquarter.app;

import android.app.Application;
import android.content.Context;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.squareup.leakcanary.LeakCanary;
import com.tencent.bugly.crashreport.CrashReport;

/**
 * Created by robot on 2017/11/14.
 */

public class MyApp extends Application{

    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
        context = getApplicationContext();
        //Bugly统计
        CrashReport.initCrashReport(getApplicationContext(), "c7d3946bb1", false);
        //LeakCanary统计
        /*if(LeakCanary.isInAnalyzerProcess(this))
        {
            return;
        }
        LeakCanary.install(this);*/
    }
}
