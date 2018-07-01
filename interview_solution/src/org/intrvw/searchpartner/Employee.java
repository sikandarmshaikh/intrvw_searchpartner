package org.intrvw.searchpartner;

public class Employee {
    
    public String[][] eL; // employee left of the belt
    public String[][] eR; // employee right of the belt
    
    private int[] indL; // presence of employees on left
    private int[] indR; // presence of employees on right
    
    private int[] stateL; // state left
    private int[] stateR; // state right
    
    private int L;  // belt length
    private int nC; // number of components
    
    public String[] belt;
    
    public Employee(int nC, String[] belt){
        
        L  = belt.length;
        this.nC = nC;
        
        this.belt = new String[L];
        System.arraycopy(belt, 0, this.belt, 0, L);
        
        eL = new String[L][nC];
        eR = new String[L][nC];
        
        indL = new int[L];
        indR = new int[L];
        
        stateL = new int[L];
        stateR = new int[L];
        
        init();
        
    }
    
    private void init(){
        
        for(int i=0; i<L; i++){
            
            for(int j=0; j<nC; j++){
                eL[i][j] = null;
                eR[i][j] = null;
            }
            
            indL[i] = 1; // a employee is present (1) or not (0)
            indR[i] = 1; // (by default they are all present)
            
            stateL[i] = 1; // state 1 --> hand free
            stateR[i] = 1; // state 2 --> hands busy with components
                           // state 3 --> product ready
            
        }
        
    }
    
    public void updateState(int position){
        
        if(position==0){
            for(int i=0; i<L; i++){
                if("P".equals(eL[i][0])){
                    stateL[i] = 3;    // if first hand has P  --> state 3
                } else if(eL[i][nC-1]==null){
                    stateL[i] = 1;    // if last hand is free --> state 1
                } else stateL[i] = 2; // otherwise both hands are busy
            }
        }
        
        if(position==1){
            for(int i=0; i<L; i++){
                if("P".equals(eR[i][0])){
                    stateR[i] = 3;
                } else if(eR[i][nC-1]==null){
                    stateR[i] = 1;
                } else stateR[i] = 2;
            }
        }
        
    }
    
    public void doWork(int[] action, int position){
        
        String[][] w = new String[L][nC];
        int        k;
                
        if(position==0){
            for(int i=0; i<L; i++){
                System.arraycopy(eL[i], 0, w[i], 0, nC);
            }
        } else {
            for(int i=0; i<L; i++){
                System.arraycopy(eR[i], 0, w[i], 0, nC);
            }
        }
                
        for(int i=0; i<L; i++){
            if(action[i]==1){         // TAKE: take component from belt into free hand
                k=0;
                while(k>=0){
                    if(w[i][k]==null){
                        w[i][k] = belt[i];
                        k = -100;                        
                    }
                    k+=1;
                }
                belt[i] = null;
            }
            if(action[i]==3){         // BUILD: build product
                for(int j=1; j<nC; j++){
                    w[i][j] = null;
                }
                w[i][0] = "P";
            }
            if(action[i]==4){         // PLACE: place product on belt and empty hands
                belt[i] = "P";
                for(int j=0; j<nC; j++){
                    w[i][j] = null;
                }
                
            }                        
        }
        
        if(position==0){
            for(int i=0; i<L; i++){
                System.arraycopy(w[i], 0, eL[i], 0, nC);
            }
        } else {
            for(int i=0; i<L; i++){
                System.arraycopy(w[i], 0, eR[i], 0, nC);
            }
        }
        
        updateState(position);
        
    }
    
    public void updateBelt(String[] belt){
        System.arraycopy(belt, 0, this.belt, 0, L);
    }
    
    public int[] getStateL(){
        return stateL; 
    }
    public int[] getStateR(){
        return stateR; 
    }
    
    public int[] getWorkersL(){
        return indL; 
    }
    public int[] getWorkersR(){
        return indR; 
    }
    
}
