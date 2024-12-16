package FinalProject;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.List;

public class FinalProjectMain 
{
	// Takes a set of data, a header, and an output location and produces a file
	public static void writeTable(HashMap<String, String> finalData, String header, File outputFile) throws Exception
	{		
		BufferedWriter writeOutput = new BufferedWriter(new FileWriter(outputFile));
		writeOutput.write(header + "\n");
		
		// Loop through each data entry
		for (String key : finalData.keySet())
		{
			writeOutput.write(finalData.get(key) + "\n");
		}
		outputFile.createNewFile();
		writeOutput.close();
		System.out.println("Table complete!");
	}
	
	public static void main (String [] args) throws Exception
	{
		
		// Variables
		String microbiomeFilePath = "C:\\Users\\Jacob\\Desktop\\Wormhole\\Fall_2024\\AdvancedProgramming\\FinalProject\\TableS8_T2T_KrakenUniq_BIO_Fullset.csv";
		String metadataFilePath = "C:\\Users\\Jacob\\Desktop\\Wormhole\\Fall_2024\\AdvancedProgramming\\FinalProject\\TableS9_metadata_KrakenUniq_BIO_Fullset.csv";

		List<DataEntry> microbiomeData = DataEntry.readDataTable(microbiomeFilePath);
		List<MetaDataEntry> metaData = MetaDataEntry.readMetaDataTable(metadataFilePath);

		HashMap<String, String> commonKey = new HashMap<>();
		
		
		// Find common keys
		for(MetaDataEntry i : metaData)
		{
			for(DataEntry j : microbiomeData)
			{
				if (i.getKey().equals(j.getKey()))
				{
					if (commonKey.get(j) != null)
						System.out.println("Duplicate keys");
					
					commonKey.put(i.getKey(), i.getKey() + "," + i.getAge() + "," + j.getData());
				}
			}
		}	
		
		// Get header from one of the dataEntry objects (Should be the same for each since they're all from the same data set)
		String newHeader = microbiomeData.get(0).getHeader();
		
		// Add "age" to the second column
		newHeader = newHeader.substring(0, newHeader.indexOf(',')) + ",age" + newHeader.substring(newHeader.indexOf(','), newHeader.length());
		
		// Remove "" from strings
		newHeader = newHeader.replace("\"", "");
		
		// Output
		writeTable(commonKey, newHeader, new File("C:\\Users\\Jacob\\Desktop\\outputFastaTable.csv"));
	}
}

