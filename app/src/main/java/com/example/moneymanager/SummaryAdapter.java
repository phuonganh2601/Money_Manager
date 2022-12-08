package com.example.moneymanager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.moneymanager.repositories.*;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Vector;

public class SummaryAdapter extends RecyclerView.Adapter<SummaryAdapter.DataViewHolder> {
    private Vector<Input> list;
    private Context context;

    public SummaryAdapter(Vector<Input> list, Context context) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public DataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView;

        switch (viewType) {
            case 2:
                itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.summary_rv_income_item_name, parent, false);
                break;
            default:
                itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.summary_rv_expense_item_name, parent, false);
                break;
        }
        return new DataViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull SummaryAdapter.DataViewHolder holder, int position) {
        Input temp = list.get(position);
        NumberFormat formatter = new DecimalFormat("#,###");
        String show = String.format("%s\n %s %s\n %s\n %d/%d/%d", temp.getCategory(), formatter.format(temp.getAmount()), temp.getCurrency(), temp.getNote(), temp.getDate(), temp.getMonth(), temp.getYear());
        holder.tvName.setText(show);
    }

    @Override
    public int getItemViewType(int position) {
        return (int) list.get(position).getType();
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public static class DataViewHolder extends RecyclerView.ViewHolder {
        private TextView tvName;

        public DataViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_summary_item_name);
        }
    }
}
