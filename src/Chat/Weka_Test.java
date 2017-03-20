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
import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.bayes.NaiveBayesUpdateable;
import weka.classifiers.evaluation.Evaluation;
import weka.classifiers.functions.SGD;
import weka.classifiers.functions.SGDText;
import weka.classifiers.functions.SMO;

import weka.classifiers.meta.FilteredClassifier;
import weka.classifiers.meta.MultiClassClassifierUpdateable;
import weka.classifiers.trees.J48;
import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.FastVector;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ArffLoader;
import weka.core.converters.ArffSaver;
import weka.core.converters.CSVLoader;
import weka.core.converters.LibSVMLoader;
import weka.knowledgeflow.steps.Classifier;
import weka.experiment.InstanceQuery;
import weka.filters.Filter;
import weka.filters.supervised.attribute.AttributeSelection;
import weka.filters.unsupervised.attribute.Add;
import weka.filters.unsupervised.attribute.Remove;
import weka.filters.unsupervised.attribute.StringToNominal;
import weka.filters.unsupervised.attribute.StringToWordVector;
import libsvm.*;
import weka.classifiers.functions.supportVector.*;
import weka.classifiers.lazy.IBk;
import weka.classifiers.lazy.KStar;




public class Weka_Test {
	
	Instances training;
	FilteredClassifier[] classifier;
	
	
	
	ArrayList<Integer> levels = new ArrayList<Integer>();
	
	
	

	
	public Instances load_csv(String path){
		
		
	    CSVLoader loader = new CSVLoader();
	    try {
			loader.setSource(new File(path));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    try {
			return  loader.getDataSet();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			return null;
		}
		
		
	}
	
	//Save Instance
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
	
	//Save a Filtered Classifier(Model)
	public void save_model(FilteredClassifier filclas, String target){
		
		
	    ObjectOutputStream out;
		try {
			System.out.println("geht");
			out = new ObjectOutputStream(new FileOutputStream(target+".dat"));
			System.out.println("geht");
		    out.writeObject(filclas);
		    System.out.println("geht");
		    out.close();
				
		} catch (IOException e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
		}
 
		
		
	}
	
	public void save_nb_updatable(NaiveBayesUpdateable model, String path){
		
		 ObjectOutputStream out;
			try {
			
				out = new ObjectOutputStream(new FileOutputStream(path+".dat"));
				
			    out.writeObject(model);
			  
			    out.close();
					
			} catch (IOException e) {
				// TODO Auto-generated catch block
				
				e.printStackTrace();
			}
		
		
		
		
		
		
		
	}
	
	public NaiveBayesUpdateable load_nb_updatable(String source){
		
		
		ObjectInputStream in;
		
	try {
		//Load the File, Wrap it to FilteredClassifier
		in = new ObjectInputStream(new FileInputStream(source+".dat"));
		try {
			return (NaiveBayesUpdateable)in.readObject();
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
	
	
	public FilteredClassifier load_model(String source){
		
		ObjectInputStream in;
		
	try {
		//Load the File, Wrap it to FilteredClassifier
		in = new ObjectInputStream(new FileInputStream(source+".dat"));
		try {
			return (FilteredClassifier)in.readObject();
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
	
	
	
	
	public Instances read_from_db(String path, String query_string){
		
		InstanceQuery query=null;
		
		
		try {
			query = new InstanceQuery();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		query.setDatabaseURL("jdbc:sqlite:"+path);
		query.setQuery(query_string);
		
		try {
			return query.retrieveInstances();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}
	

	

	
	public void train (){
		
	
		
	ObjectInputStream in=null;
	FilteredClassifier model=null;
	try {
		in = new ObjectInputStream(new FileInputStream("Naive_Bayes_Model.dat"));
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	try {
		model = (FilteredClassifier) in.readObject();
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
	
	model.setClassifier(new NaiveBayesUpdateable());
		
		Instances training = load_file("Training.arff");
		training.setClassIndex(2);

		
		System.out.println(model.getOptions());
	
		
		
		
		
//		training.setClassIndex(training.lastIndexOf(training));
//		StringToWordVector filter = new StringToWordVector();
//		filter.setAttributeIndices("first");
		
	
		
		
		
		
	}
	

	
	
	
	
	public void create_nb() throws Exception{

		
		NaiveBayesUpdateable nb = new NaiveBayesUpdateable();
		
		Instances data = load_file("Dictionary.arff");
		data.setClassIndex(2);
		
		
		
		
		nb.buildClassifier(data);
		
		Instances eval_data = load_file("Training.arff");
		eval_data.setClassIndex(1);
		eval_data.delete(eval_data.numAttributes()-1);
		
		Evaluation eval = new Evaluation(eval_data);
		
		eval.crossValidateModel(nb, eval_data, 5, new Random(1));
		
		System.out.println(eval.toSummaryString());
		
		save_nb_updatable(nb, "Naive_Bayes_Updatable_Model");
		
		
	}

	
	public void load_nb() throws Exception{
		
	NaiveBayesUpdateable nb = load_nb_updatable("Naive_Bayes_Updatable_Model");
	
	
	 ArffLoader loader = new ArffLoader();
	 loader.setFile(new File("Training.arff"));
	 Instances training = loader.getStructure();
	 training.setClassIndex(1);
	
	 Instance trainer;
		
	 while ((trainer = loader.getNextInstance(training)) != null)
		   nb.updateClassifier(trainer);
	 
	 save_nb_updatable(nb, "Naive_Bayes_Updatable_Model_Trained");
		
	}
	
	
	public void udate_normal_nb() throws IOException{
		FilteredClassifier nb = load_model("Naive_Bayes_Model");
		
		 ArffLoader loader = new ArffLoader();
		 loader.setFile(new File("Training.arff"));
		 Instances training = loader.getStructure();
		 training.setClassIndex(1);
		
		 Instance trainer;
			
		
		
		
		
	}
	
	
	public void predict_nb(){
		NaiveBayesUpdateable nb = load_nb_updatable("Naive_Bayes_Updatable_Model_Trained");
		
		
		
		
	}
	
	public void initial_train() throws Exception{
		
		Instances training;
		Instances training_raw = load_file("Dictionary.arff");
		Remove remove = new Remove();
	
		
		
		String [] remove_options = new String [2];
		remove_options[0] = "-R";
		remove_options[1] = "3";
		remove.setOptions(remove_options);
		remove.setInputFormat(training_raw);

		training = Filter.useFilter(training_raw, remove);
		
		training.setClassIndex(1);
		
		Instances training_num;
		Instances training_num_raw=load_file("Dictionary_Numeric.arff");
		
		remove.setInputFormat(training_num_raw);
		training_num= Filter.useFilter(training_num_raw, remove);
		training_num.setClassIndex(1);
		

		StringToWordVector stw = new StringToWordVector();
		
		stw.setOptions(weka.core.Utils.splitOptions("-C -V -prune-rate 20 -L "));
		
		
		
		
		NaiveBayesUpdateable nb = new NaiveBayesUpdateable();
		
		SGD sgd = new SGD();
		String [] options = new String[2];
		options[0]="-F";
		options[1]="0";
		sgd.setOptions(options);
		//sgd.buildClassifier(training_num);
		
		MultiClassClassifierUpdateable multi = new MultiClassClassifierUpdateable();
		KStar kstar = new KStar();
		kstar.buildClassifier(training_num);
		SGDText sgdtext = new SGDText();
		
		IBk knear = new IBk();
		
	//	knear.setOptions(weka.core.Utils.splitOptions("-I -F -K 4"));
		knear.buildClassifier(training);
		
		System.out.println(knear);
		
		
		kstar.buildClassifier(training);
		
		Instances binary=null;
		
				binary=new Instances(training);
		
		Add filter;
        
      
        filter = new Add();
     
		filter.setOptions(weka.core.Utils.splitOptions("-T NUM"));
		filter.setAttributeIndex("last");
		filter.setInputFormat(binary);
		
		binary = Filter.useFilter(training, filter);
		
		
		

		SMO smo = new SMO();
		
		
		
		String convert;
		Double value=0.0;
		
		
		for (int i=0;i<training.numInstances();i++){
			convert = training.get(i).stringValue(1);
			if(convert.equals("0"))value = 0.0;
			else if(convert.equals("1"))value =1.0;
			else if(convert.equals("2"))value =2.0;
			else if(convert.equals("3"))value =3.0;
			
			binary.instance(i).setValue(2, value);
		
		}
		System.out.println(binary);
		Remove rm = new Remove();
		rm.setAttributeIndices("second");
		rm.setInputFormat(binary);
		binary=Filter.useFilter(binary, rm);
		
		System.out.println(binary);
		
//		System.out.println(binary_instance.toString());
		
		
		
		try {
			nb.buildClassifier(training);
			//sgd.buildClassifier(binary_instance);
		
			//sgd.buildClassifier(training);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
		
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream ("Naive_Bayes_Raw.dat"));
		out.writeObject(nb);
		out.close();
	}

	public void future_training() throws ClassNotFoundException, IOException{
		NaiveBayesUpdateable nb;
		ObjectInputStream in = new ObjectInputStream (new FileInputStream("Naive_Bayes_Raw.dat"));
		nb = (NaiveBayesUpdateable) in.readObject();
		
		ArffLoader load = new ArffLoader();
		load.setFile(new File ("Training.arff"));
		
		Instances training = load.getDataSet();
		System.out.println(training.toSummaryString());
		training.setClassIndex(1);
		
		
		
		Instance inst;
		
		for(int i=0;i<training.numInstances();i++){
			inst = training.get(i);
			try {
				nb.updateClassifier(inst);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		System.out.println(nb);
		
		
		
	}
	
	
	
	public void evaluation() throws Exception{
		
		Instances test = load_file("Testing.arff");
		
		Evaluation eval_1 = new Evaluation(test);
		
		
		
	}
	
	
	
	
	public void result(String s) throws Exception{
		
		
		for(int i=0;i<4;i++)
			levels.add(i);
		
	
	Instances dictionary = load_file("Dictionary.arff");
	dictionary.setClassIndex(0);
	
	
	
	StringToWordVector stw = new StringToWordVector();
	
	stw.setAttributeIndices("1");
	classifier = new FilteredClassifier[3];
	for(int i=0;i<3;i++){
		
		classifier[i]= new FilteredClassifier();
		classifier[i].setFilter(stw);
	
	}
//FilteredClassifier svm = ( FilteredClassifier ) Class.forName(
  //          "weka.classifiers.functions.LibSVM" ).newInstance();
	

	classifier[0].setClassifier(new SMO());
	classifier[1].setClassifier(new NaiveBayes());
	classifier[2].setClassifier(new J48());

	
	System.out.println("test");
	for(int i=0;i<3;i++){
		
		classifier[i].buildClassifier(dictionary);
		
		System.out.println("test: "+i);

	}
	System.out.println(classifier[0].toString());
	
	System.out.println(classifier[0].getRevision());
	save_model(classifier[0], "SVM_Model");
	save_model(classifier[0], "Naive_Bayes_Model");
	save_model(classifier[0], "J48_Model");
	
	//training = load_file("Training.arff");
	//training.setClassIndex(0);
	
	
	
//	FastVector fvNominalVal = new FastVector(4);
//	
//	for (int i=0;i<4;i++)
//		fvNominalVal.addElement("\""+i+"\"");
//	
	
//	Attribute attribute2 = new Attribute("Level", fvNominalVal);
//	Attribute attribute1 = new Attribute("text",(FastVector) null);
//	// Create list of instances with one element
//	FastVector fvWekaAttributes = new FastVector(2);
//	fvWekaAttributes.addElement(attribute1);
//	fvWekaAttributes.addElement(attribute2);
//	instances = new Instances("Prediction", fvWekaAttributes, 1);           
//	// Set class index
//	instances.setClassIndex(0);
//	// Create and add the instance
//	DenseInstance instance = new DenseInstance(2);
//	instance.setValue(attribute2, text);
//	// Another way to do it:
//	// instance.setValue((Attribute)fvWekaAttributes.elementAt(1), text);
//	instances.add(instance);
//	
	
	
//	Add filter = new Add();
//	
//	filter.setAttributeIndex("first");
//	filter.setNominalLabels("0,1,2,3");
//	filter.setAttributeName("text");
//	filter.setInputFormat(training);
//	
//	Instances filteredInstance = Filter.useFilter(training, filter);
//	
//	StringToWordVector filter2 = new StringToWordVector();
//	filter2.setAttributeIndices("first");
	
	
	
	
//	double pred = classifier[0].classifyInstance(filteredInstance.firstInstance());
//	System.out.println("Class predicted: " + training.classAttribute().value((int) pred));
//	
	
	
	

	

	
	

	
	
	
	
	
//	    CSVLoader loader = new CSVLoader();
//	    
//	    loader.setSource(new File("C:\\Test2.csv"));
//	  
//	    Instances data = loader.getDataSet();
//	
	 

	 
	    
	    

	    main main= new main(false);
	    
	
	  
	  	FastVector expression = new FastVector();
	  	
//	  	for(int i=0;i<main.expression.size();i++)
//	    expression.addElement(new Attribute(main.expression.get(i).expression, main.expression.get(i).category, main.expression.get(i).level));
//	    
//	  	
//	  	
//	  	
//	  	Instances instances = new Instances("Test", expression, 1);
//	  	instances.setClassIndex(0);
//	  	
//	    
//	    data.setClassIndex(0);
//	    StringToWordVector filter = new StringToWordVector();
//	    
//	    
//	    
//	    
////	    filter.setAttributeIndicesArray();
//	    
//
//
//	    FilteredClassifier classifier = new FilteredClassifier();
//	    classifier.setFilter(filter);
//	    classifier.setClassifier(new NaiveBayes());
//	    Evaluation eval = new Evaluation(data);
//		eval.crossValidateModel(classifier, data, 4, new Random(1));
//		System.out.println(eval.toSummaryString());
//		System.out.println(eval.toClassDetailsString());
	    
	    
	    // save ARFF
//	    ArffSaver saver = new ArffSaver();
//	    saver.setInstances(data);
//	    saver.setFile(new File("C:\test2"));
//	    saver.setDestination(new File("C:\test2"));
//	    saver.writeBatch();
		
		
//	NaiveBayes bayes = new NaiveBayes();
	
	//bayes.buildClassifier(data);
	
	
		
	
	
	//main main = new main();

		


	
	

//

		
	}
	
public static void main(String []args){
	
	try {
		Weka_Test test = new Weka_Test();
		test.initial_train();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
	
	
}
