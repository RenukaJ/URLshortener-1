package model.dao;

public interface UserUrlDao {
	/*
	 * This DAO will contain shortened URL List Per user
	 */
	
	/*
	 * TODO: Return type might be needed to be changed
	 */
	public void addNewUrlToList(String shortUrl, String LongUrl);
	public void deleteUserListValue(String urlToRemove);
	/*
	 * Return type needs to be changed
	 */
	public void getUserUrlList();

}
