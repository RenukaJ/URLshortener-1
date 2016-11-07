package model.dao;
import model.dto.UserUrl;
public interface UserURLDao {
	/*
	 * This DAO will contain shortened URL List Per user
	 */
	public void addUrlToUserList(String username, String shortUrl, String LongUrl);
	public void deleteUserListValue(String urlToRemove);
	public <UserUrl> List<UserUrl> getUserUrlList(String username);
	public boolean checkIfUrlExistsForUser(int userid, String shortUrl);
	public int getUserId(String username);

}
