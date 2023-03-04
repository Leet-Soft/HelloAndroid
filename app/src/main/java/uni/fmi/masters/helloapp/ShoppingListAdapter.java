package uni.fmi.masters.helloapp;


import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import uni.fmi.masters.helloapp.entity.ShoppingItem;

public class ShoppingListAdapter
        extends RecyclerView.Adapter<ShoppingListAdapter.MyViewHolder>{

    static List<ShoppingItem> itemList;

    ShoppingListAdapter(List<ShoppingItem> itemList){
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                           int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.shopping_list_row, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder,
                                 int position) {
        ShoppingItem item = itemList.get(position);

        holder.titleTV.setText(item.getName());
        holder.quantityTV.setText(item.getQuantity()
                + item.getMeasure());
        holder.boughtCB.setChecked(item.isBought());

        holder.boughtCB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b)
                    holder.rootLayout.setBackgroundColor(Color.GREEN);
                else
                    holder.rootLayout.setBackgroundColor(Color.WHITE);
            }
        });

       // holder.deleteTV.setOnClickListener(onClick);
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView titleTV;
        TextView quantityTV;
        CheckBox boughtCB;
        TextView deleteTV;
        LinearLayout rootLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTV = itemView.findViewById(R.id.rowNameTV);
            quantityTV = itemView.findViewById(R.id.rowQuantityTV);
            boughtCB = itemView.findViewById(R.id.boughtItemCB);
            deleteTV = itemView.findViewById(R.id.deleteTV);
            rootLayout = itemView.findViewById(R.id.rowRootLL);
        }
    }
}
