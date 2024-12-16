package FinalProject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;


public class DataEntry 
{
	private final String key;
	private final String data;
	private final String header;
	
	public DataEntry(String key, String data, String header)
	{
		this.key = key;
		this.data = data;
		this.header = header;
	}
	
	public String getData()
	{
		return this.data;
	}
	
	public String getHeader()
	{
		return this.header;
	}
	
	public String getKey()
	{
		return this.key;
	}
	
	@Override
	public String toString()
	{
		return "This is a data entry with a key value of " + this.getKey();
	}
	
	// Loop through excel file and create data objects for each line
	public static List<DataEntry> readDataTable(String filepath) throws Exception
	{
		BufferedReader fileContent = new BufferedReader(new FileReader(new File(filepath)));
		List<DataEntry> dataList = new ArrayList<DataEntry>();
		
		// Get the header
		String firstLine = fileContent.readLine();
		System.out.println(firstLine);
		
		
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
        		// First column has key for this table
        		String keyEntry = nextLine.substring(0, nextLine.indexOf(','));
        		dataList.add(new DataEntry(keyEntry, nextLine.substring(nextLine.indexOf(',') + 1, nextLine.length()), firstLine));
        	}
        }
        	
        fileContent.close();
        return dataList;
	}
	public static void main(String[] args)
	{
		
		System.out.println("Data entry class");
		
		DataEntry DataEntryObject = new DataEntry("DataEntryKey" ,"DataEntryData", "DataEntryHeader");
		System.out.println(DataEntryObject);
	}
}
