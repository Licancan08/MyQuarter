package licancan.com.myquarter.activity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.yancy.imageselector.ImageConfig;
import com.yancy.imageselector.ImageSelector;
import com.yancy.imageselector.ImageSelectorActivity;

import java.io.File;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import licancan.com.myquarter.R;
import licancan.com.myquarter.adapter.TianjiaXRecyclerAdapter;
import licancan.com.myquarter.app.GlideLoader;
import licancan.com.myquarter.base.BaseActivity;
import licancan.com.myquarter.base.BasePresenter;
import licancan.com.myquarter.entity.BaseBean;
import licancan.com.myquarter.model.ShareDuanModel;
import licancan.com.myquarter.presenter.ShareDuanPresenter;
import licancan.com.myquarter.view.ShareDuanView;

public class DuanziActivity extends BaseActivity<ShareDuanPresenter> implements View.OnClickListener, ShareDuanView {

    private TextView tv_duan_back;
    private TextView tv_duan_share;
    private EditText duan_content;
    private ImageView iv_tianjia;

    public ArrayList<String> path = new ArrayList<>();
    private XRecyclerView tianjiaXRecyclerView;
    private TianjiaXRecyclerAdapter adapter;

    @Override
    public int getLayoutid() {
        return R.layout.activity_duanzi;
    }

    @Override
    public ShareDuanPresenter initPresenter() {
        return new ShareDuanPresenter(this,this);
    }

    @Override
    public void Creat() {
        initView();
    }

    private void initView() {
        tv_duan_back = findViewById(R.id.tv_duan_back);
        tv_duan_back.setOnClickListener(this);
        tv_duan_share = findViewById(R.id.tv_duan_share);
        tv_duan_share.setOnClickListener(this);
        duan_content = findViewById(R.id.duan_content);

        iv_tianjia = findViewById(R.id.iv_tianjia);
        iv_tianjia.setOnClickListener(this);

        tianjiaXRecyclerView = findViewById(R.id.tianjiaXRecyclerView);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.tv_duan_back:
                AlertDialog.Builder builder=new AlertDialog.Builder(this);
                builder.setMessage("是否保存到草稿箱？");
                builder.setPositiveButton("确定",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //保存到草稿箱


                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                });
                builder.create().show();
                break;
            //发表
            case R.id.tv_duan_share:
                String content = duan_content.getText().toString();
                //String s=new String(content.getBytes([ISO]),"UTF-8");
                SharedPreferences sp=getSharedPreferences("config",MODE_PRIVATE);
                String uid = sp.getString("uid", "148");
                System.out.println("----"+uid);
                System.out.println("URLEncoder.encode(content)你好"+content);
                ShowToast("你好");
                List<File> fileList=new ArrayList<>();
                for (String s : path) {
                    File file=new File(s);
                    fileList.add(file);
                }
                ShareDuanModel sm = new ShareDuanModel();
                sm.shareDuanzi(DuanziActivity.this,uid, content,fileList);
                sm.setiShareDuanzi(new ShareDuanModel.iShareDuanzi() {
                    @Override
                    public void ShareDuanziSuccess(String BaseBean) {
                        finish();
                    }

                    @Override
                    public void ShareDuanziFailure(String BaseBean) {

                    }
                });
                //presenter.shareDuan(uid, content,fileList);
                break;
            //选择图片进行上传
            case R.id.iv_tianjia:
                ImageConfig imageConfig= new ImageConfig.Builder(
                        // GlideLoader 可用自己用的缓存库
                        new GlideLoader())
                        // 如果在 4.4 以上，则修改状态栏颜色 （默认黑色）
                        .steepToolBarColor(getResources().getColor(R.color.blue))
                        // 标题的背景颜色 （默认黑色）
                        .titleBgColor(getResources().getColor(R.color.blue))
                        // 提交按钮字体的颜色  （默认白色）
                        .titleSubmitTextColor(getResources().getColor(R.color.white))
                        // 标题颜色 （默认白色）
                        .titleTextColor(getResources().getColor(R.color.white))
                        // 开启多选   （默认为多选）  (单选 为 singleSelect)
                        //.singleSelect()
//                        .crop()
                        // 多选时的最大数量   （默认 9 张）
                        .mutiSelectMaxSize(9)
                        // 已选择的图片路径
                        .pathList(path)
                        // 拍照后存放的图片路径（默认 /temp/picture）
                        .filePath("/ImageSelector/Pictures")
                        // 开启拍照功能 （默认开启）
                        .showCamera()
                        .requestCode(REQUEST_CODE)
                        .build();


                ImageSelector.open(DuanziActivity.this, imageConfig);   // 开启图片选择器
                GridLayoutManager layoutManager = new GridLayoutManager(DuanziActivity.this, 3);
                tianjiaXRecyclerView.setLayoutManager(layoutManager);
                adapter = new TianjiaXRecyclerAdapter(DuanziActivity.this,path);
                tianjiaXRecyclerView.setAdapter(adapter);

                break;
        }
    }



    public static final int REQUEST_CODE = 1000;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            List<String> pathList = data.getStringArrayListExtra(ImageSelectorActivity.EXTRA_RESULT);

            for (String path : pathList) {
                Log.i("ImagePathList", path);
            }

            path.clear();
            path.addAll(pathList);
            adapter.notifyDataSetChanged();


        }
    }

    @Override
    public void RequestSuccess(BaseBean baseBean) {
        ShowToast(baseBean.msg);//发布成功

        finish();

    }

    @Override
    public void RequestFailure(BaseBean baseBean) {
        ShowToast(baseBean.msg);
    }

    @Override
    public void Failure(BaseBean baseBean) {
        ShowToast(baseBean.msg);
    }
}
