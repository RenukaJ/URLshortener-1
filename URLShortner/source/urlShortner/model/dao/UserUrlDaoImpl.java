package model.dao;

public class UserUrlDaoImpl implements UserUrlDao{
	/*
	 * This class contains the implementation for UserUrlDao Interface
	 */
	
	/*
	 * TODO: Return type might be needed to be changed
	 */
	public void addNewUrlToList(String shortUrl, String LongUrl){
		
	}
	public void deleteUserListValue(String urlToRemove){
		
	}
	/*
	 * Return type needs to be changed
	 */
	public void getUserUrlList(){
		
	}
	
	
}


/*
public UserUrlList(String shortUrl, String longUrl){
	this.userUrlList.put(shortUrl, longUrl);
}

public void addNewUserListValue(String shortUrl, String longUrl){
	this.userUrlList.put(shortUrl, longUrl);

}

public boolean deleteUserListValue(String urlToRemove){

	for(Iterator<Map.Entry<String, String>> it = userUrlList.entrySet().iterator(); it.hasNext(); ) {
		Map.Entry<String, String> entry = it.next();
		if(entry.getKey().equals(urlToRemove)) {
			it.remove();
			return true;
		}
	}
	return false;	
}

public Map<String, String> getUserUrlList(){
	return this.userUrlList;
}
*/
