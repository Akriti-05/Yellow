package com.example.aasthaapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.aasthaapp.Adapters.ChatAdapter;
import com.example.aasthaapp.Models.MessageModel;
import com.example.aasthaapp.databinding.ActivityChatDetailCpBinding;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Date;

public class chatDetailActivityCp extends AppCompatActivity {

    ActivityChatDetailCpBinding binding;
    FirebaseDatabase database;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChatDetailCpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().hide();
        database = FirebaseDatabase.getInstance();
        auth= FirebaseAuth.getInstance();

        String senderId= auth.getUid();
        String receiveId= getIntent().getStringExtra("userId");
        String userName= getIntent().getStringExtra("username");
        String profilepic= getIntent().getStringExtra("profilePic");

        binding.userName.setText(userName);
        Picasso.get().load(profilepic).placeholder(R.drawable.user).into(binding.profileImage);

        binding.backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(chatDetailActivityCp.this, MainActivityCP.class);
                startActivity(intent);
            }
        });

        final ArrayList<MessageModel> messageModels= new ArrayList<>();

        final ChatAdapter chatAdapter= new ChatAdapter(messageModels,this);
        binding.chatRecyclerView.setAdapter(chatAdapter);

        LinearLayoutManager layoutManager= new LinearLayoutManager(chatDetailActivityCp.this);
        binding.chatRecyclerView.setLayoutManager(layoutManager);

        String senderRoom= senderId+receiveId;
        String receiverRoom=receiveId+senderId;

        database.getReference().child("chats")
                .child(senderRoom)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        messageModels.clear();
                        for(DataSnapshot snapshot1:snapshot.getChildren()){
                            MessageModel model= snapshot1.getValue(MessageModel.class);

                            messageModels.add(model);
                        }
                        chatAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });




        binding.send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message= binding.etMessage.getText().toString();
                final MessageModel model= new MessageModel(senderId, message);
                model.setTimestamp(new Date().getTime());
                binding.etMessage.setText("");

                database.getReference().child("chats")
                        .child(senderRoom)
                        .push()
                        .setValue(model).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        database.getReference().child("chats").child(receiverRoom)
                                .push()
                                .setValue(model).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {

                            }
                        });

                    }
                });
            }
        });


    }

}