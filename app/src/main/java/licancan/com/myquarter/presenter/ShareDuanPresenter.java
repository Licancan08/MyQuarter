package licancan.com.myquarter.presenter;

import android.content.Context;

import java.io.File;
import java.util.List;

import licancan.com.myquarter.base.BasePresenter;
import licancan.com.myquarter.base.BaseView;
import licancan.com.myquarter.entity.BaseBean;
import licancan.com.myquarter.model.ShareDuanModel;
import licancan.com.myquarter.view.ShareDuanView;

/**
 * Created by robot on 2017/11/28.
 */

public class ShareDuanPresenter extends BasePresenter<ShareDuanView> implements ShareDuanModel.iShareDuanzi{
    Context context;
    private final ShareDuanModel shareDuanModel;

    public ShareDuanPresenter(ShareDuanView mView,Context context) {
        super(mView);
        this.context=context;
        shareDuanModel = new ShareDuanModel();
        shareDuanModel.setiShareDuanzi(this);
    }

    public void shareDuan(String uid, String content, List<File> files){
       // shareDuanModel.shareDuanzi(uid,content,files);
    }

    @Override
    public void ShareDuanziSuccess(String baseBean) {
        //mView.RequestSuccess(baseBean);
    }

    @Override
    public void ShareDuanziFailure(String baseBean) {
      //  mView.RequestFailure(baseBean);
    }
}
