package com.cpsc476.urlshortner;

import java.nio.ByteBuffer;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Random;
import java.util.UUID;

import org.apache.commons.codec.binary.Base64;


public class URLShortner {
	HashMap<String, String> hashEncodedURLMap = new HashMap<>();
	HashMap<String, String> hashLongUrlMap = new HashMap<>();

	private String encodedURL= "";
	private String longURL= "";


	public URLShortner(){

	}
	
	
	public String getLongURL(String shortURL) {
		return generateLongURL(shortURL);
	}


	public void setLongURL(String longURL) {
		this.longURL = longURL;
	}


	public String getEncodedURL(String longURL) {

		return generateShortenedURL(longURL);
	}


	public void setEncodedURL(String encodedURL) {
		this.encodedURL = encodedURL;
	}


	public String generateShortenedURL(String longUrl){
		//convert longUrl into 36 bit hash value
		String shortURL="";
	    String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	    SecureRandom rnd = new SecureRandom();
	    StringBuilder sb = new StringBuilder() ;
	       for( int i = 0; i < 5; i++ ){ 
	          sb.append( AB.charAt( rnd.nextInt(AB.length()) ) );
	       }
	    shortURL= sb.toString();	
		return shortURL;
	}


	public String generateLongURL(String shortURL){

		String longURL = "";
		String hashKey = uuidFromEncodedURL(shortURL);
		if(hashLongUrlMap.containsKey(hashKey)){
			longURL = hashLongUrlMap.get(hashKey);
		}else{
			System.out.println("Error: Short URL not registered");
		}

		return longURL;
	}

	private static String uuidToEncodedURL(UUID hashKey) {
		Base64 base64 = new Base64();
		ByteBuffer bb = ByteBuffer.wrap(new byte[16]);
		bb.putLong(hashKey.getMostSignificantBits());
		bb.putLong(hashKey.getLeastSignificantBits());
		return base64.encodeBase64URLSafeString(bb.array());
	}


	private static String uuidFromEncodedURL(String encodedURL) {
		Base64 base64 = new Base64(); 
		byte[] bytes = base64.decodeBase64(encodedURL);
		ByteBuffer bb = ByteBuffer.wrap(bytes);
		UUID hashKey = new UUID(bb.getLong(), bb.getLong());
		return hashKey.toString();
	}


}


//---------------------------------------------------------
/* DO NOT CHANGE TRIED COUPLE OF THINGS> KEPT FOR REFERENCE> WILL CLEAN LATER */
//import java.util.Base64;
//import java.util.UUID;

/*
class URLShortner1 {
	// convert url to shortened urlhashKey
	String shortUrl = "";
	//private static final Charset UTF_8 = StandardCharsets.UTF_8;

	public String getShortUrl() {
		return shortUrl;
	}
	public void setShortUrl(String shortUrl) {
		this.shortUrl = shortUrl;
	}

	//	public String shortenUrl(String url){
	//		//if(isValid(url)) {

	//		//}
	//	}

	public static void main(String[] args){
		//System.out.println("Original String: " + new String(base64decodedBytes, "utf-8"));
		//        byte[] base64encodedString = Base64.getUrlEncoder().encodeToString("TutorialsPoint?java8".getBytes("utf-8"));
		//        System.out.println("Base64 Encoded String (URL) :" + base64encodedString);
		String str = "https://docs.oracle.com/javase/8/docs/api/java/util/Base64.Encoder.html";
		//generate uuid from longURL
		UUID uid = UUID.nameUUIDFromBytes(str.getBytes());
		System.out.println(uid.toString().length());
		String uidStr =uid.toString();
		System.out.println(uidStr);
		//		String uid = UUID.randomUUID().toString();
		//		System.out.println(uid);
		//		System.out.println(uid.length());
		String b64 = uuidToBase64(uidStr);
		System.out.println(b64);
		String backuid = uuidFromBase64(b64);
		System.out.println(backuid);
		//	        String ans = Base64.encodeBase64URLSafeString(uid.getBytes());
		//	        System.out.println(ans);
		//	        System.out.println(ans.length());
		//		String str = "https://docs.oracle.com/javase/8/docs/api/java/util/Base64.Encoder.html";
		//		byte[] binaryStr;
		//			binaryStr = str.getBytes();		
		//		String byte64encodeStr = Base64.getUrlEncoder().encodeToString(binaryStr);
		//		//System.out.println(byte64encodeStr);


		//generate uuid from longURL
		//		UUID urlUuid = UUID.nameUUIDFromBytes(str.getBytes());
		//		System.out.println(urlUuid.toString().length());
		//assertTrue(urlUuid.equals(UUID.nameUUIDFromBytes(str.getBytes())));

	}




	private static String uuidFromBase64(String str) {
		Base64 base64 = new Base64(); 
		byte[] bytes = base64.decodeBase64(str);
		ByteBuffer bb = ByteBuffer.wrap(bytes);
		UUID uuid = new UUID(bb.getLong(), bb.getLong());
		return uuid.toString();
	}

}
 */