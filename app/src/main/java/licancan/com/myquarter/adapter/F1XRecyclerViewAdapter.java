package licancan.com.myquarter.adapter;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.HashMap;
import java.util.Map;

import licancan.com.myquarter.R;

/**
 * Created by robot on 2017/12/1.
 */

public class F1XRecyclerViewAdapter extends XRecyclerView.Adapter<F1XRecyclerViewAdapter.ViewHolder>{

    Context context;
    Map<Integer,Boolean> map=new HashMap();

    public F1XRecyclerViewAdapter(Context context) {
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.f1_item,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        if(map.get(position)==null)
        {
            map.put(position,false);
        }

        //动画
        holder.f1_item_jia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(map.get(position)==false)
                {
                    holder.f1_layout_1.setVisibility(View.VISIBLE);
                    holder.f1_layout_2.setVisibility(View.VISIBLE);
                    holder.f1_layout_3.setVisibility(View.VISIBLE);


                    holder.f1_item_jia.setImageResource(R.drawable.jian);
                    float translationX = holder.f1_layout_1.getTranslationX();
                    ObjectAnimator objectAnimator=ObjectAnimator.ofFloat(holder.f1_layout_1,"translationX",translationX,-100f);

                    float translationX1 = holder.f1_layout_2.getTranslationX();
                    ObjectAnimator objectAnimator1=ObjectAnimator.ofFloat(holder.f1_layout_2,"translationX",translationX1,-200f);

                    float translationX2 = holder.f1_layout_3.getTranslationX();
                    ObjectAnimator objectAnimator2=ObjectAnimator.ofFloat(holder.f1_layout_3,"translationX",translationX2,-300f);

                    objectAnimator.setDuration(500);
                    objectAnimator1.setDuration(500);
                    objectAnimator2.setDuration(500);
                    objectAnimator.start();
                    objectAnimator1.start();
                    objectAnimator2.start();
                    map.put(position,true);
                }
                else if(map.get(position)==true)
                {
                    float translationX = holder.f1_layout_1.getTranslationX();
                    ObjectAnimator objectAnimator=ObjectAnimator.ofFloat(holder.f1_layout_1,"translationX",translationX,0f);

                    float translationX1 = holder.f1_layout_2.getTranslationX();
                    ObjectAnimator objectAnimator1=ObjectAnimator.ofFloat(holder.f1_layout_2,"translationX",translationX1,0f);

                    float translationX2 = holder.f1_layout_3.getTranslationX();
                    ObjectAnimator objectAnimator2=ObjectAnimator.ofFloat(holder.f1_layout_3,"translationX",translationX2,0f);

                    objectAnimator.setDuration(500);
                    objectAnimator1.setDuration(500);
                    objectAnimator2.setDuration(500);
                    objectAnimator.start();
                    objectAnimator1.start();
                    objectAnimator2.start();
                    objectAnimator.addListener(new Animator.AnimatorListener() {
                        @Override
                        public void onAnimationStart(Animator animator) {

                        }

                        @Override
                        public void onAnimationEnd(Animator animator) {
                            holder.f1_layout_1.setVisibility(View.INVISIBLE);
                            holder.f1_layout_2.setVisibility(View.INVISIBLE);
                            holder.f1_layout_3.setVisibility(View.INVISIBLE);
                            holder.f1_item_jia.setImageResource(R.drawable.jia);
                        }

                        @Override
                        public void onAnimationCancel(Animator animator) {

                        }

                        @Override
                        public void onAnimationRepeat(Animator animator) {

                        }
                    });
                    map.put(position,false);

                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class ViewHolder extends XRecyclerView.ViewHolder{

        public TextView f1_item_name,f1_item_time;
        public ImageView f1_item_jia;
        public LinearLayout f1_layout_1,f1_layout_2,f1_layout_3;

        public ViewHolder(View itemView) {
            super(itemView);
            f1_item_name = itemView.findViewById(R.id.f1_item_name);//昵称
            f1_item_time = itemView.findViewById(R.id.f1_item_time);//日期

            f1_item_jia = itemView.findViewById(R.id.f1_item_jia);  //动画图片

            f1_layout_1 = itemView.findViewById(R.id.f1_layout_1);
            f1_layout_2=itemView.findViewById(R.id.f1_layout_2);
            f1_layout_3=itemView.findViewById(R.id.f1_layout_3);

        }
    }
}
