package controller;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.DBRequesthandler;


@WebServlet(
        name = "shortURLHandler",
        urlPatterns = "/short/*"
)

public class UrlRequesthandler extends HttpServlet{
	
	DBRequesthandler reqHandler = new DBRequesthandler();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException , IOException
    {
		//Get all objects of UserUrlList class.
		//search for relevant match
		//add clicks to URL
		
		String action = request.getParameter("action");
        
        if(action == null)
            action = "page";
        
        switch(action)
        {
            case "gotoUrl":
            	System.out.println("in PP");
                this.gotoUrl(request, response);
                break;
            case "page":
            default:
            	browserUrlRequstAction(request, response);
                break;
        }
    }
	
	public void gotoUrl(HttpServletRequest request, HttpServletResponse response)
			 throws ServletException, IOException
			 {
				
				System.out.println("In process");
				String orgUrl = "";
				String shUrl = request.getParameter("url");
				//String parts[] = shUrl.split("short/");
				if(reqHandler.shortUrlexists(shUrl)){
					orgUrl = reqHandler.getLongUrl(shUrl);
					reqHandler.addUrlVisitCount(shUrl);
					response.sendRedirect(orgUrl);
				}
				else{
					System.out.println("Url does not exist" + shUrl);
					//redirect to 404 page
				}
				
			 }
	
	public void browserUrlRequstAction(HttpServletRequest request, HttpServletResponse response)
			 throws ServletException, IOException
			 {
				String shUrl = request.getRequestURI();
				shUrl = "http://localhost:8080" + shUrl;
				
				String orgUrl = "";
			
				if(reqHandler.shortUrlexists(shUrl)){
					orgUrl = reqHandler.getLongUrl(shUrl);
					reqHandler.addUrlVisitCount(shUrl);
					response.sendRedirect(orgUrl);
				}
				else{
					System.out.println("Url does not exist" + shUrl);
					//redirect to 404 page
				}
			 }
}









/*
	public String generateShortenedURL(String longUrl){
		//convert longUrl into 36 bit hash value
		String encodedURL="";
		try{
			UUID hashKey = UUID.nameUUIDFromBytes(longUrl.getBytes());
			String hashKeyStr = hashKey.toString();
			if(!hashLongUrlMap.containsKey(hashKeyStr)){
				hashLongUrlMap.put(hashKeyStr, longUrl);
			}else{
				encodedURL = hashEncodedURLMap.get(hashKeyStr);
				return encodedURL;
			}
			
			//generate base64 encoded key from uuid
			encodedURL = uuidToEncodedURL(hashKey);			
			if(!hashEncodedURLMap.containsKey(hashKeyStr)){
				hashEncodedURLMap.put(hashKeyStr, encodedURL);
			}
			

		}catch(Exception ex){
			ex.printStackTrace(System.out);
		}

		return encodedURL;
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
	// convert url to shortened url
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