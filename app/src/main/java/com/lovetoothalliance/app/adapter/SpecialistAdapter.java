package com.lovetoothalliance.app.adapter;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lovetoothalliance.app.R;
import com.lovetoothalliance.app.net.bean.response.SpecialistRespBean;
import com.textview.ExpandableTextView;
import com.utils.JavaUtils;
import com.utils.view.RatingBar;

import java.util.List;

/**
 * Author:lt
 * time:2017/8/11.
 * contact：weileng143@163.com
 *
 * @description
 */

public class SpecialistAdapter extends BaseAdapter implements ExpandableTextView.OnExpandListener {
    Context mcontext;
    private SparseArray<Integer> mPositionsAndStates = new SparseArray<>();
    List<SpecialistRespBean.DataBean> specialist;
    private LayoutInflater inflater;
    //只要在getview时为其赋值为准确的宽度值即可，无论采用何种方法
    private int etvWidth;

    public nextOrderListenter mNextOrderListenter;
    public interface nextOrderListenter{
        void startOrderListenter(int position);
    }

    public SpecialistAdapter(Context context, List<SpecialistRespBean.DataBean> mSpecialist,nextOrderListenter nextOrderListenter) {
        this.mcontext = context;
        this.specialist = mSpecialist;
        this.mNextOrderListenter=nextOrderListenter;
        inflater = LayoutInflater.from(context);
    }


    @Override
    public int getCount() {
        return specialist.size();
    }

    @Override
    public Object getItem(int position) {
        return specialist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        if (null == convertView){
            viewHolder = new ViewHolder();
            convertView = inflater.inflate(R.layout.seek_specialist_item, parent, false);
            viewHolder.etv = (ExpandableTextView) convertView.findViewById(R.id.etv);
            viewHolder.tvSeekName=(TextView)convertView.findViewById(R.id.tv_seek_name);
            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.iv_specialist);
            viewHolder.ratingBar=(RatingBar)convertView.findViewById(R.id.rb_seek);

            viewHolder.tvShopName= (TextView) convertView.findViewById(R.id.tv_sm);
            viewHolder.btnSeekXd=(Button) convertView.findViewById(R.id.btn_seek_xd);
            convertView.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

//        String content = (String)getItem(position);
        SpecialistRespBean.DataBean mSpDataBean=specialist.get(position);
        viewHolder.tvSeekName.setText(mSpDataBean.name);
        viewHolder.tvShopName.setText(mSpDataBean.ShopName);
        Glide.with(mcontext).load(mSpDataBean.OriginalImge).into(viewHolder.imageView);
        viewHolder.ratingBar.setClickable(false);
        if (mSpDataBean.stars.equals("")) {
            viewHolder.ratingBar.setStar(JavaUtils.convertToFloat("0"));
        } else {
            viewHolder.ratingBar.setStar(JavaUtils.convertToFloat(mSpDataBean.stars));
        }

        if(etvWidth == 0){
            viewHolder.etv.post(new Runnable() {
                @Override
                public void run() {
                    etvWidth = viewHolder.etv.getWidth();
                }
            });
        }
        viewHolder.etv.setTag(position);
        viewHolder.etv.setExpandListener(this);
        Integer state = mPositionsAndStates.get(position);

        viewHolder.etv.updateForRecyclerView(mcontext.getResources().getString(R.string.poem_0), etvWidth, state== null ? 0 : state);//第一次getview时肯定为etvWidth为0
        //设置字体大小为58（单位为物理像素），设置字体为红色，字体背景为黄色
//                helper.setText(R.id.etv,"北京市发布霾黄色预警，外出携带好口罩");
//                Spannable span = new SpannableString("北京市发布霾黄色预警，外出携带好口罩");
//                Spannable span = new SpannableString(getResources().getString(R.string.poem_0));
//                span.setSpan(new AbsoluteSizeSpan(58), 0, 5, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//                span.setSpan(new ForegroundColorSpan(Color.RED), 11, 16, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//                span.setSpan(new BackgroundColorSpan(Color.YELLOW), 0, 6, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//                helper.setText(R.id.etv, span);
        viewHolder.btnSeekXd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mNextOrderListenter.startOrderListenter(position);
            }
        });
        return convertView;
    }

    @Override
    public void onExpand(ExpandableTextView view) {
        Object obj = view.getTag();
        if(obj != null && obj instanceof Integer){
            mPositionsAndStates.put((Integer)obj, view.getExpandState());
        }
    }

    @Override
    public void onShrink(ExpandableTextView view) {
        Object obj = view.getTag();
        if(obj != null && obj instanceof Integer){
            mPositionsAndStates.put((Integer)obj, view.getExpandState());
        }
    }


    static class ViewHolder{
        ExpandableTextView etv;
        TextView tvSeekName;
        TextView tvShopName;
        RatingBar ratingBar;
        ImageView imageView;
        Button btnSeekXd;
    }
}
