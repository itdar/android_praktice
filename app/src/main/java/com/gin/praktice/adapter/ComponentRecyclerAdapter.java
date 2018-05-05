package com.gin.praktice.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gin.praktice.R;
import com.gin.praktice.component.Component;

import java.util.List;


public class ComponentRecyclerAdapter extends RecyclerView.Adapter<ComponentRecyclerAdapter.ItemViewHolder> {
    List<Component> items;

    public ComponentRecyclerAdapter(List<Component> items) {
        this.items = items;
    }


    // 새로운 뷰 홀더 생성
    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_recyclerview, parent, false);
        return new ItemViewHolder(view);
    }


    // View 의 내용을 해당 포지션의 데이터로 바꿉니다.
    @Override
    public void onBindViewHolder(ItemViewHolder holder, int index) {
        holder.mNameTv.setText(items.get(index).getName());
    }

    // 데이터 셋의 크기를 리턴해줍니다.
    @Override
    public int getItemCount() {
        return items.size();
    }

    // 커스텀 뷰홀더
    // item layout 에 존재하는 위젯들을 바인딩합니다.
    class ItemViewHolder extends RecyclerView.ViewHolder {
        private TextView mNameTv;

        public ItemViewHolder(View itemView) {
            super(itemView);
            mNameTv = (TextView) itemView.findViewById(R.id.contentName);
        }
    }
}
