package at.univie.ct.ga;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * load properties file
 * you can set the parameters with key and value
 *
 */
public class Configuration
{
    private Properties propertie;
    private InputStream inputFile;
    /**
     * 	initialize Configuration class
     */
    public Configuration()
    {
        propertie = new Properties();
    }
    
    /**
     * initialize Configuration class
     * @param fileName: load file name
     */
    public Configuration(String fileName)
    {
        propertie = new Properties();
        try {
            inputFile = this.getClass().getClassLoader().getResourceAsStream(fileName);
            propertie.load(inputFile);
            inputFile.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Load Error! - File does not exist!");
            ex.printStackTrace();
        } catch (IOException ex) {
            System.out.println("Failed to load the file!");
            ex.printStackTrace();
        }
    }//end ReadConfigInfo(...)
    
    /**
     * Overload function，get the value of key
     * @param key: the value of key
     * @return key: key value
     */
    public String getValue(String key)
    {
        if(propertie.containsKey(key)){
            String value = propertie.getProperty(key);
            return value;
        }
        else 
            return "";
    }//end getValue(...)
    
    /**
     * Overload function，get the value of key
     * @param fileName: the properties file name
     * @param key: the value of key
     * @return key: the value of key
     */
    public String getValue(String fileName, String key)
    {
        try {
            String value = "";
            inputFile = this.getClass().getClassLoader().getResourceAsStream(fileName);
            propertie.load(inputFile);
            inputFile.close();
            if(propertie.containsKey(key)){
                value = propertie.getProperty(key);
                return value;
            }else
                return value;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return "";
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        } catch (Exception ex) {
            ex.printStackTrace();
            return "";
        }
    }//end getValue(...)
    
   
    public static void main(String[] args)
    {
//    	Please note that the file name with a space.
        Configuration rc = new Configuration(" information.properties");// file name
        
        String path = rc.getValue("filePath");//read the value of properties file
        String mutationRate = rc.getValue("mutationRate");
        String generationMax = rc.getValue("generationMax");
        String Crossovermethode = rc.getValue("Crossovermethode");
        
        System.out.println("TTTTTTTTest " + path + " " + mutationRate + " " + generationMax + " " + Crossovermethode);

        Configuration cf = new Configuration();
        String mutationRate1 = cf.getValue(" information.properties", "mutationRate");
        System.out.println("mutationRate = " + mutationRate1);
        
        
    }//end main()
    
}//end class ReadConfigInfo
