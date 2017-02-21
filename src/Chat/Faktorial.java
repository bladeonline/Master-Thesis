package Chat;


public class Faktorial {

	
	int faktor(int number){
		if(number==0) return 1;
		else return number * faktor(number-1);
		
		
	}
	
	
	public static void main(String []args){
		
		
		System.out.println(new Faktorial().faktor(6));
	}
	
}
