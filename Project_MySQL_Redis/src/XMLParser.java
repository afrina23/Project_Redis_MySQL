import java.util.ArrayList;
import java.util.Collection;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class XMLParser {
	
	
	static DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	
	
	
	private static Comment nodeToComment(Node n){
		Element row = (Element) n;
		
		NamedNodeMap attSet = row.getAttributes();
		
		long Id = Long.parseLong(row.getAttribute("Id"));
		long PostId = Long.parseLong(row.getAttribute("PostId"));
		int Score = Integer.parseInt(row.getAttribute("Score"));
		String Text = row.getAttribute("Text");
		String CreationDate = row.getAttribute("CreationDate");
		long UserId = Long.parseLong(row.getAttribute("UserId"));
		
		Comment comment =  new Comment (Id, PostId, Score, Text, CreationDate, UserId);
		//comment.printCommentRow();
		return comment;
	}

	
	public static void parseOneFile(String name) throws Exception{
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document doc = builder.parse(name);
		NodeList nd = doc.getElementsByTagName("row");

		
		for (int i = 0; i<nd.getLength(); i++){
			Node n = nd.item(i);
			
			if (n.getNodeType()== Node.ELEMENT_NODE){
				//convert to Comment and push to DataSet	
				try{Dataset.comments.add(nodeToComment(n));}
				catch (Exception e){
					System.out.println(name + " : Row " + i + " not inserted.");
				}
			}
		}
	}

}
