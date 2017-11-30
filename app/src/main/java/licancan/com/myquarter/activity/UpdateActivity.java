package licancan.com.myquarter.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import licancan.com.myquarter.R;
import licancan.com.myquarter.base.BaseActivity;
import licancan.com.myquarter.entity.BaseBean;
import licancan.com.myquarter.entity.User;
import licancan.com.myquarter.presenter.UpdateNamePresenter;
import licancan.com.myquarter.service.ApiService;
import licancan.com.myquarter.utils.NetWorkUtils;
import licancan.com.myquarter.view.UpdateNickNameView;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class UpdateActivity extends BaseActivity<UpdateNamePresenter> implements UpdateNickNameView, View.OnClickListener {

    private RelativeLayout layout_head;
    private RelativeLayout layout_name;
    private EditText et_updateName;
    private View view1;
    private TextView tv_update_name;
    private ImageView iv_update_head;

    private static final int CHOOSE_PICTURE=0;
    private static final int TAKE_PICTURE=1;
    private static final int CROP_SMALL_PICTURE=2;
    private static Uri tempUri;
    private String uid;

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
        tv_update_name.setText(name);
    }

    private void initView() {
        layout_head = findViewById(R.id.layout_head);
        layout_name = findViewById(R.id.layout_name);
        layout_head.setOnClickListener(this);
        layout_name.setOnClickListener(this);
        tv_update_name = findViewById(R.id.tv_update_name);
        iv_update_head = findViewById(R.id.iv_update_head);
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
                AlertDialog.Builder builder=new AlertDialog.Builder(this);
                builder.setTitle("设置头像");
                String[] items={"选择本地照片","拍照"};
                builder.setNegativeButton("取消",null);
                builder.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        switch (i){
                            case CHOOSE_PICTURE://选择本地图片
                                Intent intent=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);;
                                intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,"image/*");
                                startActivityForResult(intent,CHOOSE_PICTURE);
                                break;
                            case TAKE_PICTURE://拍照
                                Intent intent1=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                tempUri= Uri.fromFile(new File(Environment.getExternalStorageDirectory(),"image.jpg"));
                                intent1.putExtra(MediaStore.EXTRA_OUTPUT,tempUri);
                                startActivityForResult(intent1,TAKE_PICTURE);
                                break;
                        }
                    }
                });
                builder.create().show();


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
                        SharedPreferences uidSp=getSharedPreferences("config",MODE_PRIVATE);
                        uid = uidSp.getString("uid", "148");
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
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case TAKE_PICTURE:
                startPhotoZoom(tempUri);
                break;
            case CHOOSE_PICTURE:
                startPhotoZoom(data.getData());
                break;
            case CROP_SMALL_PICTURE:
                if(data!=null){
                    setImageToView(data);
                }
                break;
        }
    }
    private void startPhotoZoom(Uri uri) {
        if(uri==null){
            Log.i("tag","The uri is not exist");
        }
        tempUri=uri;
        Intent in=new Intent("com.android.camera.action.CROP");
        in.setDataAndType(uri,"image/*");
        //设置裁剪
        in.putExtra("crop","true");
        in.putExtra("aspectX",1);
        in.putExtra("aspectY",1);
        //宽高
        in.putExtra("outputX",150);
        in.putExtra("outputY",150);
        in.putExtra("return-data",true);
        startActivityForResult(in,CROP_SMALL_PICTURE);
    }
    private void setImageToView(Intent data) {
        Bundle extras=data.getExtras();
        if(extras!=null){
            Bitmap photo=extras.getParcelable("data");
            iv_update_head.setImageBitmap(photo);
            saveImage(photo);
            File file=new File(this.getCacheDir()+"/aa.jpg");
            RequestBody requestBody=RequestBody.create(MediaType.parse("multipart/form/data"),file);
            MultipartBody.Part myuid=MultipartBody.Part.createFormData("uid",uid);
            MultipartBody.Part myfile=MultipartBody.Part.createFormData("file",file.getName(),requestBody);

            new NetWorkUtils.Builder().addConverterFactory(GsonConverterFactory.create())
                    .addCalladapterFactory(RxJava2CallAdapterFactory.create())
                    .build().getApiService().upload(myuid,myfile).subscribeOn(Schedulers.io())
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
                                System.out.println("图片上传成功======="+baseBean.msg);
                            }
                            else if("1".equals(code))
                            {
                                System.out.println("图片上传失败======="+baseBean.msg);
                            }
                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onComplete() {

                        }
                    });
        }
    }
    private void saveImage(Bitmap photo) {
        File file=new File(this.getCacheDir()+"/aa.jpg");
        try {
            BufferedOutputStream bos=new BufferedOutputStream(new FileOutputStream(file));
            photo.compress(Bitmap.CompressFormat.JPEG,100,bos);
            bos.flush();
            bos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
