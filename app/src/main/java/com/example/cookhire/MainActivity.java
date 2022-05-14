package com.example.cookhire;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.cookhire.Adapter.UserAdapter;
import com.example.cookhire.Message.NotificationActivity;
import com.example.cookhire.Adapter.User;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private NavigationView navView;

    private CircleImageView navProfileImage;
    private TextView navFullName,navEmail,navCookType,navType;

    private RecyclerView recyclerView;
    private ProgressBar progressBar;

    private DatabaseReference useRef;



    private List<User> userList;
    private UserAdapter userAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar=findViewById(R.id.toolbar);
        toolbar.setTitle("CookHire App");
        //setSupportActionBar(toolbar);
     //   getSupportActionBar().setTitle("CookHire App");

        drawerLayout=findViewById(R.id.drawerLayout);
        navView=findViewById(R.id.navView);

        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(MainActivity.this,drawerLayout,
                toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navView.setNavigationItemSelectedListener(this);

        progressBar =findViewById(R.id.progressBar);
        recyclerView=findViewById(R.id.recyclerView);



        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(layoutManager);

        userList=new ArrayList<>();
        userAdapter=new UserAdapter(MainActivity.this, (ArrayList<User>) userList);

        recyclerView.setAdapter(userAdapter);

        DatabaseReference ref=FirebaseDatabase.getInstance().getReference()
                .child("user").child(FirebaseAuth.getInstance().getCurrentUser().getUid());

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String type=snapshot.child("type").getValue().toString();
                if (type.equals("cook")){
                    navView.getMenu().clear();
                    navView.inflateMenu(R.menu.menu2);
                    readHirer();
                }
                else{

                    readCook();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        navProfileImage =navView.getHeaderView(0).findViewById(R.id.navUserImage);
        navFullName =navView.getHeaderView(0).findViewById(R.id.navUserFullName);
        navEmail =navView.getHeaderView(0).findViewById(R.id.navUserEmail);
        //navCookType =navView.getHeaderView(0).findViewById(R.id.navCookType);
        navType =navView.getHeaderView(0).findViewById(R.id.navUserType);



        useRef= FirebaseDatabase.getInstance().getReference().child("user").child(

                FirebaseAuth.getInstance().getCurrentUser().getUid()

        );

        useRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if (snapshot.exists()){
                    String name=snapshot.child("name").getValue().toString();
                    navFullName.setText(name);



                    String email=snapshot.child("email").getValue().toString();
                    navEmail.setText(email);

                   // String cooktype=snapshot.child("cookType").getValue().toString();
                    //navCookType.setText(cooktype);

                    String type=snapshot.child("type").getValue().toString();
                    navType.setText(type);


                    if(snapshot.hasChild("profilepictureurl")){

                        String imgUrl=snapshot.child("profilepictureurl").getValue().toString();
                        Glide.with(getApplicationContext()).load(imgUrl).into(navProfileImage);

                    }
                    else {
                        navProfileImage.setImageResource(R.drawable.profile_image);

                    }

                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

    private void readCook() {

        DatabaseReference reference=FirebaseDatabase.getInstance().getReference().child("user");
        Query query=reference.orderByChild("type").equalTo("cook");

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                userList.clear();

                for (DataSnapshot dataSnapshot:snapshot.getChildren()){
                    User user=dataSnapshot.getValue(User.class);
                    userList.add(user);

                }
                userAdapter.notifyDataSetChanged();
                progressBar.setVisibility(View.GONE);


                if(userList.isEmpty()){

                    Toast.makeText(MainActivity.this,"No Hirer",Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);

                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

    private void readHirer() {

        DatabaseReference reference=FirebaseDatabase.getInstance().getReference().child("user");
        Query query=reference.orderByChild("type").equalTo("hirer");

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                userList.clear();

                for (DataSnapshot dataSnapshot:snapshot.getChildren()){
                    User user=dataSnapshot.getValue(User.class);
                    userList.add(user);

                }
                userAdapter.notifyDataSetChanged();
                progressBar.setVisibility(View.GONE);


                if(userList.isEmpty()){

                    Toast.makeText(MainActivity.this,"No Hirer",Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);

                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){

            case  R.id.home:
                Intent intent13 =new Intent(MainActivity.this,MainActivity.class);
                startActivity(intent13);
                break;



            case  R.id.notifications:
                Intent intent10 =new Intent(MainActivity.this, NotificationActivity.class);
                intent10.putExtra("group","One-Day");
                startActivity(intent10);
                break;


            case  R.id.oneDay:
                Intent intent2 =new Intent(MainActivity.this,CategorySelectedActivity.class);
                intent2.putExtra("group","One-Day");
                startActivity(intent2);
                break;


            case  R.id.oneMonth:
                Intent intent3 =new Intent(MainActivity.this,CategorySelectedActivity.class);
                intent3.putExtra("group","One-Month");
                startActivity(intent3);
                break;



            case  R.id.permanent:
                Intent intent4 =new Intent(MainActivity.this,CategorySelectedActivity.class);
                intent4.putExtra("group","Permanent");
                startActivity(intent4);
                break;


            case  R.id.specialItem:
                Intent intent5 =new Intent(MainActivity.this,CategorySelectedActivity.class);
                intent5.putExtra("group","Special-Item");
                startActivity(intent5);
                break;

            case  R.id.mostHired:
                Intent intent6 =new Intent(MainActivity.this,CategorySelectedActivity.class);
                intent6.putExtra("group","Most-Hired");
                startActivity(intent6);
                break;


            case  R.id.hireForParty:
                Intent intent7 =new Intent(MainActivity.this,CategorySelectedActivity.class);
                intent7.putExtra("group","Hire-for-Party");
                startActivity(intent7);
                break;

           /* case  R.id.compatible:
                Intent intent8 =new Intent(MainActivity.this,CategorySelectedActivity.class);
                intent8.putExtra("group","Compatible with me");
                startActivity(intent8);
                break; */


            case  R.id.profile:
                Intent intent =new Intent(MainActivity.this,ProfileActivity.class);
                startActivity(intent);
                break;



            case R.id.logOut:
                FirebaseAuth.getInstance().signOut();
                Intent intent1 =new Intent(MainActivity.this,LogInActivity.class);
                startActivity(intent1);
                break;



            case  R.id.aboutApp:
                Intent intent9 =new Intent(MainActivity.this,AboutAppActivity.class);
                startActivity(intent9);
                break;

            case  R.id.aboutDeveloper:
                Intent intent12 =new Intent(MainActivity.this,AboutDeveloperActivity.class);
                startActivity(intent12);
                break;


            case  R.id.share:
                Intent intent11 =new Intent(MainActivity.this,ShareActivity.class);
                startActivity(intent11);
                break;

        }

        drawerLayout.closeDrawer(GravityCompat.START);

        return true;
    }
}