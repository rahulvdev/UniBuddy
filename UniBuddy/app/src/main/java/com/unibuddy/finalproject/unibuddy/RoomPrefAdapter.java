package com.unibuddy.finalproject.unibuddy;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONException;

import java.util.List;
import java.util.Map;

/**
 * Created by RAHUL VIJAYDEV on 25-Apr-17.
 */

public class RoomPrefAdapter extends RecyclerView.Adapter<RoomPrefAdapter.ViewHolder> {

    private Context context;
    ClickItems itemClickListener;

    public RoomPrefAdapter(){

    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView userImg;
        public TextView userName;
        public TextView userEmail;

        public ViewHolder(View v) {
            super(v);
            userImg = (ImageView) v.findViewById(R.id.rMateMatchImg);
            userName = (TextView) v.findViewById(R.id.rMateMatchName);
            userEmail = (TextView) v.findViewById(R.id.rMateMatchEmail);

            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (itemClickListener != null) {
                        itemClickListener.ShortClick(view, getAdapterPosition());
                    }
                }
            });
        }
        public void buildMatchData(StudentListDataJson.StudentItem student){
            try {
                userName.setText( student.details.getString("Name"));
                userEmail.setText(student.details.getString("userName"));
                userImg.setImageBitmap(student.image);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View  v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardlayout,parent,false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }



    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        StudentListDataJson.StudentItem student = StudentListDataJson.ITEMS.get(position);
        holder.buildMatchData(student);
    }

    @Override
    public int getItemViewType(int position) {
        // Just as an example, return 0 or 2 depending on position
        // Note that unlike in ListView adapters, types don't have to be contiguous
        return position;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public int getItemCount() {
        return StudentListDataJson.ITEMS.size();
    }

    public void SetOnItemClick(final ClickItems clickItems) {
        this.itemClickListener = clickItems;
    }

    public interface ClickItems {
        void ShortClick(View v, int position);
    }


}
