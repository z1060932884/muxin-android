package com.zzj.muxin.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zzj.muxin.R;


/**
 * 个人中心item
 * Created by JamesZhang on 2018/8/15.
 */

public class MineItemGroup extends FrameLayout{

    private RelativeLayout rl_item_group;
    private TextView mTvTitle;
    private ImageView mIvIcon;
    private View line_view,mView;

    private String mTitle;
    private Drawable mDrawable_left,mDrawable_right;
    private int line_color,line_height;

    private MineItemGroupOnClickListener itemGroupOnClickListener;

    public MineItemGroup(@NonNull Context context) {
        this(context,null);
    }

    public MineItemGroup(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MineItemGroup(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttrs(context,attrs);
        initView(context);
        setData();
    }


    /**
     * 初始化属性
     * @param context
     * @param attributeSet
     */
    private void initAttrs(Context context,AttributeSet attributeSet){
        TypedArray typedArray = context.obtainStyledAttributes(attributeSet, R.styleable.MineItemGroup);
        mTitle = typedArray.getString(R.styleable.MineItemGroup_mTitle);
        mDrawable_left = typedArray.getDrawable(R.styleable.MineItemGroup_drawable_left);
        mDrawable_right = typedArray.getDrawable(R.styleable.MineItemGroup_drawable_right);
        line_color = typedArray.getColor(R.styleable.MineItemGroup_line_color,0xff999999);
        line_height = typedArray.getInteger(R.styleable.MineItemGroup_line_height,1);
        typedArray.recycle();
    }

    private void initView(Context context){
        mView = LayoutInflater.from(context).inflate(R.layout.minelib_mineitemgroup,null);
        mTvTitle = mView.findViewById(R.id.tv_title);
        rl_item_group = mView.findViewById(R.id.rl_item_group);
        mIvIcon = mView.findViewById(R.id.iv_right);
        line_view = mView.findViewById(R.id.line);
        //将view添加到布局中
        this.addView(mView);
        rl_item_group.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("onClick----->","点击了-------");
                if(itemGroupOnClickListener!=null){
                    itemGroupOnClickListener.onItemClickListener(v);
                }
            }
        });
    }

    private void setData(){
        mTvTitle.setText(mTitle);
        if(mDrawable_left!=null){
            mDrawable_left.setBounds(0,0,72,72);
            mTvTitle.setCompoundDrawables(mDrawable_left,null,null,null);
        }
        if(mDrawable_right!=null){
            mIvIcon.setImageDrawable(mDrawable_right);
        }
        line_view.setBackgroundColor(line_color);
        line_view.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,line_height));
    }


    public void setItemGroupOnClickListener(MineItemGroupOnClickListener itemGroupOnClickListener) {
        this.itemGroupOnClickListener = itemGroupOnClickListener;
    }

    public interface MineItemGroupOnClickListener{
        void onItemClickListener(View view);
    }
}
