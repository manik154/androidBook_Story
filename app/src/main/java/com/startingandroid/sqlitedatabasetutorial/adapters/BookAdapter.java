package com.startingandroid.sqlitedatabasetutorial.adapters;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;
import com.startingandroid.sqlitedatabasetutorial.R;
import com.startingandroid.sqlitedatabasetutorial.Model.Book;

import java.util.ArrayList;


public class BookAdapter extends RecyclerView.Adapter<BookAdapter.UserViewHolder> {
    private ArrayList<Book> mDataSet;
    Context context;

    public BookAdapter(Context context, ArrayList<Book> mDataSet) {
        this.context = context;
        this.mDataSet = mDataSet;

    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_row, parent, false);
        UserViewHolder userViewHolder = new UserViewHolder(v);
        return userViewHolder;
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {
        holder.name_entry.setText(mDataSet.get(position).getName());
        // holder.icon_entry.setText(mDataSet.get(position).getNumber());
        if (mDataSet.get(position).getIcon() != null) {

            int drawableResourceId = context.getResources().getIdentifier(mDataSet.get(position).getIcon(),
                    "drawable", context.getPackageName());
            Glide.with(context).load(drawableResourceId).into(holder.icon_entry);
           /* Picasso.with(context).load(drawableResourceId).centerCrop()
                    .resize(holder.icon_entry.getWidth(), 500)
                    .into(holder.icon_entry);*/
 /*           Resources resources = context.getResources();
            final int resourceId = resources.getIdentifier(mDataSet.get(position).getIcon(), "drawable",
                    context.getPackageName());
            holder.icon_entry.setImageResource(resourceId);
 */
        }


    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

    public static class UserViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView name_entry, content;
        ImageView icon_entry;

        UserViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView.findViewById(R.id.user_layout);
            name_entry = (TextView) itemView.findViewById(R.id.name_entry);
            icon_entry = (ImageView) itemView.findViewById(R.id.image2);
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}

