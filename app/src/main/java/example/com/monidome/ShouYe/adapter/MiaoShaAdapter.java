package example.com.monidome.ShouYe.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import java.util.List;
import example.com.monidome.R;
import example.com.monidome.ShouYe.bean.ShouYeBean;
import example.com.monidome.gouwuche.GouWuCheActivity;


/**
 * Created by lenovo on 2018/4/27.
 */

public class MiaoShaAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<ShouYeBean.MiaoshaBean.ListBeanX> list;
    //2、定义一个属性
    private OnItemClickListener onItemClickListener;

    //1、接口回调第一步，先定义一个接口
    public interface OnItemClickListener {
        void onItemClick(ShouYeBean.MiaoshaBean.ListBeanX listBean);
    }

    //3、定义一个方法
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public MiaoShaAdapter(Context context, List<ShouYeBean.MiaoshaBean.ListBeanX> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_miaosha, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyViewHolder myViewHolder = (MyViewHolder) holder;
        final ShouYeBean.MiaoshaBean.ListBeanX beanX = list.get(position);
        String[] split = beanX.getImages().split("\\|");
        Glide.with(context).load(split[0]).into(myViewHolder.iv);
        myViewHolder.price_new.setText("￥" + beanX.getPrice());
        myViewHolder.price_lod.setText("￥" + beanX.getSalenum());
        myViewHolder.ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //就是跳转
                Intent intent = new Intent(context, GouWuCheActivity.class);
                intent.putExtra("pscid", beanX.getPscid() + "");
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView iv;
        private TextView price_new;
        private TextView price_lod;
        private LinearLayout ll;

        public MyViewHolder(View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.zhuyerecycleview2_iv);
            price_new = itemView.findViewById(R.id.zhuyerecycleview2_tv_price_new);
            price_lod = itemView.findViewById(R.id.zhuyerecycleview2_tv_price_lod);
            ll = itemView.findViewById(R.id.zhuyeshouji);
            price_lod.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        }
    }
}
