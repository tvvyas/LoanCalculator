package com.example.loancalculator;

public class LoanObj {

	private int id;
	private String name;
	private int amt;
	private int rate;
	private int time;
	private String agent;
	private String location;
	private String date;
	
	
	public long getId() {
	      return id;
	}
    public void setId(int id) {
       this.id = id;
    }
	public String getName() {
	   return this.name;
    }
   public void setName(String name) {
	        this.name = name;
   }
   public long getAmt() {
	      return this.amt;
	}
   public void setAmt(int amt) {
    this.amt = amt;
   }
   
   public long getRate() {
	      return this.rate;
	}
    public void setRate(int rate) {
    this.rate = rate;
   }
    
    public long getTime() {
	      return this.time;
	}
  public void setTime(int time) {
  this.time = time;
 }
  
  public String getAgent() {
	   return this.agent;
   }
  public void setAgent(String agent) {
	        this.agent = agent;
  }
  
  public String getLocation() {
	   return this.location;
  }
 public void setLocation(String location) {
	        this.location = location;
 }
 
 public String getDate() {
	   return this.date;
}
public void setDate(String date) {
	        this.date = date;
}
	    @Override
	
   public String toString() {
       return name;
   }
	
}
