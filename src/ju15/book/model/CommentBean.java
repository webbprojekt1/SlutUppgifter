/**
 * Author:Irina Fatkoulin
 */

package ju15.book.model;

public class CommentBean {
	private String feedBack;
	
	public CommentBean(){
		
	}
	public CommentBean(String feedBack){
		this.feedBack=feedBack;
		}

	public String getFeedBack() {
		return feedBack;
	}

	public void setFeedBack(String feedBack) {
		this.feedBack = feedBack;
	}
	
	
}


