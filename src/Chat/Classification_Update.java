package Chat;

import java.io.File;
import java.io.IOException;

import weka.core.Instances;
import weka.core.converters.ArffLoader;
import weka.core.converters.ArffSaver;

public class Classification_Update {

	
	Instances dataset;
	
	public void save_file(String path, Instances instances){
		ArffSaver saver = new ArffSaver();
		try {
			saver.setInstances(instances);
			saver.setFile(new File(path));
			saver.writeBatch();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}
	
	public Instances load_file(String path)
	{
		ArffLoader loader = new ArffLoader();
		
		try {
			loader.setFile(new File(path));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			return loader.getDataSet();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	
		
	}
	
	
	
	
	
	
	
	public void update_classifier(){
		
		
		
		
		
		
	}
	
}
