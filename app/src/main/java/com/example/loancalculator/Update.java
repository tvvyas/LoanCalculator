package com.example.loancalculator;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Update extends Activity {
	
	EditText rname,ramount;
	TextView resultText1,resultText2,resultText3;
	
	 public void onCreate(Bundle savedInstanceState){
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.update);
	        Button update = (Button) findViewById(R.id.update);
	       
	        final LoanOperation db = new LoanOperation(this);
	         db.open();
	         
	         
	        
	         
	        update.setOnClickListener(new OnClickListener() {            
		         public void onClick(View v) {                            
		        	 rname=(EditText)findViewById(R.id.rname);
		        	 ramount=(EditText)findViewById(R.id.ramount);
		 	        final String edit1=rname.getText().toString();
		 	       final String edit2=ramount.getText().toString();
 	               
		 	       
					int loanobj =db.UpdateUser(edit1,edit2);
	     	    //    int result=db.getContactsCount();
	     	     //  resultText.setText(""+result);
					 
		        	      db.close();
                    Toast.makeText(Update.this,"Data Updated",Toast.LENGTH_LONG).show();// TODO Auto-generated method stub
                  	}
		        	 
		    
		   });
	         
	    } 
	 
}
