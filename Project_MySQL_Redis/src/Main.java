import java.util.Scanner;


public class Main {

	
	
	
	
	public static void main(String[] args) {
		
		MySQLQueryRunner.connectMySQL();
		RedisQueryRunner.connectRedisson();
		
		int whichQuery = -1;
		
		System.out.println();
		
		String[] queries = new String[3];
		queries[0] = "Show the top 3 liked comments.";
		queries[1] = "Show the most recent comment.";
		queries[2] = "Show the most commented post.";
		
		for (int i=1; i<=3; i++) System.out.println(i + "." + queries[i-1]);
		System.out.println("Press 1/2/3 for the first/second/third query. Press 0 to exit.");
		
		
		while (whichQuery != 0){
			
			Scanner scan=new Scanner(System.in);			
			whichQuery = scan.nextInt();
			
			long startTime = System.currentTimeMillis();

			
			switch (whichQuery){
			case 1:{				
				RedisQueryRunner.showTop3LikedComment();
				
				long endTimeRedis   = System.currentTimeMillis();
				long totalTimeRedis = endTimeRedis - startTime;
				System.out.println("Redis took " + totalTimeRedis/1000 + " seconds.");
				
				
				MySQLQueryRunner.showTop3LikedComment();

				long endTimeSQL   = System.currentTimeMillis();
				long totalTimeSQL = endTimeSQL - endTimeRedis;
				System.out.println("MySQL took " + totalTimeSQL/1000 + " seconds.");
				break;
			}
			case 2:{
				
				RedisQueryRunner.showMostRecentComment();
				
				long endTimeRedis   = System.currentTimeMillis();
				long totalTimeRedis = endTimeRedis - startTime;
				System.out.println("Redis took " + totalTimeRedis/1000 + " seconds.");
				
				MySQLQueryRunner.showMostRecentComment();
				
				long endTimeSQL   = System.currentTimeMillis();
				long totalTimeSQL = endTimeSQL - endTimeRedis;
				System.out.println("MySQL took " + totalTimeSQL/1000 + " seconds.");
				
				break;
			}
			case 3:{
				RedisQueryRunner.showMostCommentedPost();
				
				long endTimeRedis   = System.currentTimeMillis();
				long totalTimeRedis = endTimeRedis - startTime;
				System.out.println("Redis took " + totalTimeRedis/1000 + " seconds.");
				
				MySQLQueryRunner.showMostCommentedPost();
				
				long endTimeSQL   = System.currentTimeMillis();
				long totalTimeSQL = endTimeSQL - endTimeRedis;
				System.out.println("MySQL took " + totalTimeSQL/1000 + " seconds.");
				
				break;
			}
			default : {
				if (whichQuery != 0) System.out.println("Enter a valid number.");			
				break;	
			}
			}
		}
		
		
		
		
		
		System.out.println("Program finished");
	}

}
/*long startTime = System.currentTimeMillis();
.....your program....
long endTime   = System.currentTimeMillis();
long totalTime = endTime - startTime;
System.out.println(totalTime);*/