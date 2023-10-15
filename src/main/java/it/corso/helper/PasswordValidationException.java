package it.corso.helper;

//classe per la validazione personalizzata password
public class PasswordValidationException extends RuntimeException 
{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6725397959361426533L;
	private final String message = "Invalid Password";
	
	public String getMessage()
	{
		return message;
	}

}
