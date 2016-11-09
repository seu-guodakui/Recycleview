package com.kevinguo.guodakui.recycleviewmacdemo;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by guodakui on 16/11/5.
 */

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ItemViewHolder>{

    private List<String> mDatas;
    private LayoutInflater minflater;
    private OnitermClickLister onitermClickLister;
    public ListAdapter(Context context,List<String> data){
        this.mDatas=data;
        minflater = LayoutInflater.from(context);
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        ItemViewHolder holder = new ItemViewHolder(minflater.inflate(
                R.layout.item_layout,parent,false));
        return holder;
    }

    @SuppressLint("NewApi")
    @Override
    public void onBindViewHolder(final ItemViewHolder holder, int position) {
        holder.mtextView.setText(mDatas.get(position));
        if (onitermClickLister!=null){
            if (!holder.mtextView.hasOnClickListeners()){
                holder.mtextView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int pos = holder.getLayoutPosition();
                        onitermClickLister.OnItemClick(view,pos);
                    }
                });

                holder.mtextView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View view) {
                        int pos = holder.getLayoutPosition();
                        onitermClickLister.OnItemLongClick(view,pos);
                        return true;
                    }
                });
            }
        }
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public void setOnitermClickLister(OnitermClickLister onitermClickLister){
        this.onitermClickLister = onitermClickLister;
    }

    interface OnitermClickLister{
        public void OnItemClick(View view,int position);
        public void OnItemLongClick(View view,int position);
    }

    class ItemViewHolder extends RecyclerView.ViewHolder{

        private TextView mtextView;
        public ItemViewHolder(View itemView) {
            super(itemView);
            mtextView = (TextView)itemView.findViewById(R.id.textview);
        }
    }
}
