package com.gin.praktice.adapter;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gin.praktice.R;
import com.gin.praktice.component.Component;
import com.gin.praktice.component.Member;

import java.util.ArrayList;
import java.util.List;

public class LocationRecyclerAdapter extends RecyclerView.Adapter<LocationRecyclerAdapter.ItemViewHolder> {

    private List<Component> items;
    private final ArrayList<Integer> selected = new ArrayList<>();

    public LocationRecyclerAdapter(List<Component> items) {

        this.items = items;
    }


    // 새로운 뷰 홀더 생성
    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.content_recyclerview, viewGroup, false);

        return new LocationRecyclerAdapter.ItemViewHolder(view);
    }


    // View 의 내용을 해당 포지션의 데이터
    @Override
    public void onBindViewHolder(LocationRecyclerAdapter.ItemViewHolder holder, int index) {

        holder.mNameTv.setText(items.get(index).getName());
        if (((Member)items.get(index)).isOneSecond)
        {
            holder.mNameTv.append("        " + "1/2");
        }
        else if (((Member)items.get(index)).isOneThird)
        {
            holder.mNameTv.append("        " + "1/3");
        }

        if (!selected.contains(index)){
            holder.itemView.setBackgroundColor(Color.LTGRAY);
        }
        else {
            holder.itemView.setBackgroundColor(Color.CYAN);
        }

    }

    // 데이터 셋의 크기를 리턴
    @Override
    public int getItemCount() {
        return items.size();
    }

    // 커스텀 뷰홀더
    // item layout 에 존재하는 위젯들을 바인딩
    class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView mNameTv;

        public ItemViewHolder(View itemView) {
            super(itemView);

            mNameTv = (TextView) itemView.findViewById(R.id.contentName);
            itemView.setOnClickListener(this);
//            itemView.setOnFocusChangeListener(this);
        }

        @Override
        public void onClick(View view) {
//            Toast.makeText(view.getContext(), "position = " + getAdapterPosition(), Toast.LENGTH_SHORT).show();

            if (!selected.contains(getAdapterPosition()))
            {
                selected.clear();
                selected.add(getAdapterPosition());
            }
            else
            {
                selected.clear();
            }
            notifyDataSetChanged();
        }

    }

    public List<Integer> getSelectedList() {
        return this.selected;
    }

    public void setItems(List<Component> items) { this.items = items; }
    public List<Component> getItems() { return this.items; }
    public void clearItems() { this.items.clear(); }
}
