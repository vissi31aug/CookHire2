package com.example.cookhire.Email;

import android.content.Context;
import android.os.AsyncTask;
import android.se.omapi.Session;

public class JavaMailApi extends AsyncTask<Void,Void,Void> {

    private Context context;
    private Session session;
    private String email,subject,message;

    public JavaMailApi(Context context, String email, String subject, String message) {
        this.context = context;
        this.email = email;
        this.subject = subject;
        this.message = message;
    }

    @Override
    protected Void doInBackground(Void... voids) {

       /* Properties properties=new Properties();
        properties.put("mail.smtp.host","smtp.gmail.com");
        properties.put("mail.smtp.socketFactory.port","465");
        properties.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
        properties.put("mail.smtp.auth","true");
        properties.put("mail.smtp.port","465");*/

       /* session=Session.getDefaultInstance(properties,new javax.mail.Authenticator()){
            prottected PasswordAuthentication getpasswordAuthentication(){
                return  new PasswordAuthentication(Util.EMAIL,Util.PASSWORD);
            }

        }*/






       /* session = Session.getInstance(properties,
                new javax.mail.Authenticator() {
                    protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(Util.EMAIL, Util.PASSWORD);
                    }
                }); */


        //MimeMessage  mimeMessage=new MimeMessage(session);



        return null;
    }
}
