//import java.text.ParseException; 
import java.util.*;
  
class assignment1 { 

	static int process[] = {1, 2, 3, 4};
	static int n = process.length; 
	static int at[] = new int[n];
	static int bt[] = new int[n];
	static int ct[] = new int[n];
	static int wt[] = new int[n];
	static int tat[] = new int[n]; 
	static int flag[] = new int[n]; 
	static int total_wt = 0, total_tat = 0;
	static int st=0, tot=0;
	static int inactive = 0;
	static float avgwt = 0;
    static float avgtat = 0;
	static Scanner sc= new Scanner(System.in);
	
//_______________________________Input code____________________________________________________________	
	
	static void inputs() {
		
		System.out.print("\nEnter the arrival time: ");
        for (int i=0; i<n; i++) {
        	at[i]= Integer.parseInt(sc.next());		
        }

        System.out.print("Enter the burst time: ");
        for (int i=0; i<n; i++) {
        	bt[i]= Integer.parseInt(sc.next());		
        }
	}
	
//_______________________________FCFS code____________________________________________________________	

	static void findWaitingTime() { 
       wt[0] = 0; 
        for (int i = 1; i < n; i++) { 
            wt[i] = tat[i] - bt[i];
        } 
    } 
  
    static void findTurnAroundTime() { 
        for (int i = 0; i < n; i++) { 
            tat[i] = ct[i] - at[i]; 
        } 
    } 
    
    static void findCompletionTime() {
    	for(int i=0; i<n; i++) {
    		if(at[i]>st) {
    			inactive = inactive + at[i] - st;
    			st=at[i];
    		}
    		ct[i] = st + bt[i];
    		st+=bt[i];
    	}
    }
  
    static void fcfs() {
    	
    	int at[] = new int[n];
		int bt[] = new int[n];
		int ct[] = new int[n];
		int wt[] = new int[n];
		int tat[] = new int[n];   
		total_wt = 0; total_tat = 0;
		st=0; tot=0;
		inactive = 0;
		avgwt = 0;
	    avgtat = 0;
    	
    	findCompletionTime();
    	findTurnAroundTime(); 
    	findWaitingTime(); 
  
        System.out.printf("Process | AT | BT | CT | TAT | WT \n"); 
  
        for (int i = 0; i < n; i++) { 
            total_wt += wt[i]; 
            total_tat += tat[i]; 
            System.out.printf("  P%d ", (i + 1));
            System.out.printf("      %d", at[i]);
            System.out.printf("    %d ", bt[i]);
            System.out.printf("   %d ", ct[i]);
            System.out.printf("   %d", tat[i]); 
            System.out.printf("    %d\n", wt[i]);
        }
        
        avgwt = (float)total_wt /(float) n; 
        avgtat = (float)total_tat / (float)n; 
        System.out.printf("Average waiting time = %f", avgwt); 
        System.out.printf("\nAverage turn around time = %f ", avgtat); 
        System.out.printf("\nGantt chart:\n");
        processes();
        System.out.printf("\nInactive time duration of CPU: %d", inactive);
        System.out.printf("\n\n");
    } 
    
    static void executing_process(int p) {
    	System.out.printf(" P%d ", process[p]);
    }
    
    static void print_time(int t) {
    	System.out.printf(" %d ", t);
    }
    
    static void processes() {
        print_time(0);
        for(int i=0; i<n; i++) {
	    	executing_process(i);
	    	print_time(ct[i]);
        }
    }
    
//_______________________________SJF(Preemptive) code_________________________________________________
    
    static void sjf() {
    	
    	int at[] = new int[n];
		int bt[] = new int[n];
		int ct[] = new int[n];
		int wt[] = new int[n];
		int tat[] = new int[n]; 
		int flag[] = new int[n];  
		total_wt = 0; total_tat = 0;
		st=0; tot=0;
		inactive = 0;
		avgwt = 0;
	    avgtat = 0;
    	
    	int k[] = new int[n];
    	 while(true){
 	    	int min=99,c=n;
 	    	if (tot==n)
 	    		break;
 	    	
 	    	for (int i=0;i<n;i++) {
 	    		k[i] = bt[i];
 	    		if ((at[i]<=st) && (flag[i]==0) && (bt[i]<min)){	
 	    			min=bt[i];
 	    			c=i;
 	    		}
 	    	}
 	    	
 	    	if (c==n) st++;
 	    	else{
 	    		bt[c]--;
 	    		st++;
 	    		if (bt[c]==0)
 	    		{
 	    			ct[c]= st;
 	    			flag[c]=1;
 	    			tot++;
 	    		}
 	    	}
 	    }
 	    
 	    for(int i=0;i<n;i++){
 	    	tat[i] = ct[i] - at[i];
 	    	wt[i] = tat[i] - k[i];
 	    	avgwt+= wt[i];
 	    	avgtat+= tat[i];
 	    }
		System.out.printf("Process | AT | BT | CT | TAT | WT \n");
		for(int i=0;i<n;i++){
			avgwt+= wt[i];
			avgtat+= tat[i];
			System.out.printf("  P%d ", (i + 1));
            System.out.printf("      %d", at[i]);
            System.out.printf("    %d ", bt[i]);
            System.out.printf("   %d ", ct[i]);
            System.out.printf("   %d", tat[i]); 
            System.out.printf("    %d\n", wt[i]);
		}
		System.out.printf("\nAverage turn around time = %f", (float)(avgtat/n));
		System.out.printf("\nAverage waiting time = %f", (float)(avgwt/n));
		System.out.printf("\nGantt chart:\n");
        processes();
		System.out.printf("\n\n");
    }
    
//_______________________________main code____________________________________________________________
 
    public static void main(String[] args){ 
    	
    	int exit=0;
    	while (exit==0) {
    		
    		System.out.printf("Select: \n1. FCFS \n2. SJF(Preemptive) \n5. Exit");
    		String option = sc.next(); 
    		
    		switch(option) {
    		case "1": 	inputs();
			            fcfs(); 
			            break;
    			
    		case "2": 	inputs();
    					sjf();
    					break;
    			
    		case "5": 	exit=1;
    					break;
    			
    		default: 	System.out.printf("Please enter valid option!\n");
    					break;
    		}
    	}
    } 
} 