package es.uji.ei1027.majorsACasa.controller;

public class MajorsACasaException extends RuntimeException{
	String message;
	String errorName;
	
	public MajorsACasaException(String message, String errorName){
		this.message=message;
		this.errorName=errorName;
	   }

	   public String getMessage() {
	       return message;
	   }

	   public void setMessage(String message) {
	       this.message = message;
	   }

	   public String getErrorName() {
	       return errorName;
	   }

	   public void setErrorName(String errorName) {
	       this.errorName = errorName;
	   }
}
