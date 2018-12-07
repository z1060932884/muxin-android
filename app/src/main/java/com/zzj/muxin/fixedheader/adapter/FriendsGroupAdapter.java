package com.zzj.muxin.fixedheader.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zzj.muxin.R;
import com.zzj.muxin.fixedheader.entity.ExpandGroupItemEntity;
import com.zzj.muxin.fixedheader.entity.FriendsItem;


/**
 * @author zzj
 * @desc 星际联系人分组悬浮列表的适配器
 */
public class FriendsGroupAdapter extends RecyclerExpandBaseAdapter<String, FriendsItem, RecyclerView.ViewHolder> {

	private OnItemClickListener onItemClickListener;

	public interface OnItemClickListener{
		void onItemClickListener(FriendsItem subItem, int position);
	}

	public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
		this.onItemClickListener = onItemClickListener;
	}

	/**
	 * 悬浮标题栏被点击的时候，展开收起切换功能
	 */
	public void switchExpand(int adapterPosition) {
		int groupIndex = mIndexMap.get(adapterPosition).getGroupIndex();
		ExpandGroupItemEntity entity = mDataList.get(groupIndex);
		entity.setExpand(!entity.isExpand());
		notifyDataSetChanged();
	}

	@Override
	public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		if (viewType == VIEW_TYPE_ITEM_TITLE) {
			TitleItemHolder holder = new TitleItemHolder(
				LayoutInflater.from(parent.getContext()).inflate(R.layout.item_expand_order_title, parent, false));
			holder.itemView.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					ExpandGroupItemEntity entity = (ExpandGroupItemEntity) v.getTag();
					entity.setExpand(!entity.isExpand());
					notifyDataSetChanged();
				}
			});
			return holder;
		} else {
			return new SubItemHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_expand_order_sub, parent, false));
		}
	}

	@Override
	public RecyclerView.ViewHolder onCreatePinnedViewHolder(ViewGroup parent, int viewType) {
		TitleItemHolder holder = (TitleItemHolder) super.onCreatePinnedViewHolder(parent, viewType);
		return holder;
	}

	@Override
	public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
		if (getItemViewType(position) == VIEW_TYPE_ITEM_TITLE) {
			int groupIndex = mIndexMap.get(position).getGroupIndex();
			TitleItemHolder itemHolder = (TitleItemHolder) holder;
			itemHolder.itemView.setTag(mDataList.get(groupIndex));
			itemHolder.mTextTitle.setText(mDataList.get(groupIndex).getParent());

		} else {
			final SubItemHolder subHolder = (SubItemHolder) holder;

			int groupIndex = mIndexMap.get(position).getGroupIndex();
			int childIndex = mIndexMap.get(position).getChildIndex();
			final FriendsItem subItem = mDataList.get(groupIndex).getChildList().get(childIndex);
			subHolder.itemView.setTag(subItem);
			subHolder.mTextUsers.setText(subItem.getUser());
			subHolder.mTextState.setText(subItem.getTime());
			//添加点击事件
			if( onItemClickListener!=null ){
				subHolder.itemView.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						onItemClickListener.onItemClickListener(subItem,position);
					}
				});

			}
		}
	}

	@Override
	public void onBindPinnedViewHolder(RecyclerView.ViewHolder holder, int position) {
		super.onBindPinnedViewHolder(holder, position);
		TitleItemHolder itemHolder = (TitleItemHolder) holder;
	}



	static class TitleItemHolder extends RecyclerView.ViewHolder {

		TextView  mTextTitle;
		ImageView mImageExpandFlag;

		TitleItemHolder(View itemView) {
			super(itemView);
			mTextTitle = itemView.findViewById(R.id.text_title);
			mImageExpandFlag = itemView.findViewById(R.id.image_expand_flag);
		}
	}

	static class SubItemHolder extends RecyclerView.ViewHolder {

		TextView  mTextUsers;
		TextView  mTextState;

		SubItemHolder(View itemView) {
			super(itemView);
			mTextUsers = itemView.findViewById(R.id.tv_linkman_friend_name);
			mTextState = itemView.findViewById(R.id.tv_linkman_friend_status);

		}
	}
}
