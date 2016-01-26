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
			try {
				inputLine = in.readLine();
				inputLine = in.readLine();
				while ((inputLine != null) && (loops+1<maxPackages)) {
				    if (inputLine.length() > 0) {
				    	splits = inputLine.toString().split("=");
				    	splitLine = splits[splits.length-1].replace("ms", "");
				    	splitLine = splitLine.replace(" ", "");
				    	System.out.println(loops+1);
				    	pingTotal+=Float.valueOf(splitLine);
				    	loops++;
				    }
				    inputLine = in.readLine();
				}
				System.out.println("Ping: "+pingTotal/loops);
				return pingTotal/loops;
			} catch (IOException e) {
				
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		return -1;
	}
}
