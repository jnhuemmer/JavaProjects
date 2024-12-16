package FinalProject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;


public class MetaDataEntry extends DataEntry
{
	private final int age;
	
	public MetaDataEntry(String key, String data, String header, int age)
	{
		super(key, data, header);
		this.age = age;
	}
	
	public int getAge()
	{
		return age;
	}
	
	// Loop through excel file and create meta data objects for each line
	public static List<MetaDataEntry> readMetaDataTable(String filepath) throws Exception
	{
		BufferedReader fileContent = new BufferedReader(new FileReader(new File(filepath)));
		List<MetaDataEntry> dataList = new ArrayList<MetaDataEntry>();
		
		// Get the header
		String firstLine = fileContent.readLine();		
		
		// Reads through whole file
        for (String nextLine = fileContent.readLine(); nextLine != null; nextLine = fileContent.readLine())
        {
        	// Does nothing if a line is empty or just contains "\n"
        	if (nextLine.isEmpty())
        	{
        		;
        	}
        	
        	else
        	{
        		// First column has the key for this table
        		String keyEntry = nextLine.substring(0, nextLine.indexOf(','));
        		String[] splitEntry = nextLine.split(",");
        		       
        		// Try block used to catch NA entries or otherwise non-number entries
        		try
        		{
        			int tempAge = Integer.valueOf(splitEntry[24]); // Age is in the 25th column
            		dataList.add(new MetaDataEntry(keyEntry, nextLine.substring(nextLine.indexOf(',') + 1, nextLine.length()), firstLine, tempAge));
        		}
        		catch (NumberFormatException e)
        		{
        			System.out.println(splitEntry[24] + " is not a number");
        		}
        		
        	}
        }
        fileContent.close();
        return dataList;
	}
	
	@Override
	public String toString()
	{
		return "This is a meta data entry with a key value of " + this.getKey() + " with an age value of " + this.getAge();
	}
	
	public static void main(String[] args)
	{
		
		System.out.println("Data entry class");
		MetaDataEntry metaDataObject = new MetaDataEntry("MetaDataKey" ,"MetaDataData", "MetaDataHeader", 420);
		System.out.println(metaDataObject);
	}
}
