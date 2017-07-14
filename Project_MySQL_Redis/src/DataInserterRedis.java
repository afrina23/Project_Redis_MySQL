import org.redisson.Redisson;
import org.redisson.core.RMap;


public class DataInserterRedis {

	/**
	 * @param args
	 */
	static Redisson redisson;
	static RMap<Integer, Comment> Map;
	
	public static void connectRedisson(){
		 redisson= Redisson.create();
		 System.out.println("redison hoise");
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		connectRedisson();
		System.out.println("Redissson connected");
		//XMLParser.parseXML();
		
		//redisson.getMap("CommentsRedis").clear();		
		Map= redisson.getMap("Comments");


		System.out.println("Table is ready.");
		
		try {
			int k =1;
			
	    	for(int i=1;i<=56;i++){
	    		String filename="XMLFiles/Comments_"+i+".xml";
	    		try{
	    			XMLParser.parseOneFile(filename);
	    			for(int j=0;j<Dataset.comments.size();j++){
	    				
	    				Map.put(k, Dataset.comments.get(j));
	    				k++;
	    			}
	    			System.out.println("Parsed file : " + filename);
	    			Dataset.comments.clear();
	    		} catch (Exception e){
	    			System.out.println("File " + i + " not parsed.");
	    		}
	    	}
			
			
			
		} 
	     catch ( Exception e) {
			e.printStackTrace();
		}
		
		
		
		//System.out.println(Dataset.comments.size());
		System.out.println("program finished");
	}

}
