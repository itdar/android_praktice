package com.gin.praktice.adapter;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gin.praktice.R;
import com.gin.praktice.component.Component;
import com.gin.praktice.component.Squad;

import java.util.ArrayList;
import java.util.List;


public class SquadRecyclerAdapter extends RecyclerView.Adapter<SquadRecyclerAdapter.ItemViewHolder> {

    private List<Component> squadItems;
//    private List<Component> memberItems;
    private ComponentRecyclerAdapter memberListAdapter;

    private final ArrayList<Integer> selected = new ArrayList<>();

    public SquadRecyclerAdapter(List<Component> squadItems, List<Component> memberItems, ComponentRecyclerAdapter memberListAdapter) {
        this.squadItems = squadItems;
//        this.memberItems = memberItems;
        this.memberListAdapter = memberListAdapter;
    }


    // 새로운 뷰 홀더 생성
    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.content_recyclerview, viewGroup, false);

        return new ItemViewHolder(view);
    }


    /**
     * ViewList 에서 각 index 별로 들어가서 보여질 객체의 내용물과 배경컬러값 수정 가능
     *
     * @param holder
     * @param index
     */
    @Override
    public void onBindViewHolder(ItemViewHolder holder, int index) {

        holder.mNameTv.setText(squadItems.get(index).getName());

        if (!selected.contains(index)){
            holder.itemView.setBackgroundColor(Color.LTGRAY);
        }
        else {
            holder.itemView.setBackgroundColor(Color.GREEN);
        }

    }

    // 데이터 셋의 크기를 리턴
    @Override
    public int getItemCount() {
        return squadItems.size();
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

        /**
         * If selected list already has same index, clear / if not -> select and coloring
         * Show the members of the selected Squad on the MemberRecyclerView
         * @param view
         */
        @Override
        public void onClick(View view) {
//            Toast.makeText(view.getContext(), "position = " + getAdapterPosition(), Toast.LENGTH_SHORT).show();

            if (!selected.contains(getAdapterPosition())) {
                selected.clear();

                memberListAdapter.clearItems();
                memberListAdapter.setItems(((Squad)squadItems.get(getAdapterPosition()).clone()).getList());
                selected.add(getAdapterPosition());
            } else {
                memberListAdapter.clearItems();
                selected.clear();
            }
//            Toast.makeText(view.getContext(), memberItems.toString(), Toast.LENGTH_LONG).show();

            memberListAdapter.notifyDataSetChanged();
            notifyDataSetChanged();
        }
    }

    public List<Integer> getSelectedList() { return this.selected; }

//    public void setItems(List<Component> items) { this.items = items; }
//    public void clearItems() { this.items.clear(); }
}
