package example.com.monidome.ShouYe;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.youth.banner.Banner;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import example.com.monidome.R;
import example.com.monidome.ShouYe.adapter.MiaoShaAdapter;
import example.com.monidome.ShouYe.adapter.MyRvMiddleAdapter;
import example.com.monidome.ShouYe.adapter.TuiJianAdapter;
import example.com.monidome.ShouYe.bean.MyRvMiddleBean;
import example.com.monidome.ShouYe.bean.ShouYeBean;
import example.com.monidome.utils.GlideImageLoader;
import example.com.monidome.utils.LooperTextView;


public class ShouYeActivity extends AppCompatActivity implements ShouYeJieKou.iView {

    @BindView(R.id.ErClick)
    ImageView mErClick;
    @BindView(R.id.selectShop)
    EditText mSelectShop;
    @BindView(R.id.info_show_type)
    ImageView mInfoShowType;
    @BindView(R.id.shouyebanner)
    Banner mShouyebanner;
    @BindView(R.id.syiv)
    ImageView mSyiv;
    @BindView(R.id.grildrecycleview)
    RecyclerView mGrildrecycleview;
    @BindView(R.id.shouye_time)
    TextView mShouyeTime;
    @BindView(R.id.ltv)
    LooperTextView mLtv;
    @BindView(R.id.rlms)
    LinearLayout mRlms;
    @BindView(R.id.shouyemiaoshao)
    RecyclerView miaosharecycleview;
    @BindView(R.id.shouyetuijian)
    TextView mShouyetuijian;
    @BindView(R.id.zhuyerecycleview3)
    RecyclerView tuijianrecycleview;
    private ShouYePresenter shouYePresenter;
    private SimpleDateFormat format;
    private Handler handler = new Handler();
    private long recLen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shou_ye);
        ButterKnife.bind(this);

        shouYePresenter = new ShouYePresenter(this);
        shouYePresenter.getBannerUrl(this);

        shouYePresenter.getMiddleViewUrl(this);

        mLtv.setTipList(generateTips());

    }

    /**
     * 中间的RecyclerView
     */
    @Override
    public void middleView(List<MyRvMiddleBean.DataBean> list) {
        GridLayoutManager manager = new GridLayoutManager(this, 2, OrientationHelper.HORIZONTAL, false);
        mGrildrecycleview.setLayoutManager(manager);
        MyRvMiddleAdapter adapter = new MyRvMiddleAdapter(ShouYeActivity.this, list);
        mGrildrecycleview.setAdapter(adapter);
    }

    @Override
    public void shouYeRV(ShouYeBean shouYeBean) {
        /**
         * 设置秒杀倒计时
         */
        ShouYeBean.MiaoshaBean miaoshaBean = shouYeBean.getMiaosha();
        recLen = miaoshaBean.getTime();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                recLen -= 1000;
                format = new SimpleDateFormat("HH:mm:ss");
                final String str = format.format(recLen);
                mShouyeTime.setText(str);
                handler.postDelayed(this, 1000);
            }
        };
        handler.postDelayed(runnable, 100);
        /*
         * 设置到RecycleView
         */
        GridLayoutManager manager = new GridLayoutManager(this, 1, GridLayoutManager.HORIZONTAL, false);
        miaosharecycleview.setLayoutManager(manager);
        MiaoShaAdapter miaoShaAdapter = new MiaoShaAdapter(this, miaoshaBean.getList());
        miaosharecycleview.setAdapter(miaoShaAdapter);
        /*
         * 设置首页推荐
         */
        ShouYeBean.TuijianBean tuijian = shouYeBean.getTuijian();
        mShouyetuijian.setText(tuijian.getName());
        GridLayoutManager manager2 = new GridLayoutManager(this, 2, OrientationHelper.VERTICAL, false);
        tuijianrecycleview.setLayoutManager(manager2);
        TuiJianAdapter tuiJianAdapter = new TuiJianAdapter(this, tuijian.getList());
        tuijianrecycleview.setAdapter(tuiJianAdapter);

    }

    /*
     * 顶部的Banner以及banner的点击事件
     * @param list
     *
     */


    @Override
    public void shouBanner(final ShouYeBean shouYeBean) {
        final List<ShouYeBean.DataBean> data = shouYeBean.getData();
        List<String> images = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            images.add(data.get(i).getIcon());
        }

        mShouyebanner.setImages(images);
        mShouyebanner.setImageLoader(new GlideImageLoader());
        mShouyebanner.setBannerAnimation(Transformer.DepthPage);
        mShouyebanner.start();
        mShouyebanner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                if (data.get(position).getUrl().length() < 1) {
                    return;
                }
                Toast.makeText(ShouYeActivity.this, "准备跳转到WebView!!!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ShouYeActivity.this, ShouYeWebView.class);
                intent.putExtra("WebViewUrl", data.get(position).getUrl());
                ShouYeActivity.this.startActivity(intent);
            }
        });
    }

    /**
     * 首页跑马灯效果
     *
     * @return
     */
    private List<String> generateTips() {
        List<String> tips = new ArrayList<>();
        tips.add("AI就要掌控世界了？绝对没你想得那么快！");
        tips.add("衣服大一号,人就瘦一圈?");
        tips.add("闪瞎:全球最贵五辆摩托车");
        tips.add("一半受访者会被类人机器人吓跑!");
        tips.add("深度学习索引速度更快、占用空间更少");
        tips.add("资源| 谷歌开源TFGAN：轻量级生成对抗网络工具库?");
        tips.add("谷歌团队越狱苹果系统");
        return tips;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        shouYePresenter.Dettach();
    }

    @OnClick({R.id.ErClick, R.id.selectShop, R.id.info_show_type, R.id.grildrecycleview,  R.id.shouyemiaoshao, R.id.shouyetuijian })
    public void onClick(View v) {
        switch (v.getId()) {
            //扫一扫
            case R.id.ErClick:
                //ErClick();
                Toast.makeText(ShouYeActivity.this, "扫一扫正在开发中......", Toast.LENGTH_SHORT).show();
                break;
            //搜索
            case R.id.selectShop:

                Toast.makeText(ShouYeActivity.this, "搜索正在开发中......", Toast.LENGTH_SHORT).show();
                break;
            //消息
            case R.id.info_show_type:
                Toast.makeText(ShouYeActivity.this, "消息正在集成中......", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
