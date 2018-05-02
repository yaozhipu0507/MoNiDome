package example.com.monidome.gouwuche.moudle;

import java.util.List;
import example.com.monidome.gouwuche.Bean.DatasBean;
import example.com.monidome.gouwuche.Bean.MessageBean;
import example.com.monidome.gouwuche.GouWuJieKou;
import example.com.monidome.gouwuche.present.NewsPresenter;
import example.com.monidome.utils.RetrofitUtils;
import io.reactivex.Flowable;

/**
 * Created by lenovo on 2018/4/19.
 */

public class NewsModel implements GouWuJieKou.IModel{
    private NewsPresenter presenter;

    public NewsModel(NewsPresenter presenter) {
        this.presenter = (NewsPresenter) presenter;

    }

    @Override
    public void getData(String uid, String pid) {
        Flowable<MessageBean<List<DatasBean>>> flowable = RetrofitUtils.getInstance().getApiService().getDatas(uid);
        presenter.getNews(flowable);

    }
}
