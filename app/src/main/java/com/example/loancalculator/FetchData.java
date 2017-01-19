package com.example.loancalculator;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class FetchData extends Activity implements OnItemSelectedListener{

	EditText usname;
	TextView resultText1,resultText2,resultText3,resultText4,resultText5,resultText6;
	
	// Spinner element
    Spinner spinner;
	
	 public void onCreate(Bundle savedInstanceState){
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.display);
	        Button disply = (Button) findViewById(R.id.disply);
	        // Spinner element
	        spinner = (Spinner) findViewById(R.id.spinner1);
	               
	     // Loading spinner data from database
	        loadSpinnerData();
	     // Spinner click listener
	        spinner.setOnItemSelectedListener(this);
	        
	        final TextView resultText1 = (TextView)findViewById(R.id.textView2);
	        final TextView resultText2 = (TextView)findViewById(R.id.textView3);
	        final TextView resultText3 = (TextView)findViewById(R.id.textView4);
	        final TextView resultText4 = (TextView)findViewById(R.id.textView5);
	        final TextView resultText5 = (TextView)findViewById(R.id.textView6);
	        final TextView resultText6 = (TextView)findViewById(R.id.textView7);
	        final LoanOperation db = new LoanOperation(this);
	         db.open();
	         
	        disply.setOnClickListener(new OnClickListener() {            
		         public void onClick(View v) {                            
		       	 usname=(EditText)findViewById(R.id.usname);
		 	       final String edit1=usname.getText().toString();
		       	 LoanObj loanobj =db.findUser(edit1);
	     	        int result=db.getContactsCount();
	     	     //  resultText.setText(""+result);
		        	 if (loanobj != null) {
		        		 resultText1.setText("Amount = "+String.valueOf(loanobj.getAmt()));
		        		 resultText2.setText("Rate = "+String.valueOf(loanobj.getRate()));
		        		 resultText3.setText("Time = "+String.valueOf(loanobj.getTime()));
		        		 resultText4.setText("Agent = "+String.valueOf(loanobj.getAgent()));
		        		 resultText5.setText("Date = "+String.valueOf(loanobj.getDate()));
		        		 resultText6.setText("Location = "+String.valueOf(loanobj.getLocation()));
		             } else {
		            	 resultText1.setText("No Match Found");
		             } 
	     	        db.close();
                    Toast.makeText(FetchData.this,"DataFetch",Toast.LENGTH_LONG).show();// TODO Auto-generated method stub
                  	}
		        	 
		    
		  });
	         
	   }

	 /**
	     * Function to load the spinner data from SQLite database
	     * */
	    private void loadSpinnerData() {
	        // database handler
	    	LoanOperation db = new LoanOperation(getApplicationContext());	 
	    	db.open() ;
	        // Spinner Drop down elements
	        List<String> test = db.Get_Contacts();
	 
	        // Creating adapter for spinner
	        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
	                android.R.layout.simple_spinner_item, test);
	 
	        // Drop down layout style - list view with radio button
	        dataAdapter
	                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	 
	        // attaching data adapter to spinner
	        spinner.setAdapter(dataAdapter);
	        db.close();
	    }
	 
	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,
            long id) {
		 // On selecting a spinner item
        String label = parent.getItemAtPosition(position).toString();
 
        // Showing selected spinner item
        Toast.makeText(parent.getContext(), "You selected: " + label,
                Toast.LENGTH_LONG).show();
		
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	} 
	
}
