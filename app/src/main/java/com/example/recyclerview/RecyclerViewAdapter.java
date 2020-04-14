package com.example.recyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class RecyclerViewAdapter extends ListAdapter<Data,RecyclerViewAdapter.MyViewHolder> {




    private  OnContactClicked instance;
  private static   DiffUtil.ItemCallback<Data> diffCallback =new DiffUtil.ItemCallback<Data>() {
        @Override
        public boolean areItemsTheSame(@NonNull Data oldItem, @NonNull Data newItem) {
            return  oldItem.getNumber().equals(newItem.getNumber());
        }

        @Override
        public boolean areContentsTheSame(@NonNull Data oldItem, @NonNull Data newItem) {
            return oldItem.getName().equals(newItem.getName()) && oldItem.getNumber().equals(newItem.getNumber());
        }
    };
//    *********** default constructor********
    protected RecyclerViewAdapter(OnContactClicked onContactClicked) {
        super(diffCallback);
        this.instance= onContactClicked ;
    }


    //Interface
    interface OnContactClicked {
        void Clicked(Data data, int position);
        void delete (Data data);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.onBind(getItem(position));
//        تم اضافه هذا السطر **********
        final Data data =getItem(position);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                instance.Clicked(data, position);
            }
        });
        holder.deleteImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                instance.delete(data);
            }
        });
    }




    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView number;
        ImageView deleteImage ;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tv_name);
            number = itemView.findViewById(R.id.tv_job);
            deleteImage = itemView.findViewById(R.id.image_delete);
        }

        void onBind(Data data) {

            name.setText(data.getName());
            number.setText(data.getNumber());
        }
    }
}

