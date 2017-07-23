package ru.innopolis.ruslan.rssreader;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by ruslan on 21.07.2017.
 */

public class PublicationAdapter extends RecyclerView.Adapter<PublicationAdapter.PublicationHolder> {

    private Context context;
    private List<Publication> items;

    public PublicationAdapter (Context context, List<Publication> items){
        this.context = context;
        this.items = items;
    }


    @Override
    public PublicationHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.publication, parent, false);
        return new PublicationHolder(v);
    }

    @Override
    public void onBindViewHolder(PublicationHolder holder, int position) {
        holder.mTitle.setText(items.get(position).getTitle());
        holder.mDescription.setText(items.get(position).getDescription());
        holder.mDate.setText(items.get(position).getPubDate());
        holder.mCreator.setText(items.get(position).getCreator());
        holder.mCategory.setText(items.get(position).getCategory());
    }

    @Override
    public int getItemCount() { return items.size(); }

    public static class PublicationHolder extends RecyclerView.ViewHolder {

        TextView mTitle;
        TextView mDescription;
        TextView mLink;
        TextView mDate;
        TextView mCreator;
        TextView mCategory;

        public PublicationHolder(View v) {
            super(v);
            mTitle = v.findViewById(R.id.title);
            mDescription = v.findViewById(R.id.description);
            mDate = v.findViewById(R.id.date);
            mCreator = v.findViewById(R.id.creator);
            mCategory = v.findViewById(R.id.category);

        }
    }




}
