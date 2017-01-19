package com.example.loancalculator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class Newentry extends Activity {
	
	protected void onCreate(Bundle savedInstanceState) {
		        // TODO Auto-generated method stub
		         super.onCreate(savedInstanceState);
		        setContentView(R.layout.newentry);
		         Button newentry = (Button) findViewById(R.id.newentry);
		         Button showold = (Button) findViewById(R.id.showold);
		         Button update = (Button) findViewById(R.id.update);
		         Button history = (Button) findViewById(R.id.history);
		      //   newentry.setOnClickListener((OnClickListener) this);
		       //  disply.setOnClickListener((OnClickListener) this);
		         
		          newentry.setOnClickListener(new OnClickListener() {            
		         public void onClick(View v) {                            
		    	//setContentView(R.layout.newentry);
		    	 startActivity(new Intent(getApplicationContext(), MainActivity.class));     
		    }
		   });
		         
		          showold.setOnClickListener(new View.OnClickListener() {
		                public void onClick(View v) {
		                	startActivity(new Intent(getApplicationContext(), FetchData.class)); 
		                	//  Intent myintent2 = new Intent(Newentry.this,FetchData.class);
		                    //startActivity(myintent2);

		                }
		            });
		           
		          update.setOnClickListener(new View.OnClickListener() {
		                public void onClick(View v) {
		                	startActivity(new Intent(getApplicationContext(), Update.class)); 
		                	//  Intent myintent3 = new Intent(Newentry.this,Update.class);
		                   // startActivity(myintent3);

		                }
		            });  
		          
		          
		          history.setOnClickListener(new View.OnClickListener() {
		                public void onClick(View v) {
		                	startActivity(new Intent(getApplicationContext(), ListDisplay.class)); 
		                	//  Intent myintent3 = new Intent(Newentry.this,Update.class);
		                   // startActivity(myintent3);

		                }
		            });   
	
		}}

	
