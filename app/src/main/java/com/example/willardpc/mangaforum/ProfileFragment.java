package com.example.willardpc.mangaforum;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.PopupMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ProfileFragment extends Fragment{


    private DatabaseReference mDatabase;

    private FirebaseAuth firebaseAuth;


    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_profile, container, false);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        firebaseAuth = FirebaseAuth.getInstance();
        if (firebaseAuth.getCurrentUser() == null) {
            getActivity().finish();
            startActivity(new Intent(getActivity(), LoginActivity.class));

        }

        Menu logout_button = (Menu) view.findViewById(R.id.logout_button);
        ImageView menu_button = (ImageView) view.findViewById(R.id.menu_button);
        ImageView header_cover_image = (ImageView) view.findViewById(R.id.header_cover_image);
        EditText about_user = (EditText) view.findViewById(R.id.about_user);
        EditText user_animelist = (EditText) view.findViewById(R.id.user_animelist);
        EditText user_profile_name = (EditText) view.findViewById(R.id.user_profile_name);
        EditText user_profile_short_bio = (EditText) view.findViewById(R.id.user_profile_short_bio);


        menu_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {

                PopupMenu popupMenu = new PopupMenu(getActivity(), v);


                popupMenu.inflate(R.menu.popup_menu);

                popupMenu.show();

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener(){

                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                       switch (item.getItemId()){
                           case R.id.logout_button:
                               firebaseAuth.signOut();
                               getActivity().finish();
                               Intent intent = new Intent(getActivity(), LoginActivity.class);
                               getActivity().startActivity(intent);
                               break;
                           
                       }
                        return true;
                    }
                });

            }
        });

        return view;

    }

}
