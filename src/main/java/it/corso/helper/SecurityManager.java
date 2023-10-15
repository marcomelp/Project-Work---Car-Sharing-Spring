package it.corso.helper;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.Base64;

public class SecurityManager {

	public static String encode(String toEncode)
	{
		return Base64.getEncoder().encodeToString(toEncode.getBytes()); // dal dato otiginale creiamo una sorta di array di byte che dalla stringa ci ritorna un array di byte. Primo metodo per codificare
		
	}
	
	//metodo per fare ritornare la password in chiaro
	public static String decode(String toDecode)
	{
		return new String(Base64.getDecoder().decode(toDecode)); // gli passiamo la stringa da decodificare. Ma ci ritorna l'array di byte. Aggiungo quindi = return new String()
	}
	
	
	// per generare il token
	public static String generateToken(String username, boolean type) // ipotizziamo: --> true = admin false = customer(product)
	{
		LocalDateTime now = LocalDateTime.now();
		Instant instant = now.toInstant(OffsetDateTime.now().getOffset());
		long timestamp = instant.getEpochSecond() * 1000;
		String suffix = type ? "_A" : "_C"; // diventa così in base a se è un admin oppure o un customer. Ecco il perchè del valore booleano.
		//IN ESERCIZIO REAL-ESTATE INVECE NON è COSì PERCHè NON CI SERVE IL VALORE BOOLEANO IN QUANTO ABBIAMO SOLO L'ADMIN!!!!
		return encode(username) + "_" + timestamp + suffix;
	}
}

