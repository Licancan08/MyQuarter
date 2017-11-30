package licancan.com.myquarter.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;

import licancan.com.myquarter.R;
import licancan.com.myquarter.activity.DuanziActivity;

/**
 * Created by robot on 2017/11/30.
 */

public class TianjiaXRecyclerAdapter extends XRecyclerView.Adapter<TianjiaXRecyclerAdapter.ViewHolder>{
    Context context;
    ArrayList<String> list;
    public TianjiaXRecyclerAdapter(Context context, ArrayList<String> list) {
        this.context=context;
        this.list=list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.tianjia_item,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Glide.with(context)
                .load(list.get(position))
                .centerCrop()
                .into(holder.iv_itemImg);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends XRecyclerView.ViewHolder{

        public ImageView iv_itemImg;

        public ViewHolder(View itemView) {
            super(itemView);
            iv_itemImg = itemView.findViewById(R.id.iv_itemImg);
        }
    }
}
