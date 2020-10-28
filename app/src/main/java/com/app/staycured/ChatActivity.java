package com.app.staycured;

import android.os.Bundle;
import android.os.Handler;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.app.staycured.geoutils.SessionData;
import com.app.staycured.util.ChatAdapter;
import com.app.staycured.util.ChatMessage;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;


public class ChatActivity extends AppCompatActivity {

    private EditText messageET;
    private ListView messagesContainer;
    private ImageView sendBtn;
    private ChatAdapter adapter;
    private ArrayList<ChatMessage> chatHistory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        initControls();
//        displayData();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_chat, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void initControls() {
        messagesContainer = (ListView) findViewById(R.id.messagesContainer);
        messageET = (EditText) findViewById(R.id.messageEdit);
        sendBtn = (ImageView) findViewById(R.id.chatSendButton);

        TextView meLabel = (TextView) findViewById(R.id.meLbl);
//        TextView companionLabel = (TextView) findViewById(R.id.friendLabel);
        RelativeLayout container = (RelativeLayout) findViewById(R.id.container);
//        companionLabel.setText("My Buddy");
        meLabel.setText( SessionData.getInstance().getDrName());

        loadDummyHistory();


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
//

                ChatMessage chatMessage = new ChatMessage();
                chatMessage.setMessage("Tell your problem ");
                displayMessage(chatMessage);



            }
        }, 5000);


        String messageText1 = SessionData.getInstance().getQuestion();



        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setId(125);//dummy
        chatMessage.setMessage(messageText1);
        chatMessage.setMe(true);
        displayMessage(chatMessage);

        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {


                String messageText = messageET.getText().toString();


                if (TextUtils.isEmpty(messageText)) {
                    return;
                }


                ChatMessage chatMessage = new ChatMessage();
                chatMessage.setId(122);//dummy
                chatMessage.setMessage(messageText);
//                chatMessage.setDate(DateFormat.getDateTimeInstance().format(new Date()));
                chatMessage.setMe(true);

                messageET.setText("");

                displayMessage(chatMessage);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
//

                        ChatMessage chatMessage = new ChatMessage();
                        chatMessage.setMessage("OK ,it can be done you have to come to the clinic directly");
                        displayMessage(chatMessage);



                    }
                }, 5000);



            }
        });


    }




    public void displayMessage(ChatMessage message) {
        adapter.add(message);
        adapter.notifyDataSetChanged();
        scroll();
    }

    private void scroll() {
        messagesContainer.setSelection(messagesContainer.getCount() - 1);
    }

    private void loadDummyHistory(){

        chatHistory = new ArrayList<ChatMessage>();

        ChatMessage msg = new ChatMessage();
        msg.setId(1);
//        msg.setMe(false);

//        msg.setMessage(SessionData.getInstance().getQuestion());
//        msg.setDate(DateFormat.getDateTimeInstance().format(new Date()));
//        chatHistory.add(msg);
//        ChatMessage msg1 = new ChatMessage();
//        msg1.setId(2);
//        msg1.setMe(false);
//        msg1.setMessage("Is it Possible to have direct meeting with you Doctor ?");
//        msg1.setDate(DateFormat.getDateTimeInstance().format(new Date()));
//        chatHistory.add(msg1);

        adapter = new ChatAdapter(ChatActivity.this, new ArrayList<ChatMessage>());
        messagesContainer.setAdapter(adapter);

        for(int i=0; i<chatHistory.size(); i++) {
            ChatMessage message = chatHistory.get(i);
            displayMessage(message);
        }

    }

}
