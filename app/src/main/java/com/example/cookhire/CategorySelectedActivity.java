package com.example.cookhire;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cookhire.Adapter.UserAdapter;
import com.example.cookhire.Adapter.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class CategorySelectedActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private RecyclerView recyclerView;

    private List<User> userList;
    private UserAdapter userAdapter;

    private String title="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_selected);

        toolbar=findViewById(R.id.toolbar);
        toolbar.setTitle(getIntent().getStringExtra("group"));
        //setSupportActiokaasnBar(toolbar);
//
//        getSupportActionBar().setDisplayShowHomeEnabled(true);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView=findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setStackFromEnd(true);
        linearLayoutManager.setReverseLayout(true);

        recyclerView.setLayoutManager(linearLayoutManager);


        userList=new ArrayList<>();
        userAdapter=new UserAdapter(CategorySelectedActivity.this, (ArrayList<User>) userList);
        recyclerView.setAdapter(userAdapter);

        if (getIntent().getExtras()!=null){


           // getSupportActionBar().setTitle("Cook Type"+title);




            title=getIntent().getStringExtra("group");
          //  getSupportActionBar().setTitle("CookType"+title);

            readUsers();

            /*i f (title.equals("Compatible with me")){

                //getCompatibleUser();
               // getSupportActionBar().setTitle("Compatible with me");

            }
            else {
                readUsers();
            }
*/

        }


    }

   /* private void getCompatibleUser() {

        DatabaseReference ref= FirebaseDatabase.getInstance().getReference().child("user")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid());

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String result;
                String type=snapshot.child("type").getValue().toString();

                if (type.equals("cook")){
                    result="hirer";
                }
                else {
                    result="cook";
                }

                String cookType=snapshot.child("cookType").getValue().toString();

                DatabaseReference reference=FirebaseDatabase.getInstance().getReference()
                        .child("user");

                Query query=reference.orderByChild("search").equalTo(result+cookType);

                query.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        userList.clear();
                        for (DataSnapshot dataSnapshot:snapshot.getChildren()){

                            User user=dataSnapshot.getValue(User.class);
                            userList.add(user);

                        }

                        userAdapter.notifyDataSetChanged();

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
*/
    private void readUsers() {

        DatabaseReference ref= FirebaseDatabase.getInstance().getReference().child("user")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid());

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String result;
                String type=snapshot.child("type").getValue().toString();

                if(type.equals("cook")){
                    result="cook";
                }
                else {
                    result="cook";
                }

               // String str=snapshot.child("cookType").getValue().toString();

                DatabaseReference reference=FirebaseDatabase.getInstance().getReference()
                        .child("user");

                Query query=reference.orderByChild("search").equalTo(result+title);

                query.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        userList.clear();
                        for (DataSnapshot dataSnapshot:snapshot.getChildren()){

                            User user=dataSnapshot.getValue(User.class);
                            userList.add(user);

                        }

                        userAdapter.notifyDataSetChanged();

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.home:
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }



    }

}