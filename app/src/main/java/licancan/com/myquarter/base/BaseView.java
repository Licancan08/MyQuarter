package licancan.com.myquarter.base;

/**
 * Created by robot on 2017/11/28.
 */

public interface BaseView<T> {
    void RequestSuccess(T t);
    void RequestFailure(T t);
    void Failure(T t);
}
