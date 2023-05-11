package com.example.socialapp.following;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.socialapp.ImageDetail;
import com.example.socialapp.ProfileOtherUser;
import com.example.socialapp.R;
import com.example.socialapp.model.User;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class rcvUserAdapter extends RecyclerView.Adapter<rcvUserAdapter.UserViewHolder>{

    private List<User> listUsers;
    private Context context;

    public rcvUserAdapter (Context context, List<User> listUsers){
        this.listUsers = listUsers;
        this.context = context;
    }

    public void updateList(List<User> filterList) {
        this.listUsers = filterList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        User user = listUsers.get(position);
        if(user == null){
            return;
        }

        holder.avatar.setImageResource(user.getAvatar());
        holder.tvName.setText(user.getName());
        holder.tvId.setText(user.getId());

        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showProfile(user);
            }
        });

    }

    private void showProfile(User user) {
        Intent intent = new Intent(context, ProfileOtherUser.class);
        context.startActivity(intent);
    }

    @Override
    public int getItemCount() {

        if(listUsers != null){
            return listUsers.size();
        }
        return 0;
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {

        private CircleImageView avatar;
        private TextView tvName;
        private TextView tvId;
        private RelativeLayout relativeLayout;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);

            avatar = itemView.findViewById(R.id.avatar);
            tvName = itemView.findViewById(R.id.tv_name);
            tvId = itemView.findViewById(R.id.tv_id);
            relativeLayout = itemView.findViewById(R.id.layoutuser);

        }
    }
}
