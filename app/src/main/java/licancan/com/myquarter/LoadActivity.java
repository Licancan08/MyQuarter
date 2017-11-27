package licancan.com.myquarter;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;
import com.igexin.sdk.PushManager;

import licancan.com.myquarter.service.MyService;
import licancan.com.myquarter.service.PutService;

/**
 * 引导页
 */
public class LoadActivity extends AppCompatActivity {

    private int time=3;
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            time--;
            if(time>0)
            {
                //Toast.makeText(LoadActivity.this, ""+time, Toast.LENGTH_SHORT).show();
            }else if(time==0){
                Intent intent=new Intent(LoadActivity.this,LoginStyleActivity.class);
                startActivity(intent);
            }
            handler.sendEmptyMessage(100);
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load);

        // com.getui.demo.DemoPushService 为第三方自定义推送服务
        //注册
        PushManager.getInstance().initialize(this.getApplicationContext(), MyService.class);
        //接收
        PushManager.getInstance().registerPushIntentService(this.getApplicationContext(), PutService.class);
        handler.sendEmptyMessageDelayed(100,1000);

    }
}
