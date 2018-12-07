package com.zzj.muxin.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Toast;


import com.zzj.muxin.R;
import com.zzj.muxin.fixedheader.PinnedHeaderItemDecoration;
import com.zzj.muxin.fixedheader.PinnedHeaderRecyclerView;
import com.zzj.muxin.fixedheader.adapter.FriendsGroupAdapter;
import com.zzj.muxin.fixedheader.entity.ExpandGroupItemEntity;
import com.zzj.muxin.fixedheader.entity.FriendsItem;
import com.zzj.muxin.viewmodel.LinkManFriendViewModel;
import com.zzj.mvvm.base.BaseLifecycleFragment;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * author : zzj
 * e-mail : zhangzhijun@pansoft.com
 * date   : 2018/8/2916:41
 * desc   : 联系人好友
 * version: 1.0
 */
public class LinkManFriendFragment extends BaseLifecycleFragment<LinkManFriendViewModel> {

    private PinnedHeaderRecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManager;
    private FriendsGroupAdapter mAdapter;

    @Override
    protected void initWidget(View root) {
        super.initWidget(root);
        mRecyclerView = root.findViewById(R.id.recycler_view_osplinkman_friend);
        mRecyclerView.setLayoutManager(mLayoutManager = new LinearLayoutManager(_mActivity));
        mRecyclerView.addItemDecoration(new PinnedHeaderItemDecoration());
    }

    @Override
    protected void lazyLoad() {
        super.lazyLoad();

        initAdapter();
        initListener();
    }

    @Override
    protected Object getStateEventKey() {
        return TAG;
    }

    @Override
    protected void initListener() {
        super.initListener();
        /**
         * 当标题栏被悬浮的时候的点击功能
         */
        mRecyclerView.setOnPinnedHeaderClickListener(new PinnedHeaderRecyclerView.OnPinnedHeaderClickListener() {
            @Override
            public void onPinnedHeaderClick(int adapterPosition) {
                mAdapter.switchExpand(adapterPosition);
                //标题栏被点击之后，滑动到指定位置
                mLayoutManager.scrollToPositionWithOffset(adapterPosition, 0);
            }
        });
        mAdapter.setOnItemClickListener(new FriendsGroupAdapter.OnItemClickListener() {
            @Override
            public void onItemClickListener(FriendsItem subItem, int position) {
                Toast.makeText(_mActivity,subItem.getUser()+"被惦记了",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void initAdapter() {

        mAdapter = new FriendsGroupAdapter();
        mAdapter.setData(getDataList());
        mRecyclerView.setAdapter(mAdapter);
    }

    private List<ExpandGroupItemEntity<String,FriendsItem>> getDataList() {
        List<ExpandGroupItemEntity<String, FriendsItem>> dataList = new ArrayList<>();
        for (int group = 0; group < 10; group++) {
            ExpandGroupItemEntity<String, FriendsItem> groupItem = new ExpandGroupItemEntity<>();
            groupItem.setExpand(false);
            groupItem.setParent("我的好友 " + group);
            List<FriendsItem> childList = new ArrayList<>();
            for (int child = 0; child < group + 1; child++) {
                FriendsItem childItem = new FriendsItem();
                childItem.setTime(new Date().toString());
                childItem.setFactoryName((2000 + child) + " 项目");
                childItem.setUser("好友 " + child);
                childItem.setState(child % 5);
                childList.add(childItem);
            }
            groupItem.setChildList(childList);
            dataList.add(groupItem);
        }

        return dataList;
    }


    @Override
    protected int getContentLayoutId() {
        return R.layout.osplinkman_friend_fragment;
    }
}
