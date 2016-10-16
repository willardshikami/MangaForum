package com.example.willardpc.mangaforum;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    ArrayList<TopicModel> listitems = new ArrayList<>();
    RecyclerView MyRecyclerView;
    String Topics[] = {"Episode Reviews","Anime Recommendations","Anime Thoughts","Fall 2016 Animes","Summer 2016 Animes","Fan Art","Community Reviews"};
    int  Images[] = {R.drawable.review,R.drawable.reccomendations,R.drawable.thoughts,R.drawable.fall2016,R.drawable.summer2016,R.drawable.fanwork,R.drawable.reviews};

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeList();
        getActivity().setTitle("Manga Forum");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        MyRecyclerView = (RecyclerView) view.findViewById(R.id.cardView);
        MyRecyclerView.setHasFixedSize(true);
        LinearLayoutManager MyLayoutManager = new LinearLayoutManager(getActivity());
        MyLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        if (listitems.size() > 0 & MyRecyclerView != null) {
            MyRecyclerView.setAdapter(new MyAdapter(listitems));
        }
        MyRecyclerView.setLayoutManager(MyLayoutManager);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {
        private ArrayList<TopicModel> list;

        public MyAdapter(ArrayList<TopicModel> Data) {
            list = Data;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent,int viewType) {
            // create new view
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_list, parent, false);
            MyViewHolder holder = new MyViewHolder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, final int position) {
            holder.titleTextView.setText(list.get(position).getCardName());
            holder.coverImageView.setImageResource(list.get(position).getImageResourceId());
            holder.coverImageView.setTag(list.get(position).getImageResourceId());
            holder.cv.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    switch (position){
                        case 0:
                            Intent intent0 = new Intent(v.getContext(), EReviews.class);
                            v.getContext().startActivity(intent0);
                            break;
                        case 1:
                            Intent intent1 = new Intent(v.getContext(),Recommendations.class);
                            v.getContext().startActivity(intent1);
                            break;
                        case 2:
                            Intent intent2 = new Intent(v.getContext(),Thoughts.class);
                            v.getContext().startActivity(intent2);
                            break;
                        case 3:
                            Intent intent3 = new Intent(v.getContext(),Fall.class);
                            v.getContext().startActivity(intent3);
                            break;
                        case 4:
                            Intent intent4 = new Intent(v.getContext(),Summer.class);
                            v.getContext().startActivity(intent4);
                            break;
                        case 5:
                            Intent intent5 = new Intent(v.getContext(),FanArt.class);
                            v.getContext().startActivity(intent5);
                            break;
                        case 6:
                            Intent intent6 = new Intent(v.getContext(),CReviews.class);
                            v.getContext().startActivity(intent6);
                            break;
                    }
                }

            });
        }

        @Override
        public int getItemCount() {
            return list.size();
        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        public TextView titleTextView;
        public ImageView coverImageView;
        public CardView cv;


        public MyViewHolder(View view) {
            super(view);
            titleTextView = (TextView) view.findViewById(R.id.titleTextView);
            coverImageView = (ImageView) view.findViewById(R.id.coverImageView);
            cv = (CardView) view.findViewById(R.id.card_view);
            }
    }

    public void initializeList() {
        listitems.clear();

        for(int i =0;i<7;i++){


            TopicModel item = new TopicModel();
            item.setCardName(Topics[i]);
            item.setImageResourceId(Images[i]);
            item.setIsturned(0);
            listitems.add(item);
        }

    }
}
