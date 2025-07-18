package textsearch;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class TextSearchInGrid {
	
	
	public static ArrayList<String> findTextCoordinates(List<String> gridList, List<String> searchTextList, int gridLength,  int gridWidth) {

		String coordinates = null;
		char[][] grid = new char[gridLength][gridWidth];
		ArrayList<String> searchCoordinatesList = new ArrayList<>();
		
		
		for(int i=0; i < gridLength; i++) {
		   
     	   char[] temp = gridList.get(i).replaceAll("[\\s,]","").toCharArray();
     	   grid[i] = temp;
        }
		
		for(String searchText:searchTextList) {
			String orgSearchString = searchText;
			
			searchText = searchText.replaceAll("\\s+", "");


			
			coordinates = horzSearch(grid, searchText, gridLength, gridWidth) ;
			
			if("" == coordinates) {
				
				 coordinates = vertSearch(grid, searchText, gridLength, gridWidth) ;
				
				 if("" == coordinates) {
					
				 coordinates = diagSearch(grid, searchText, gridLength, gridWidth) ;
					
				}
				
			}
			searchCoordinatesList.add(orgSearchString  + " " + coordinates);
		}

    	return searchCoordinatesList;
	}
	
	
	public static String horzSearch(char[][] grid, String searchText, int gridLength,  int gridWidth) {
		
		String coordinates = "";
		int rows = gridLength;
		int cols = gridWidth;
		int startIndex = 0;
		int endIndex = 0;
		int searchTextLength = searchText.length();

		for (int i = 0; i < rows; i++) { // 

            StringBuilder horzString = new StringBuilder();
            StringBuilder horzStringRev = new StringBuilder();
            
            for (int j = 0; j < cols; j++) { 
                 horzString.append(grid[i][j]);
            }
            if (horzString.toString().contains(searchText)) {
              startIndex = horzString.indexOf(searchText);
          	  endIndex = startIndex + (searchTextLength - 1);
          	  coordinates = String.valueOf(i)+ ":" + String.valueOf(startIndex)+ " " + String.valueOf(i)+ ":" + String.valueOf(endIndex);
              }
            if ( !horzString.toString().contains(searchText)) {
            	horzStringRev = horzString.reverse();
            }  
            if (horzStringRev.toString().contains(searchText)) {
              startIndex = horzString.indexOf(searchText);
          	  endIndex = startIndex + (searchTextLength - 1);
          	  coordinates = String.valueOf(i)+ ":" + String.valueOf(horzStringRev.length()-1-startIndex)+ " " + String.valueOf(i)+ ":" + String.valueOf((horzStringRev.length()-1)-endIndex);
             } 
            if(coordinates != "")
	        	break;
        }

		return coordinates;
	}
	
    public static String vertSearch(char[][] grid, String searchText, int gridLength, int gridWidth) {
		
		String coordinates = "";
		int rows = gridLength;
	    int cols = gridWidth;
	    int searchTextLength = searchText.length();
	    //char[] searchChars = searchText.toCharArray();
	    int startIndex = 0;
		int endIndex = 0;
	   // StringBuilder vertString = new StringBuilder();
	  //  StringBuilder vertStringRev = new StringBuilder();

	    for (int j = 0; j < cols; j++) { 
	    	StringBuilder vertString = new StringBuilder();
		    StringBuilder vertStringRev = new StringBuilder();
		    
	        for (int i = 0; i < rows; i++) { 
	           vertString.append(grid[i][j]);
            }
	        if (vertString.toString().contains(searchText)) {
	              startIndex = vertString.indexOf(searchText);
	        	  endIndex = startIndex + (searchTextLength - 1);
	        	  coordinates = String.valueOf(startIndex)+ ":" + String.valueOf(j) + " " +  String.valueOf(endIndex)+ ":" + String.valueOf(j);
	        }
	        if (!vertString.toString().contains(searchText)) {
            	vertStringRev = vertString.reverse();
            } 
	        if (vertStringRev.toString().contains(searchText)) {
	              startIndex = vertString.indexOf(searchText);
	        	  endIndex = startIndex + (searchTextLength - 1);
	        	  coordinates = String.valueOf((vertStringRev.length()-1)-startIndex)+ ":" + String.valueOf(j)+ " " + String.valueOf((vertStringRev.length()-1)-endIndex)  + ":" + String.valueOf(j) ;
	              
	        }
	        if(coordinates != "")
	        	break;
	    }
		
		return coordinates;
	}
    
    public static String diagSearch(char[][] grid, String searchText, int gridLength, int gridWidth) {
		
    	String coordinates = "";
		int rows = gridLength;
	    int cols = gridWidth;
	    int searchTextLength = searchText.length();
	    //char[] searchChars = searchText.toCharArray();
	    int startIndex = 0;
		int endIndex = 0;
		
		
		for (int i = 0; i < rows; i++) { 
	    	StringBuilder diagString = new StringBuilder();
		    StringBuilder diagStringRev = new StringBuilder();
		    
	        for (int j = 0; j < cols; j++) { 
	        	diagString = appendDiaString(grid, j, i, gridLength, gridWidth);
            
		        if (diagString.length() >= searchText.length() && diagString.toString().contains(searchText)) {
		              startIndex = diagString.indexOf(searchText);
		        	  endIndex = startIndex + (searchTextLength - 1);
		        	  //coordinates = String.valueOf(startIndex + j)+ ":" + String.valueOf(i) + " " +  String.valueOf(endIndex + i)+ ":" + String.valueOf(grid[0].length -1 -j);
		        	  coordinates = String.valueOf(startIndex + j)+ ":" + String.valueOf(startIndex + i) + " " +  String.valueOf(endIndex + j)+ ":" + String.valueOf(endIndex +i);
		        }
		        
		        if(coordinates.equals("")) {
					 
		        	diagStringRev = diagString.reverse();
		        	
		        	 if (diagStringRev.length() <= searchText.length() && diagStringRev.toString().contains(searchText)) {
			              startIndex = diagStringRev.indexOf(searchText);
			        	  endIndex = startIndex + (searchTextLength - 1);
			        	  //coordinates = String.valueOf(startIndex + j)+ ":" + String.valueOf(i) + " " +  String.valueOf(endIndex + i)+ ":" + String.valueOf(grid[0].length -1 -j);
			        	  coordinates =    String.valueOf(endIndex + j)+ ":" + String.valueOf(endIndex +i)+ " " +  String.valueOf(startIndex + j)+ ":" + String.valueOf(startIndex + i);
			        }
			        
					 
				 }
		        
		        if(!coordinates.equals(""))
		        	break;
	        }
	        
	        if(!coordinates.equals("")) {
	        	break;
	        } 
			
		}
		
		return coordinates;
	}
    
    public static StringBuilder appendDiaString(char[][] grid, int strx, int stry, int gridLength, int gridWidth) {
    	StringBuilder diagString = new StringBuilder();
    	
    	while(strx < gridLength && stry < gridWidth) {
    		
    		diagString.append(grid[strx][stry]);
    		strx = strx +1;
    		stry = stry +1;
    	}
    	
	    return diagString;
    }
    
    public static void main(String[] args) {
    	
    	if (args.length < 1) {
            System.out.println("Usage: java FileProcessor <filename>");
            return; // Exit if no file argument is provided
        }

        String filePath = args[0];
    	//String filePath = "C://Users/arora/test.txt";
    	
    	
    	
    	Path path = Paths.get(filePath);
    	
    	try { 
    		List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
    		
    		int lengthOfGrid = 0; int widthOfGrid = 0;
    		ArrayList<String> searchCoordinatesList = new ArrayList<>();
    		String gridSize = "";
    		List<String> gridList = new ArrayList<>();
    		List<String> searchStrs = new ArrayList<>();
    		
    		//lengthOfGrid = lines.get(0).charAt(0);

            for(String line:lines) {
        	   
            	if(line.contains("x")) {
            		lengthOfGrid = Integer.valueOf(String.valueOf(line.charAt(0)));
            		widthOfGrid = Integer.valueOf(String.valueOf(line.charAt(0)));
            	}
            	else if(line.contains(" ")) {
        		   gridList.add(line);
        	   } else {
        		   
        		   searchStrs.add(line);
        	    }
            }
           

            searchCoordinatesList = findTextCoordinates(gridList, searchStrs, lengthOfGrid, widthOfGrid);
            
            for(String searchCoordinate:searchCoordinatesList) {
            	
            	System.out.println(searchCoordinate);
            }
           
    	}catch(IOException ex){
    		System.err.println("Error reading file: " + ex.toString());
    	}
    			
     }
    	
   	
}
    




