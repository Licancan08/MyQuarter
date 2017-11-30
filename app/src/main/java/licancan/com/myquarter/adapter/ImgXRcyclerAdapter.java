package licancan.com.myquarter.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import licancan.com.myquarter.R;

/**
 * Created by robot on 2017/11/30.
 */

public class ImgXRcyclerAdapter extends RecyclerView.Adapter<ImgXRcyclerAdapter.ViewHolder>{

    Context context;
    String[] split;

    public ImgXRcyclerAdapter(Context context, String[] split) {
        this.context=context;
        this.split=split;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.img_item,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if(split.length==1)
        {
            holder.iv_itemImg1.setVisibility(View.VISIBLE);
            Glide.with(context).load(split[position]).into(holder.iv_itemImg1);
        }
        else if(split.length==2)
        {
            holder.iv_itemImg2.setVisibility(View.VISIBLE);
            Glide.with(context).load(split[position]).into(holder.iv_itemImg2);
        }else
        {
            holder.iv_itemImg3.setVisibility(View.VISIBLE);
            Glide.with(context).load(split[position]).into(holder.iv_itemImg3);
        }

    }

    @Override
    public int getItemCount() {
        return split.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public ImageView iv_itemImg1,iv_itemImg2,iv_itemImg3;

        public ViewHolder(View itemView) {
            super(itemView);
            iv_itemImg1 = itemView.findViewById(R.id.iv_itemImg1);
            iv_itemImg2 = itemView.findViewById(R.id.iv_itemImg2);
            iv_itemImg3 = itemView.findViewById(R.id.iv_itemImg3);
        }
    }
}
