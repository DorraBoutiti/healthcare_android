package com.example.healthcareapp.ui.ItemFragment;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.healthcareapp.DataClass;
import com.example.healthcareapp.DetailActivity;
import com.example.healthcareapp.ui.ItemFragment.MyItemRecyclerViewAdapter;
import com.example.healthcareapp.R;
import com.example.healthcareapp.placeholder.PlaceholderContent.PlaceholderItem;
import com.example.healthcareapp.databinding.FragmentItemBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link PlaceholderItem}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyItemRecyclerViewAdapter extends RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder> {

    private Context context;
    private List<DataClass> dataList;

    public MyItemRecyclerViewAdapter(Context context, List<DataClass> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        Glide.with(context).load(dataList.get(position).getDataImage()).placeholder(R.drawable.medicine).into(holder.recImage);
        holder.recTitle.setText(dataList.get(position).getDataTitle());
        holder.recDesc.setText(dataList.get(position).getDataDesc());
        holder.recLang.setText(dataList.get(position).getDataLang());
        holder.recCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("Image", dataList.get(holder.getBindingAdapterPosition()).getDataImage());
                intent.putExtra("Description", dataList.get(holder.getBindingAdapterPosition()).getDataDesc());
                intent.putExtra("Title", dataList.get(holder.getBindingAdapterPosition()).getDataTitle());
                intent.putExtra("Key",dataList.get(holder.getBindingAdapterPosition()).getKey());
                intent.putExtra("Language", dataList.get(holder.getBindingAdapterPosition()).getDataLang());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
    public void searchDataList(ArrayList<DataClass> searchList){
        dataList = searchList;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView recImage;
        TextView recTitle, recDesc, recLang;
        CardView recCard;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            recImage = itemView.findViewById(R.id.recImage);
            recCard = itemView.findViewById(R.id.recCard);
            recDesc = itemView.findViewById(R.id.recDesc);
            recLang = itemView.findViewById(R.id.recPriority);
            recTitle = itemView.findViewById(R.id.recTitle);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + recTitle.getText() + "'";
        }
    }
}