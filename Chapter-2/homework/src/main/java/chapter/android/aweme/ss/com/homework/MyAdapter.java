package chapter.android.aweme.ss.com.homework;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import chapter.android.aweme.ss.com.homework.model.Message;
import chapter.android.aweme.ss.com.homework.widget.CircleImageView;


public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private List<Message> list;

    private final ListItemClickListener onClickListener;

    public MyAdapter(List<Message> list, ListItemClickListener listener){
        this.list = list;
        onClickListener = listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType){
        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.im_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(layoutIdForListItem, viewGroup, false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int position) {
        myViewHolder.bind(position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView title,description,time;
        public CircleImageView image;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tv_title);
            description = itemView.findViewById(R.id.tv_description);
            time = itemView.findViewById(R.id.tv_time);
            image = itemView.findViewById(R.id.iv_avatar);
            itemView.setOnClickListener(this);
        }

        public void bind(int position){
            Message message = list.get(position);
            title.setText(message.getTitle());
            time.setText(message.getTime());
            description.setText(message.getDescription());
            int picId = 0;
            switch (message.getIcon()){
                case "TYPE_ROBOT" : picId = R.drawable.session_robot;break;
                case "TYPE_GAME" : picId = R.drawable.icon_micro_game_comment;break;
                case "TYPE_SYSTEM" : picId = R.drawable.session_system_notice;break;
                case "TYPE_STRANGER" : picId = R.drawable.session_stranger;break;
                case "TYPE_USER" : picId = R.drawable.icon_girl;break;
                default: break;
            }
            if(picId!=0){
                image.setImageResource(picId);
            }
        }

        @Override
        public void onClick(View v) {
            int clickedPosition = getAdapterPosition();
            if (onClickListener != null) {
                onClickListener.onListItemClick(clickedPosition);
            }
        }
    }
    public interface ListItemClickListener {
        void onListItemClick(int clickedItemIndex);
    }
}
