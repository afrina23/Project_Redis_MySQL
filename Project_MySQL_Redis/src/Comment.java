
public class Comment {

	long Id;
	long PostId;
	int Score;
	String Text;
	String CreationDate;
	long UserId;
	
	
	
	public Comment (){
		Id = 0;
		PostId = 0;
		Score = 0;
		Text = "";
		CreationDate = "";
		UserId = 0;
	}
	
	public Comment(long id,long postId,int score,String text,String creationDate,long userId){
		Id=id;
		PostId=postId;
		Score=score;
		Text=text;
		CreationDate=creationDate;
		UserId=userId;
	}
	
	
	public void printCommentRow (){
		System.out.println("Id : " + Id + ", PostId : " + PostId + ", Score : " + Score + ", Text : " + Text + ", Creation Date : " + CreationDate + ", User Id : " + UserId );
	}

}
