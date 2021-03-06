package com.example.willardpc.mangaforum;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class EReviews extends AppCompatActivity {

        private static final String TAG = "EReviews";

        static final int RC_PHOTO_PICKER = 1;

        private Button sendBtn;
        private EditText messageTxt;
        private RecyclerView messagesList;
        private ChatMessageAdapter adapter;
        private ImageButton imageBtn;



        private FirebaseApp app;
        private FirebaseDatabase database;
        private FirebaseAuth auth;
        private FirebaseStorage storage;

        private DatabaseReference databaseRef;
        private StorageReference storageRef;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_ereviews);

            sendBtn = (Button) findViewById(R.id.sendBtn);
            messageTxt = (EditText) findViewById(R.id.messageTxt);
            messagesList = (RecyclerView) findViewById(R.id.messagesList);
            imageBtn = (ImageButton) findViewById(R.id.imageBtn);

            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            messagesList.setHasFixedSize(false);
            messagesList.setLayoutManager(layoutManager);

            // Show an image picker when the user wants to upload an imasge
            imageBtn.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                    intent.setType("image/jpeg");
                    intent.putExtra(Intent.EXTRA_LOCAL_ONLY, true);
                    startActivityForResult(Intent.createChooser(intent, "Complete action using"), RC_PHOTO_PICKER);
                }
            });
            // Show a popup when the user asks to sign in
            //loginBtn.setOnClickListener(new View.OnClickListener() {
               //public void onClick(View v) {
                    //LoginDialog.showLoginPrompt(EReviews.this, app);
                //}
            //});
            // Allow the user to sign out
            //logoutBtn.setOnClickListener(new View.OnClickListener() {
                //public void onClick(View v) {
                    //auth.signOut();
                //}
            //});

            adapter = new ChatMessageAdapter(this);
            messagesList.setAdapter(adapter);
            adapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
                public void onItemRangeInserted(int positionStart, int itemCount) {
                    messagesList.smoothScrollToPosition(adapter.getItemCount());
                }
            });

            // Get the Firebase app and all primitives we'll use
            app = FirebaseApp.getInstance();
            database = FirebaseDatabase.getInstance(app);
            auth = FirebaseAuth.getInstance(app);
            storage = FirebaseStorage.getInstance(app);

            // Get a reference to our chat "room" in the database
            databaseRef = database.getReference("chat");

            sendBtn.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    ChatMessage chat = new ChatMessage("Willard", messageTxt.getText().toString());
                    // Push the chat message to the database
                    databaseRef.push().setValue(chat);
                    messageTxt.setText("");
                }
            });
            // Listen for when child nodes get added to the collection
            databaseRef.addChildEventListener(new ChildEventListener() {
                public void onChildAdded(DataSnapshot snapshot, String s) {
                    // Get the chat message from the snapshot and add it to the UI
                    ChatMessage chat = snapshot.getValue(ChatMessage.class);
                    adapter.addMessage(chat);
                }

                public void onChildChanged(DataSnapshot dataSnapshot, String s) { }
                public void onChildRemoved(DataSnapshot dataSnapshot) { }
                public void onChildMoved(DataSnapshot dataSnapshot, String s) { }
                public void onCancelled(DatabaseError databaseError) { }
            });

            // When the user has entered credentials in the login dialog
            //LoginDialog.onCredentials(new OnSuccessListener<LoginDialog.EmailPasswordResult>() {
                //public void onSuccess(LoginDialog.EmailPasswordResult result) {
                    // Sign the user in with the email address and password they entered
                    //auth.signInWithEmailAndPassword(result.email, result.password);
                //}
            //});


        }

        public void onActivityResult(int requestCode, int resultCode, Intent data) {
            if (requestCode == RC_PHOTO_PICKER && resultCode == RESULT_OK) {
                Uri selectedImageUri = data.getData();

                // Get a reference to the location where we'll store our photos
                storageRef = storage.getReference("chat_photos");
                // Get a reference to store file at chat_photos/<FILENAME>
                final StorageReference photoRef = storageRef.child(selectedImageUri.getLastPathSegment());

                // Upload file to Firebase Storage
                photoRef.putFile(selectedImageUri)
                        .addOnSuccessListener(this, new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                // When the image has successfully uploaded, we get its download URL
                                Uri downloadUrl = taskSnapshot.getDownloadUrl();
                                // Set the download URL to the message box, so that the user can send it to the database
                                messageTxt.setText(downloadUrl.toString());
                            }
                        });
            }
        }
    }
