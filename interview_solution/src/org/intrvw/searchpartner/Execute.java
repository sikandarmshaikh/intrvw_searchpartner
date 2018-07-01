package org.intrvw.searchpartner;

public class Execute {
    
    public   Conveyer   belt;
    private  Employee workers;
    private  AssemblyView   draw;
    private  Process action;
    
    public Execute(Conveyer belt, Employee workers, AssemblyView draw){
        
        this.belt    = belt;
        this.workers = workers;
        this.draw    = draw;
        
    }
    
    private void init(){
        
        System.out.println("---------------- t = 0 ---------------------\n");
               
        draw.test(belt, workers); 
        
        // create obeject action based on actual states
        action = new Process(workers.getStateL(), workers.getStateR(), workers.getWorkersL(), workers.getWorkersR(), belt.getBeltState());

    }
    
    public void runSimulation(int time){
        
        init();
        
        for(int t=0; t<time; t++){
            
            action.generateAction(workers.eL, belt.conveyerBelt, 0); // generate actions for workers on the left
            action.generateAction(workers.eR, belt.conveyerBelt, 1); // generate actions for workers on the right
            action.conflictCheck();                           // check presence of conflicts
        
            workers.doWork(action.processL, 0); // workers on the left execute their job
            workers.doWork(action.processR, 1); // workers on the right execute their job
        
            belt.updateBelt(workers.belt);  // update belt
        
            draw.test(belt, workers);
            System.out.println("---------------- t = " + (t+1) + " ---------------------\n");
        
            belt.slideBelt();       // slide the belt
            workers.updateBelt(belt.conveyerBelt); // workers update their knowledge of belt
            action.updateState(workers.getStateL(), workers.getStateR(), belt.getBeltState()); // update action with new states of belt and workers                  
            
            draw.test(belt, workers);
            
        }
        
        System.out.println("----------------------------------------------\n");
        
    }
    
    public static void main(String args[]){
        
        // Parameters: length of belt, number of components to assemble
        // (internally we can also set a variable number of workers at the belt)
        
        int BeltLength = 3;   // belt length
        int numberComp = 6;   // number of components to assemble
        
        Conveyer myBelt   = new Conveyer(BeltLength, numberComp);        
        Employee myWork = new Employee(numberComp, myBelt.conveyerBelt);        
        AssemblyView myDraw   = new AssemblyView(BeltLength, numberComp);
        
        // Create and run simulation
        Execute mySimulation = new Execute(myBelt, myWork, myDraw);
        int simTime = 60;
        mySimulation.runSimulation(simTime);
        
        //----------------------------------------------------------------------
        
        // Computation of frequencies of components. Final components are stored
        // in variable myBelt.beltOutput
        
        char[]   setComponents = new char[numberComp];
        int[]    quantity      = new int[numberComp];
        int      product       = 0;
        int      value;
        for(int i=0; i<numberComp; i++){            
            value = 65+i;                   // ASCII Set: A=65, B=66, etc
            setComponents[i] = (char)value; // contains all different components
            quantity[i]      = 0;
        }       
        
        Object p1;
        String p2;
        char   p3;
        
        for(int i=0; i<myBelt.beltOutput.size(); i++){
            p1 = myBelt.beltOutput.get(i);
            p2 = (String)p1;
            p3 = p2.charAt(0);
            for(int j=0; j<numberComp; j++){
                if(p3==setComponents[j]){
                    quantity[j] += 1;
                }
            }
            if(p3=='P'){
                product += 1; 
            }
        }
        
        for(int i=0; i<numberComp; i++){
            System.out.println(setComponents[i] + " --> " + quantity[i]);
        }
        System.out.println("P" + " --> " + product + "\n");
                
    }
    
}
