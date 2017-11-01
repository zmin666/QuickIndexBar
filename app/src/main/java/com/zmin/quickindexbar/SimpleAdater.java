package com.zmin.quickindexbar;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class SimpleAdater extends RecyclerView.Adapter<SimpleAdater.MyViewHolder> {
    private Context context;
    private List<Model> lists;

    public SimpleAdater(Context context, List<Model> lists) {
        this.context = context;
        this.lists = lists;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // View view = View.inflate(context, R.layout.simple_list_item,null);
        View view = LayoutInflater.from(context).inflate(R.layout.simple_list_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Model model = lists.get(position);
        if (position == 0) {
            holder.tv_head.setVisibility(View.VISIBLE);
            holder.tv_head.setText(model.sticky);
            holder.view_line.setVisibility(View.INVISIBLE);
        } else {
            Model lastModel = lists.get(position - 1);
            if (model.sticky != lastModel.sticky) {
                holder.tv_head.setVisibility(View.VISIBLE);
                holder.tv_head.setText(model.sticky);
                holder.view_line.setVisibility(View.INVISIBLE);
            } else {
                holder.tv_head.setVisibility(View.GONE);
                holder.view_line.setVisibility(View.VISIBLE);
            }
        }
        holder.tv_body.setText(model.name);
    }

    @Override
    public int getItemCount() {
        return lists.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_head;
        TextView tv_body;
        View view_line;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.tv_head = (TextView) itemView.findViewById(R.id.tv_head);
            this.tv_body = (TextView) itemView.findViewById(R.id.tv_body);
            this.view_line = itemView.findViewById(R.id.view_line);
        }

    }
}
