package licancan.com.myquarter.activity;

import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import licancan.com.myquarter.R;
import licancan.com.myquarter.base.BaseActivity;
import licancan.com.myquarter.entity.BaseBean;
import licancan.com.myquarter.presenter.UpdateNamePresenter;
import licancan.com.myquarter.utils.NetWorkUtils;
import licancan.com.myquarter.view.UpdateNickNameView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class UpdateActivity extends BaseActivity<UpdateNamePresenter> implements UpdateNickNameView, View.OnClickListener {

    private RelativeLayout layout_head;
    private RelativeLayout layout_name;
    private EditText et_updateName;
    private View view1;
    private TextView tv_update_name;
    private ImageView iv_update_head;
    public ArrayList<String> path = new ArrayList<>();
    private String uid;
    private File file;

    @Override
    public int getLayoutid() {
        return R.layout.activity_update;
    }

    @Override
    public UpdateNamePresenter initPresenter() {
        return new UpdateNamePresenter(this,this);
    }

    @Override
    public void Creat() {
        initView();
        Intent intent=getIntent();
        String name = intent.getStringExtra("name");
        String icon = intent.getStringExtra("icon");
        tv_update_name.setText(name);
        Uri url=Uri.parse(icon);
        iv_update_head.setImageURI(url);
    }

    private void initView() {
        layout_head = findViewById(R.id.layout_head);
        layout_name = findViewById(R.id.layout_name);
        layout_head.setOnClickListener(this);
        layout_name.setOnClickListener(this);
        tv_update_name = findViewById(R.id.tv_update_name);
        iv_update_head = findViewById(R.id.iv_update_head);
        SharedPreferences uidSp=getSharedPreferences("config",MODE_PRIVATE);
        uid = uidSp.getString("uid", "148");
    }

    @Override
    public void RequestSuccess(BaseBean baseBean) {
        ShowToast(baseBean.msg);
        System.out.println("新昵称========"+baseBean.msg);
    }

    @Override
    public void RequestFailure(BaseBean baseBean) {
        ShowToast(baseBean.msg);
    }

    @Override
    public void Failure(BaseBean baseBean) {
        ShowToast(baseBean.msg);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            //上传头像
            case R.id.layout_head:
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");//相片类型??
                startActivityForResult(intent, 100);

                break;
            //修改昵称
            case R.id.layout_name:
                view1 = View.inflate(this, R.layout.update_name,null);
                AlertDialog.Builder builder1=new AlertDialog.Builder(this);
                builder1.setMessage("确定要修改昵称吗？");
                builder1.setView(view1);
                builder1.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        et_updateName = view1.findViewById(R.id.et_updateName);
                        String updateName = et_updateName.getText().toString();

                        //String str=new String(updateName.getBytes(),"UTF-8");
                        presenter.updateName(uid,updateName);
                        tv_update_name.setText(updateName);

                    }
                });
                builder1.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        ShowToast("暂时不修改");
                    }
                });
                builder1.create().show();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode != RESULT_OK){
            Log.e("TAG--->onresult","ActivityResult?resultCode?error");
            return;
        }
        //获得图片??
        Bitmap bitmap = null;
        ContentResolver resolver = getContentResolver();
        if(requestCode == 100){
            Uri uri = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(resolver,uri);//获得图片??
            } catch (IOException e) {
                e.printStackTrace();
            }
            //返回到控件上
            iv_update_head.setImageBitmap(bitmap);
        }

        //获得路径??
        if(requestCode == 100){
            Uri uri = data.getData();
            //uri = geturi(data);//解决方案??
            String[] pro = {MediaStore.Images.Media.DATA};
            //好像是android多媒体数据库的封装接口，具体的看Android文档??
            Cursor cursor = managedQuery(uri,pro,null,null,null);
            Cursor cursor1 = getContentResolver().query(uri,pro,null,null,null);
            //拿到引索??
            int index = cursor1.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            //移动到光标开头??
            cursor.moveToFirst();
            //最后根据索引值获取图片路径??
            String path = cursor.getString(index);
            file = new File(path);

            Log.d("Tag--->path",path);
            initData();

        }
    }



    /**
     * 请求上传图片的接口
     */
    private void initData() {
        System.out.println("uid ============== " + uid+file);
        RequestBody requestBody=RequestBody.create(MediaType.parse("image/*"),file);
        MultipartBody.Part part=MultipartBody.Part.createFormData("file",file.getName(),requestBody);

        MultipartBody.Part uidPart=MultipartBody.Part.createFormData("uid",uid);
        MultipartBody.Part uidPart1=MultipartBody.Part.createFormData("source","android");
        MultipartBody.Part uidPart2=MultipartBody.Part.createFormData("appVersion","1");
        //得到token值
        SharedPreferences mytoken=getSharedPreferences("config",Context.MODE_PRIVATE);
        String token=mytoken.getString("token","");
        MultipartBody.Part uidPart3=MultipartBody.Part.createFormData("token",token);
        new NetWorkUtils.Builder().addConverterFactory(GsonConverterFactory.create())
                .addCalladapterFactory(RxJava2CallAdapterFactory.create())
                .build().getApiService().upload(uidPart,part,uidPart1,uidPart2,uidPart3).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }
                    @Override
                    public void onNext(BaseBean baseBean) {
                        String code = baseBean.code;
                        if("0".equals(code))
                        {
                            System.out.println("=======图片上传成功======"+baseBean.msg);
                        }
                        else if("1".equals(code)){
                            System.out.println("=======图片上传失败======"+baseBean.msg);
                        }
                    }
                    @Override
                    public void onError(Throwable e) {
                        System.out.println("=======图片上传请求错误======"+e.toString());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

}
