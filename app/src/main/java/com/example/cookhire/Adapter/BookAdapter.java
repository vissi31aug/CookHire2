package com.example.cookhire.Adapter;


/*import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.cookhire.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/*public class BookAdapter extends RecyclerView.Adapter<BookAdapter.ViewHolder> {


    private Context context;
    private List<User> userList;


    public BookAdapter(Context context, List<User> userList) {
        this.context = context;
        this.userList = userList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {

    View view= LayoutInflater.from(context).inflate(
            R.layout.user_displayed_layout,parent,false);

    return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final  User user=userList.get(position);

        holder.userType.setText(user.getType());

        if(user.getType().equals("hirer")){

         holder.bookNow.setVisibility(View.GONE);
        }

        holder.setPrice.setText(user.getSetprice());
       /* if (user.getSetprice().equals("hirer")){
            holder.setPrice.setVisibility(View.GONE);
        }
        else {
            holder.setPrice.setVisibility(View.VISIBLE);
        }*/


    /*    holder.userEmail.setText(user.getEmail());
        holder.userPhoneNumber.setText(user.getPhone());
        holder.userName.setText(user.getName());
        holder.cookType.setText(user.getCookType());

        Glide.with(context).load(user.getProfilepictureurl()).into(holder.userProfileImage);


        final String receiverName=user.getName();
        final String receiverId=user.getId();

        holder.bookNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(context)
                        .setTitle("BOOK NOW").
                        setMessage("Booked to "+user.getName()+"?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                DatabaseReference reference= FirebaseDatabase.getInstance().getReference()
                                        .child("user").child(FirebaseAuth.getInstance().getCurrentUser().getUid());

                                reference.addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                                        String senderName=snapshot.child("name").getValue().toString();
                                        String email=snapshot.child("email").getValue().toString();
                                        String phone=snapshot.child("phone").getValue().toString();


                                        String mBook=user.getEmail();
                                        String mSubject="BOOK REQUEST";
                                        String mMessage="Hello "+receiverName+","+senderName+
                                                " would like to hire you:\n"+
                                                "Name: "+senderName+"\n"+
                                                "Phone number :"+phone+ "\n"+
                                                "Email: "+email+ "\n"+
                                                "Kindly reach out to him! \n"
                                                +"COOKHIRE-Hire the Test";



                                        DatabaseReference senderRef=FirebaseDatabase.getInstance().getReference("email")
                                                .child(FirebaseAuth.getInstance().getCurrentUser().getUid());
                                        senderRef.child(receiverId).setValue(true).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {

                                                DatabaseReference receiverRef1=FirebaseDatabase.getInstance().getReference("email")
                                                        .child(receiverId);
                                                receiverRef1.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(true);

                                                DatabaseReference receiverRef2=FirebaseDatabase.getInstance().getReference("email")
                                                        .child(receiverId);
                                                receiverRef2.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(mBook);
                                                DatabaseReference receiverRef3=FirebaseDatabase.getInstance().getReference("email")
                                                        .child(receiverId);
                                                receiverRef3.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(mSubject);
                                                DatabaseReference receiverRef4=FirebaseDatabase.getInstance().getReference("email")
                                                        .child(receiverId);
                                                receiverRef4.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(mMessage);



                                            }
                                        });




                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {

                                    }
                                });
                            }
                        })
                        .setNegativeButton("No",null)
                        .show();
            }
        });


    }

    @Override
    public int getItemCount() {

        return userList.size() ;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public CircleImageView userProfileImage;
        public TextView userType,userName,userEmail,userPhoneNumber,cookType,setPrice;
        public Button bookNow;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);


            userProfileImage=itemView.findViewById(R.id.userProfileImage);
            userType=itemView.findViewById(R.id.userType);
            setPrice=itemView.findViewById(R.id.setPrice);
            userName=itemView.findViewById(R.id.userName);
            userEmail=itemView.findViewById(R.id.userEmail);
            userPhoneNumber=itemView.findViewById(R.id.userPhoneNumber);
            bookNow=itemView.findViewById(R.id.bookNow);
            cookType=itemView.findViewById(R.id.cookType);





        }



    }

    private  void addNotifications(String receiverId,String senderId){

        DatabaseReference reference= FirebaseDatabase.getInstance().getReference().child("notifications")
                .child(receiverId);
        String data= DateFormat.getDateInstance().format(new Date());

        HashMap<String,Object> hashMap=new HashMap<>();
        hashMap.put("receiverId",receiverId);
        hashMap.put("senderId",senderId);
        hashMap.put("text","Sent you an email,kindly check it out!");
        hashMap.put("date",data);

        reference.push().setValue(hashMap);

    }


    public interface RecyclerViewClickListener{

    }}

*/
