import java.io.IOException;

public final class Test {
	int number = 0;
	String color;
	public String swing(){
		return "blue";
	}

	public static void main(String[] args) throws Exception {
		Test s = new Test();
		//uou p = s.new uou();
		//System.out.println(p.print());
		//p.work();
		/*
		testThree test3 = new testThree();
		try{
			test3.run();
		}
		catch(IOException e){
			System.out.println(e.getMessage());
			
		}*/
		
		chamath n = s.new chamath();
		n.start();
		manuka d = s.new manuka();
		d.start();
		me ts = s.new me();
		ts.these();
		

	}
	public abstract class test{
		//Test y = new Test();
		int gameNumber;
		//number = 0;
		boolean status;
		public String print(){
			//System.out.println(y.swing());
			return "this has done";
			
		}
		public int showNum(){
			return number+=1;
		}
		public String swing(int number){
			return "number";
			
		}
		public abstract void these();
	}
	public class uou implements testTwo{

		@Override
		public void work() {
			// TODO Auto-generated method stub
			System.out.println(value);
			
			
		}
		public void start(){
			
		}
		@Override
		public int sell(String a) {
			// TODO Auto-generated method stub
			return 0;
		}
		
	}
	public class t extends test{

		@Override
		public void these() {
			// TODO Auto-generated method stub
			
		}

		
		
		
	}
	
	public class me extends test{
		
		public void these(){
			System.out.println("this is chamath");
			
		}
	}
	public static class R{
		
	}
	static class T{
		
	}
	public class manuka{
		
		public void start(){
			System.out.println("this");
		}
	}
	public class chamath extends manuka{
		public void start(int s){
			System.out.println("chamath");
		}
	}
	

}

