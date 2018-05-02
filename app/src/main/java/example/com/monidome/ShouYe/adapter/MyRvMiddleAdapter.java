package example.com.monidome.ShouYe.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import java.util.List;
import example.com.monidome.R;
import example.com.monidome.ShouYe.bean.MyRvMiddleBean;

/**
 * Created by lenovo on 2018/4/28.
 */

public class MyRvMiddleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private Context context;
    private List<MyRvMiddleBean.DataBean> list;

    public MyRvMiddleAdapter(Context context, List<MyRvMiddleBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_chaoshi_grild,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyViewHolder myViewHolder = (MyViewHolder) holder;
        Glide.with(context).load(list.get(position).getIcon()).into(myViewHolder.iv);
        myViewHolder.tv.setText(list.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    class MyViewHolder extends RecyclerView.ViewHolder{
        private ImageView iv;
        private TextView tv;
        public MyViewHolder(View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.middle_imageview);
            tv = itemView.findViewById(R.id.middle_textview);
        }
    }
}
