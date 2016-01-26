/**
 * Author:Bo Yang
 */
package ju15.book.model;

public class BookInfo {

	String itemnum;
	String name;
	String email;
	String startDate;
	String endDate;
	String message;
	public BookInfo() {
		
	}
	public BookInfo(String itemnum, String name, String email, String startDate, String endDate, String message) {
		super();
		this.itemnum=itemnum;
		this.name = name;
		this.email = email;
		this.startDate = startDate;
		this.endDate = endDate;
		this.message = message;
	}
	public String getItemnum() {
		return itemnum;
	}
	public void setItemnum(String itemnum) {
		this.itemnum = itemnum;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

	
}
