package com.example.willardpc.mangaforum;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;

import me.anwarshahriar.calligrapher.Calligrapher;

public class CReviews extends AppCompatActivity {

    //DEFINE THE VIEWS
    private ImageButton imageButtonSend;
    private EditText editTextInput;
    private TextView textViewMessage;

    private String user_name,forum_name;
    private DatabaseReference root;
    private String temp_key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creviews);
        Calligrapher calligrapher = new Calligrapher(this);
        calligrapher.setFont(this, "fonts/VarelaRound-Regular.ttf", true);

        //INSTANTIATE THE VIEWS
        //imageButtonSend = (ImageButton)findViewById(R.id.imageButtonSend);
        //editTextInput = (EditText)findViewById(R.id.editTextInput);
        //textViewMessage = (TextView)findViewById(R.id.textViewMessage);

        //user_name = getIntent().getExtras().get("user_name").toString();
        //forum_name = getIntent().getExtras().get("forum_name").toString();

        //SET THE TITLE ON THE ACTION BAR
        setTitle("Forum" +forum_name);

        //GET REFERENCE OF THE ACTIVE FORUM
        //root = FirebaseDatabase.getInstance().getReference().child(forum_name);

                //SET ONCLICK LISTENERS TO SEND BUTTON
        //imageButtonSend.setOnClickListener(new View.OnClickListener() {
            //@Override
            //public void onClick(View v) {


                //Map<String,Object> map = new HashMap<String, Object>();
                //temp_key = root.push().getKey();
                //root.updateChildren(map);

                //DatabaseReference message_root = root.child(temp_key);
                //Map<String,Object> map2 = new HashMap<String, Object>();
                //map2.put("name", user_name);
                //map2.put("msg", editTextInput.getText().toString());

                //message_root.updateChildren(map2);

            //}
        //});
        }
    }