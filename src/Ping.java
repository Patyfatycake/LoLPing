import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Ping {

	public Ping(){
		
	}
	public float getPing(String ip, int maxPackages){
		String pingCmd = "ping " + ip;
		

		Runtime runtime = Runtime.getRuntime();
		Process process;
		try {
			process = runtime.exec(pingCmd);
			BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
			
			
			//reads the outputs
			String inputLine, splitLine;
			String[] splits;
			float pingTotal=0;
			int loops=0;
			
				
				inputLine = in.readLine();
				while ((inputLine != null) && (loops<maxPackages)) {
				    if (inputLine.length() > 0) {
				    	try{
					    	splits = inputLine.toString().split("=");
					    	splitLine = splits[splits.length-1].replace("ms", "");
					    	splitLine = splitLine.replace(" ", "");
					        
					    	pingTotal+= Float.valueOf(splitLine);
					    	loops++;
				    	}catch(Exception e){
				    		e.printStackTrace();
					    }
				    }
				    inputLine = in.readLine();
				}
				
				return pingTotal/loops;
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		return -1;
	}
}
