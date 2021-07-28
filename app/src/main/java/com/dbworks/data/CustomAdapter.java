package com.dbworks.data;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.dbworks.R;

import org.jetbrains.annotations.NotNull;
import java.util.ArrayList;
import java.util.Random;

//Use extends Recycler View , this is done to fetch data and show in the data Cardview student xml file
public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    ArrayList<Student> dataset;
    Context context;

// Step 1 is making View Holder
    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView tv_name;
        TextView tv_contact;
        TextView tv_rollno;
        Button btn_title;
        ImageView iv_gender;
        ImageView iv_phone;
        ConstraintLayout cl_student;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.tv_name= (TextView) itemView.findViewById(R.id.tv_name);
            this.tv_contact= (TextView) itemView.findViewById(R.id.tv_contact);
            this.tv_rollno= (TextView) itemView.findViewById(R.id.tv_rollno);
            this.iv_gender= (ImageView) itemView.findViewById(R.id.iv_gender);
            this.iv_phone= (ImageView) itemView.findViewById(R.id.iv_phone);
            this.btn_title= (Button) itemView.findViewById(R.id.btn_title);
            this.cl_student=(ConstraintLayout)itemView.findViewById(R.id.cl_student);
        }
    }
    //The construction My View Holder Ends here


    //Make Parameterized constructor for CustomAdapter
    public CustomAdapter(ArrayList<Student> dataset, Context context)
    {
        this.dataset=dataset;
        this.context=context;
    }

    // Make oncreate ViewHolder to inflate the View created
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //inflate
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.data_cardview_student,parent,false);
        MyViewHolder myViewHolder= new MyViewHolder(view);
        return myViewHolder;
    }


    // Step 2 Make on bind view holder to bind the data from View Created above
    @Override
    public void onBindViewHolder(@NonNull @NotNull CustomAdapter.MyViewHolder holder, int position) {
        TextView tv_name=holder.tv_name;
        TextView tv_contact=holder.tv_contact;
        TextView tv_rollno=holder.tv_rollno;
        Button btn_title=holder.btn_title;
        ImageView iv_gender=holder.iv_gender;
        ImageView iv_phone=holder.iv_phone;
        ConstraintLayout cl_student=holder.cl_student;

        tv_name.setText(dataset.get(position).student_name+ " ");
        tv_contact.setText(dataset.get(position).contact_no+ " ");
        tv_rollno.setText(dataset.get(position).roll_no+ " ");

        //tthis is for male and female distinct icon
        if(dataset.get(position).gender.equalsIgnoreCase("male"))
        {
             iv_gender.setImageResource(R.drawable.male);
        }else if (dataset.get(position).gender.equalsIgnoreCase("female"))
        {
            iv_gender.setImageResource(R.drawable.female);
        }
        //this for male and female distinct icon ends here

        //setting title first letter in the imageview
        btn_title.setText( dataset.get(position).student_name.toUpperCase().charAt(0)+"");

        //setting random color to the title image
        Random random= new Random();
        int red=random.nextInt(255);
        int green=random.nextInt(255);
        int blue=random.nextInt(255);
        btn_title.setBackgroundColor(Color.rgb(red,green,blue));

        //calling when touched on phone number
        if(dataset.get(position).contact_no.length()>9)
        {
            tv_contact.setVisibility(View.VISIBLE);
            tv_contact.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+dataset.get(position).contact_no));
                    context.startActivity(intent);
                }
            });
        }
        else
        {
            tv_contact.setVisibility(View.GONE);
        }//calling optiion ends

        //settiing action loistener on the list view to update data
        cl_student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int roll_no=dataset.get(position).roll_no;
                String student_name=dataset.get(position).student_name;
                String contact_no=dataset.get(position).contact_no;
                String gender=dataset.get(position).gender;
                //Toast.makeText(context,roll_no+"\n"+student_name+"\n"+gender+"\n"+contact_no+"\n",Toast.LENGTH_LONG).show();
                Intent intent=new Intent(context, StudentActivity.class);
                intent.putExtra("student_name",student_name);
                intent.putExtra("roll_no",roll_no);
                intent.putExtra("contact_no",contact_no);
                intent.putExtra("gender",gender);
                context.startActivity(intent);
            }
        });


    }

    //Step 3 is to make a getItem count
    @Override
    public int getItemCount() {
        return dataset.size();
    }
}
