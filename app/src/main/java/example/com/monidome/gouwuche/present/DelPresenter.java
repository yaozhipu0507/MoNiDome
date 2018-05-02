package example.com.monidome.gouwuche.present;

import example.com.monidome.gouwuche.Bean.MessageBean;
import example.com.monidome.gouwuche.GouWuJieKou;
import example.com.monidome.gouwuche.moudle.DelModel;
import io.reactivex.Flowable;
import io.reactivex.subscribers.DisposableSubscriber;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by lenovo on 2018/4/19.
 */

public class DelPresenter implements GouWuJieKou.BasePresenter{
    private GouWuJieKou.Iview iv;
    private DisposableSubscriber subscriber2;

    public void attachView(GouWuJieKou.Iview iv) {
        this.iv = iv;
    }

    public void detachView() {
        if (iv != null) {
            iv = null;
        }

        if (!subscriber2.isDisposed()){
            subscriber2.dispose();
        }
    }

    @Override
    public void getData(String uid,String pid) {
        DelModel model = new DelModel(this);
        model.getData(uid,pid);
    }



    public void delData(Flowable<MessageBean> delFlowable) {
        subscriber2 = delFlowable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSubscriber<MessageBean>() {
                    @Override
                    public void onNext(MessageBean listMessageBean) {
                        if (listMessageBean != null) {
                            iv.delSuccess(listMessageBean);

                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        iv.onFailed((Exception) t);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
