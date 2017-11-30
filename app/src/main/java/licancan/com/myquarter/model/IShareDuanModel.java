package licancan.com.myquarter.model;

import android.content.Context;

import java.io.File;
import java.util.List;

/**
 * Created by robot on 2017/11/28.
 */

public interface IShareDuanModel {
    void shareDuanzi(Context context,String uid, String content, List<File> files);
}
