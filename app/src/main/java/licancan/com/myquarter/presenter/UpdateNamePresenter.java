package licancan.com.myquarter.presenter;

import android.content.Context;

import licancan.com.myquarter.base.BasePresenter;
import licancan.com.myquarter.entity.BaseBean;
import licancan.com.myquarter.model.UpdateNameModel;
import licancan.com.myquarter.view.UpdateNickNameView;

/**
 * Created by robot on 2017/11/29.
 */

public class UpdateNamePresenter extends BasePresenter<UpdateNickNameView> implements UpdateNameModel.iUpdateName{

    Context context;
    private final UpdateNameModel updateNameModel;

    public UpdateNamePresenter(UpdateNickNameView mView,Context context) {
        super(mView);
        this.context=context;
        updateNameModel = new UpdateNameModel();
        updateNameModel.setiUpdateName(this);
    }

    public void updateName(String uid,String nickname)
    {
        updateNameModel.updateName(uid,nickname);
    }

    @Override
    public void UpdateNameSuccess(BaseBean baseBean) {
        mView.RequestSuccess(baseBean);
    }

    @Override
    public void UpdateNameFailure(BaseBean baseBean) {
        mView.RequestFailure(baseBean);
    }
}
