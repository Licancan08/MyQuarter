package licancan.com.myquarter.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.igexin.sdk.PushManager;

import licancan.com.myquarter.R;
import licancan.com.myquarter.base.BaseActivity;
import licancan.com.myquarter.base.BasePresenter;
import licancan.com.myquarter.service.MyService;
import licancan.com.myquarter.service.PutService;

/**
 * 引导页
 */
public class LoadActivity extends BaseActivity{

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
                startActivity(LoginStyleActivity.class);
            }
            handler.sendEmptyMessage(100);
        }
    };

    @Override
    public int getLayoutid() {
        return R.layout.activity_load;
    }

    @Override
    public BasePresenter initPresenter() {
        return null;
    }

    @Override
    public void Creat() {
        // com.getui.demo.DemoPushService 为第三方自定义推送服务
        //注册
        PushManager.getInstance().initialize(this.getApplicationContext(), MyService.class);
        //接收
        PushManager.getInstance().registerPushIntentService(this.getApplicationContext(), PutService.class);
        handler.sendEmptyMessageDelayed(100,1000);
    }


}
