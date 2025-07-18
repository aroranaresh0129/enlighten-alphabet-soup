package textsearch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class TextSearchInGridTest {
	
	 @Test
	    public void GetAnswerKey_5x5Input_FoundAnswers() {
	        String[] answers = new String[] {
	                "HELLO 0:0 4:4",
	                "GOOD 4:0 4:3",
	                "BYE 1:3 1:1",
	                "A 0:1 0:1"
	        };
	        
	        int gridWidth = 5; int gridLength = 5;
	        
	        ArrayList<String> result = new ArrayList<>();
	        
	        List<String> expected = Arrays.asList(answers);
	        
	        String[] grid = new String[] {
	                "H", "A", "S", "D", "F",
	                "G", "E", "Y", "B", "H",
	                "J", "K", "L", "Z", "X",
	                "C", "V", "B", "L", "N",
	                "G", "O", "O", "D", "O"
	        };
	        
	        
	        String[] words = new String[] {"HELLO", "GOOD", "BYE", "A"};   
	        
	        result =  TextSearchInGrid.findTextCoordinates(makeGrid(grid, gridWidth),Arrays.asList(words), gridLength, gridWidth);
	        Assert.assertEquals(expected, result);
	    }
	 
	 
	   @Test
	    public void GetAnswerKey_14x12Input_FoundAnswers() {
	        String[] answers = new String[] {
	                "CLARINET 5:10 5:3",
	                "ACCORDION 10:2 10:10",
	                "BAGPIPE 2:0 2:6",
	                "FLUTE 1:0 1:4",
	                "BONGO DRUMS 7:2 7:11",
	                "SNARE 8:3 8:7",
	                "BAZOOKA 11:3 11:9",
	                "CORNET 4:0 9:0",
	                "HARMONICA 0:2 0:10",
	                "OBOE 12:3 12:6",
	                "OCARINA 0:11 6:11",
	                "PAN PIPE 9:10 9:4",
	                "PICCOLO 3:4 3:10",
	                "RECORDER 6:3 6:10",
	                "SAXOPHONE 11:1 3:1",
	                "TROMBONE 4:9 4:2",
	                "TRUMPET 13:7 13:1",
	                "FIDDLE 13:11 8:11",
	                "GUITAR 1:10 1:5",
	                "VIOLA 2:7 2:11",
	                "NONPROS 6:2 12:8"
	        };
	        List<String> expected = Arrays.asList(answers);
	        
            int gridWidth = 12; int gridLength = 14;
	        
	        ArrayList<String> result = new ArrayList<>();

	        String[] grid = new String[] {
	                "R", "I", "H", "A", "R", "M", "O", "N", "I", "C", "A", "O",
	                "F", "L", "U", "T", "E", "R", "A", "T", "I", "U", "G", "C",
	                "B", "A", "G", "P", "I", "P", "E", "V", "I", "O", "L", "A",
	                "A", "E", "T", "F", "P", "I", "C", "C", "O", "L", "O", "R",
	                "C", "N", "E", "N", "O", "B", "M", "O", "R", "T", "I", "I",
	                "O", "O", "O", "T", "E", "N", "I", "R", "A", "L", "C", "N",
	                "R", "H", "N", "R", "E", "C", "O", "R", "D", "E", "R", "A",
	                "N", "P", "B", "O", "N", "G", "O", "D", "R", "U", "M", "S",
	                "E", "O", "I", "S", "N", "A", "R", "E", "P", "B", "K", "E",
	                "T", "X", "P", "E", "E", "P", "I", "P", "N", "A", "P", "L",
	                "F", "A", "A", "C", "C", "O", "R", "D", "I", "O", "N", "D",
	                "H", "S", "O", "B", "A", "Z", "O", "O", "K", "A", "S", "D",
	                "A", "P", "O", "O", "B", "O", "E", "F", "S", "Z", "O", "I",
	                "N", "T", "E", "P", "M", "U", "R", "T", "O", "B", "A", "F"
	        };
	     
	        
	        String[] words = new String[] {
	                "CLARINET", "ACCORDION", "BAGPIPE", "FLUTE","BONGO DRUMS", "SNARE", "BAZOOKA", "CORNET",
	                "HARMONICA", "OBOE", "OCARINA", "PAN PIPE", "PICCOLO", "RECORDER", "SAXOPHONE", "TROMBONE",
	                "TRUMPET", "FIDDLE", "GUITAR", "VIOLA",  "NONPROS"

	        };
	  
	        result =  TextSearchInGrid.findTextCoordinates(makeGrid(grid, gridWidth),Arrays.asList(words), gridLength, gridWidth);

	        Assert.assertEquals(expected, result);
	    }
	 
	 private ArrayList<String> makeGrid(String[] input, int gridWidth){
		 
		 ArrayList<String> grid = new ArrayList<>();
		 int count = 0; int i =0;
		 
		 while(i<input.length) {
			 
			 StringBuffer gridStr = new StringBuffer();
			 
			while(count<gridWidth) {
				//StringBuffer gridStr = new StringBuffer();
				 gridStr.append(input[i]);
				 count = count + 1;
				 i = i +1;
			}
			 
			 grid.add(gridStr.toString());
			 count = 0;
		 }
		 
		 
		 return grid;
	 }

}
