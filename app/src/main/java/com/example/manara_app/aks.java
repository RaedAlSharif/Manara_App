package com.example.manara_app;


import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Properties;


import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class aks extends AppCompatActivity {
    RadioButton radioButton;
    String radioResult = "";
    String spinnerResult = "";
    RadioGroup radioGroup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aks);

        Spinner spinner = (Spinner) findViewById(R.id.spinner);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.التأمينات, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        EditText et1 = findViewById(R.id.editTextNumber);
        EditText et2 = findViewById(R.id.editTextNumber3);
        EditText et3 = findViewById(R.id.editTextTextPersonName2);

        radioGroup = findViewById(R.id.radioGroup);


        Button btn = findViewById(R.id.button2);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int rad = radioGroup.getCheckedRadioButtonId();
                String s = "219SE2179@isik.edu.tr";

                radioButton = findViewById(rad);
                radioResult = radioButton.getText().toString();

                spinnerResult = spinner.getSelectedItem().toString();

                Properties props = new Properties();
                props.put("mail.smtp.auth" , "true");
                props.put("mail.smtp.starttls.enable" , "true");
                props.put("mail.smtp.host" , "smtp.gmail.com");
                props.put("mail.smtp.port" , "587");

                Session session = Session.getInstance(props, new javax.mail.Authenticator(){
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication(){
                        return new PasswordAuthentication("raedalsharif142@gmail.com" , "feugmjtiuicxqqfs");
                    }


                });

                try {
                    Message message = new MimeMessage(session);
                    message.setFrom(new InternetAddress("raedalsharif142@gmail.com"));
                    message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(s));
                    message.setSubject(" طلب "  + radioResult  );
                    message.setText("رقم العقد او الملحق : " + et1.getText().toString() + "\n" +
                            "السنه : " + et2.getText().toString() + "\n" +
                            "المؤمن له : " + et3.getText().toString() + "\n" +
                            "نوع التأمين : " + spinnerResult);
                    Transport.send(message);
                }
                catch (MessagingException e) {
                    throw new RuntimeException(e);
                }


            }
        });
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }
    public void check(){
        int rad = radioGroup.getCheckedRadioButtonId();

        radioButton = findViewById(rad);
    }
}