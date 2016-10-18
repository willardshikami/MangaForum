package com.example.willardpc.mangaforum;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by willardpc on 10/18/16.
 */
public class ChatMessageAdapter extends RecyclerView.Adapter<ChatMessageViewHolder> {
    private static final String TAG = "ChatMessageAdapter";
    private final Activity activity;
    List<ChatMessage> messages = new ArrayList<>();

    public ChatMessageAdapter(Activity activity) {
        this.activity = activity;
    }

    public void addMessage(ChatMessage chat) {
        messages.add(chat);
        notifyItemInserted(messages.size());
    }


    @Override
    public ChatMessageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ChatMessageViewHolder(activity, activity.getLayoutInflater().inflate(android.R.layout.two_line_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ChatMessageViewHolder holder, int position) {
        holder.bind(messages.get(position));
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }
}