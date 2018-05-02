package example.com.monidome.gouwuche;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import java.util.ArrayList;
import java.util.List;
import example.com.monidome.R;
import example.com.monidome.gouwuche.Bean.DatasBean;
import example.com.monidome.gouwuche.Bean.MessageBean;
import example.com.monidome.gouwuche.Bean.MessageEvent;
import example.com.monidome.gouwuche.Bean.PriceAndCountEvent;
import example.com.monidome.gouwuche.Bean.SomeId;
import example.com.monidome.gouwuche.present.DelPresenter;
import example.com.monidome.gouwuche.present.NewsPresenter;

public class GouWuCheActivity extends AppCompatActivity implements GouWuJieKou.Iview{


    private String uid = "3981";
    private NewsPresenter presenter;
    private CheckBox mCheckbox2;
    private ExpandableListView mElv;

    /**
     * 0
     */
    private TextView mTvPrice;
    /**
     * 结算(0)
     */
    private TextView mTvNum;
    private MyAdapter adapter;
    private List<DatasBean> groupList = new ArrayList<>();
    private List<List<DatasBean.ListBean>> childList = new ArrayList<>();
    private String pid;
    private DelPresenter delPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gou_wu_che);

        EventBus.getDefault().register(this);

        presenter = new NewsPresenter();
        presenter.attachView(this);
        delPresenter = new DelPresenter();
        delPresenter.attachView(this);
        initView();
        adapter = new MyAdapter(this, groupList, childList);
        mElv.setAdapter(adapter);


        mCheckbox2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.changeAllListCbState(mCheckbox2.isChecked());
            }
        });

    }

    private void initView() {
        mElv = findViewById(R.id.elv);
        mCheckbox2 = findViewById(R.id.checkbox2);
        mTvPrice =findViewById(R.id.tv_price);
        mTvNum = findViewById(R.id.tv_num);
    }

    @Override
    public void onSuccess(Object o) {
        if (o != null) {
            List<DatasBean> list = (List<DatasBean>) o;
            if (list != null) {
                groupList.addAll(list);
                for (int i = 0; i < list.size(); i++) {
                    List<DatasBean.ListBean> datas = list.get(i).getList();
                    childList.add(datas);
                }

                adapter.notifyDataSetChanged();
                mCheckbox2.setChecked(true);

                adapter.changeAllListCbState(true);
                mElv.setGroupIndicator(null);
                for (int i = 0; i < groupList.size(); i++) {
                    mElv.expandGroup(i);
                }

            }
        }
    }

    @Override
    public void onFailed(Exception e) {

    }

    @Override
    public void delSuccess(MessageBean listMessageBean) {
        Toast.makeText(this, listMessageBean.getMsg(), Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.getData(uid, pid);

    }



    @Subscribe
    public void onMessageEvent(MessageEvent event) {
        mCheckbox2.setChecked(event.isChecked());
    }

    @Subscribe
    public void onMessageEvent(PriceAndCountEvent event) {
        mTvNum.setText("结算(" + event.getCount() + ")");
        mTvPrice.setText("￥"+event.getPrice() );
    }
    @Subscribe
    public void onMessageEvent(SomeId event) {
        pid = event.getPid();
        Log.e("zxz","我得到了pid:"+pid);
        delPresenter.getData(uid,pid);


    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        if (presenter != null) {
            presenter.detachView();
        }
    }
}
