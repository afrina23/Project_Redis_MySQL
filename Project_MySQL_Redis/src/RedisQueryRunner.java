import java.util.Comparator;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

import org.redisson.Redisson;
import org.redisson.core.RMap;


public class RedisQueryRunner {
	
	static  RMap<Integer, Comment> commentsMap;
	static Redisson redisson;
	
	public static void connectRedisson(){
		 redisson = Redisson.create();
		 System.out.println("redisson connect hoise");
		 commentsMap = redisson.getMap("Comments");
	}
	
	
	public static void showTop3LikedComment (){
		Comment top[] = new Comment[3];
		
		for (int i=0; i<3; i++){
			top[i] = new Comment();
		}
		System.out.println("Running query in redis...");
		
		for (int i=1; i<=commentsMap.size(); i++){
			if (commentsMap.get(i).Score > top[2].Score){
				if (commentsMap.get(i).Score > top[1].Score){
					if (commentsMap.get(i).Score > top[0].Score){
						top[2] = top[1];
						top[1] = top[0];
						top[0] = commentsMap.get(i);
					}
					else {
						top[2] = top[1];
						top[1] = commentsMap.get(i);
					}
				} 
				else {
					top[2] = commentsMap.get(i);
				}
			}
		}
		
		System.out.println("Top 3 most liked comments are :");
		System.out.println("1"); 
		top[0].printCommentRow(); 
		System.out.println();
		
		System.out.println("2"); 
		top[1].printCommentRow(); 
		System.out.println();
		
		System.out.println("3"); 
		top[2].printCommentRow(); 
		System.out.println();
	}
	
	
	
	static void showMostRecentComment (){
		System.out.println("Running query in redis...");
		Comment top = new Comment ();
		for (int i=1; i<=commentsMap.size(); i++){
			if (commentsMap.get(i).Score > top.Score){
				top = commentsMap.get(i);
			}
		}
		
		System.out.println("The most recent comment is :"); 
		top.printCommentRow(); 	
	}
	
	
	static void showMostCommentedPost(){
		System.out.println("Running query in redis...");
		Comment top = new Comment ();
		
		TreeMap<Long,Integer> map = new TreeMap<Long,Integer>();
		
		for (int i=1; i<=commentsMap.size(); i++){
			
			Long currId = commentsMap.get(i).PostId;
			try {
				map.put(currId, map.get(currId)+1);
			} catch (Exception e){
				map.put(currId,1);
			}
		}
		
		System.out.println("Top commented post id POST NO - " + map.lastKey() +" Total comments - " +map.get(map.lastKey()));
	}
	
	
	static <K,V extends Comparable<? super V>>
    SortedSet<Map.Entry<K,V>> entriesSortedByValues(Map<K,V> map) {
        SortedSet<Map.Entry<K,V>> sortedEntries = new TreeSet<Map.Entry<K,V>>(
            new Comparator<Map.Entry<K,V>>() {
                public int compare(Map.Entry<K,V> e1, Map.Entry<K,V> e2) {
                    int res = e1.getValue().compareTo(e2.getValue());
                    return res != 0 ? res : 1;
                }
            }
        );
        sortedEntries.addAll(map.entrySet());
        return sortedEntries;
    }
	
}
