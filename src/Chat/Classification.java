package Chat;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Random;

import weka.classifiers.bayes.NaiveBayesUpdateable;
import weka.classifiers.evaluation.Evaluation;
import weka.classifiers.lazy.IBk;
import weka.classifiers.meta.FilteredClassifier;
import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.FastVector;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.SelectedTag;
import weka.core.converters.ArffLoader;
import weka.core.converters.ArffSaver;
import weka.core.tokenizers.NGramTokenizer;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.StringToWordVector;


public class Classification {
	
	
	FilteredClassifierUpdateable classifier;
	Instances data;
	StringToWordVector stw;
	FilteredClassifierUpdateable test = new FilteredClassifierUpdateable();
	
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
	
	public void save_model(FilteredClassifierUpdateable filclas, String target){
		
		
	    ObjectOutputStream out;
		try {
		
			out = new ObjectOutputStream(new FileOutputStream(target));
		
		    out.writeObject(filclas);
		  
		    out.close();
				
		} catch (IOException e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
		}
 
		
		
	}
	
	public FilteredClassifierUpdateable load_nb_updatable(String source){
		
		
		ObjectInputStream in;
		
	try {
		//Load the File, Wrap it to FilteredClassifier
		in = new ObjectInputStream(new FileInputStream(source));
		try {
			return (FilteredClassifierUpdateable)in.readObject();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	//Return null as default
	return null;
		
		
		
	}
	
	StringToWordVector setfilter(){
		
		StringToWordVector stw = new StringToWordVector();
		try {
			
			
			stw.setIDFTransform(true);
			stw.setTFTransform(true);
			stw.setLowerCaseTokens(true);
			stw.setNormalizeDocLength(new SelectedTag(StringToWordVector.FILTER_NORMALIZE_ALL, StringToWordVector.TAGS_FILTER));
			stw.setWordsToKeep(500);
			stw.setOutputWordCounts(true);
			NGramTokenizer tokenizer = new NGramTokenizer();
			tokenizer.setNGramMinSize(1);
			tokenizer.setNGramMaxSize(3);
			stw.setTokenizer(tokenizer);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		return stw;
		
	}

	public void save_filter(){
		
		save_model(classifier, "Naive-Bayes.model");
		
	}
	public void save_data(){
		
		save_file("Big Input.arff", data);
	}
	
	
	public int predict_level(String input){
	int prediction=0;

	Instance instance = new DenseInstance(data.get(0));
	instance.attribute(0).addStringValue(input);
	
	
	try {
		
	} catch (Exception e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
		try{
	
		}
		catch(Exception e){
			e.printStackTrace();
			
		}
		data.add(instance);
	
		
		
		
		//data.add();
		
		try {
			prediction=(int) classifier.classifyInstance(instance);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return prediction;
	}
	
	public void update_classifier(Instance inst){
		
		
		try {
			classifier.updateClassifier(inst);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	
	void build_data(){
		data=load_file("New Input.arff");
		
		data.setClassIndex(1);
		try {
			stw.setInputFormat(data);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			data=Filter.useFilter(data, stw);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		save_file("New_Trained_Data.arff",data);
		
		
	}
	
	
	void build_new(){
		

		
		
		
		int delimiter= data.numInstances()/10;
		
		Instances trainer= new Instances(data, delimiter);
		trainer.setClassIndex(0);
		for(int i=0;i<delimiter;i++){
			trainer.add(new DenseInstance(data.instance(0)));
			
			
		}
		
		

		try {
			classifier.buildClassifier(trainer);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		   Evaluation eval=null;

	

		
		    for(int i=delimiter;i<data.numInstances();i++){
		    	try {
					classifier.updateClassifier(data.instance(i));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    	
		    	try {
					eval = new Evaluation(data);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    	try {
					eval.evaluateModel(classifier, data);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

//		    	save_model(classifier, "Test.model");
		    }
		    
		    
	}
	
	
	public Classification(){
		stw=setfilter();
//		data = load_file("New_Trained_Data.arff");
//		data.setClassIndex(0);
//		try {
//			stw.setInputFormat(data);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		classifier = load_nb_updatable("Naive-Bayes.model");
		classifier= new FilteredClassifierUpdateable();
		IBk knn = new IBk();
		knn.setKNN(4);
		classifier.setClassifier(knn);
		classifier.setFilter(stw);
//		classifier = load_nb_updatable("Naive-Bayes.model");
		


	}
	
	

	
	public static void main(String[]args){
		
		Classification c = new Classification();
		c.build_data();
		//c.build_new();
		
		
		
		
	}
	
	
}
