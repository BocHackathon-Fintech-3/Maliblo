package com.maliblo.fincam;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.maliblo.fincam.ViewModelsss.ExtractDataView;

import java.util.ArrayList;


public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ModelViewHolder> {
    private ArrayList<ExtractDataView> mModel = new ArrayList<>();

    @NonNull
    @Override
    public ModelViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.dataout_item, parent, false);
        ModelViewHolder evh = new ModelViewHolder(v, mListener);
        return evh;
    }

    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public static class ModelViewHolder extends RecyclerView.ViewHolder {
        public ImageView mCompanyLogo;
        public TextView mTotal;
        public TextView mCompanyName;

        public ModelViewHolder(View itemView, final OnItemClickListener listener) {
            super(itemView);
            mCompanyLogo = itemView.findViewById(R.id.company_logo);
            mCompanyName = itemView.findViewById(R.id.account_name);
            mTotal = itemView.findViewById(R.id.amaount);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }


    @Override
    public void onBindViewHolder(ModelViewHolder holder, int position) {
        ExtractDataView currentItem = mModel.get(position);

        holder.mCompanyLogo.setImageResource(currentItem.getLogo());
        holder.mTotal.setText(currentItem.getTotal());
        holder.mCompanyName.setText(currentItem.getCompanyName());

    }


    @Override
    public int getItemCount() {
        return mModel.size();
    }

    public void setExtractData(ArrayList<ExtractDataView> mModel) {
        this.mModel = mModel;
        notifyDataSetChanged();
    }
}