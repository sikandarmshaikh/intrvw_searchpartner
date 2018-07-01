package org.intrvw.searchpartner;

import java.util.ArrayList;
import java.util.Random;

public class Conveyer {
    
    public  String[] conveyerBelt;
    private int[]    conveyerState; // state of the conveyerBelt --> three states: empty, with component, with product
    private int      L;
    private int      nC;
    
    ArrayList beltOutput = new ArrayList();
    
    public Conveyer(int L, int nC){
        
        this.L    = L;
        this.nC   = nC;
        conveyerBelt      = new String[L];
        
        for(int i=0; i<L; i++){
            conveyerBelt[i] = null; // initial conveyerBelt is empty
        }
        
        conveyerState = new int[L];
        
        init();
        
    }
    
    private void init(){
        
        // add new component to the conveyerBelt
        Random randomGenerator = new Random();
        int value = randomGenerator.nextInt(nC+1);
        if (value>0){
            value = value + 64; // ASCII Set: A=65, B=66, etc
            conveyerBelt[0] = Character.toString((char)value);
        } else {
            conveyerBelt[0] = null;
        }
        updateBeltState();
        
    }
    
    private void updateBeltState(){
        
        for(int i=0; i<L; i++){
            if(conveyerBelt[i]==null){
                conveyerState[i] = 3;     // conveyerBelt empty
            } else if("P".equals(conveyerBelt[i])){
                conveyerState[i] = 2;     // belt with product
            } else conveyerState[i] = 1;  // conveyerBelt with component
        }
        
    }
    
    public void updateBelt(String[] B){
        System.arraycopy(B, 0, conveyerBelt, 0, L);  
        updateBeltState();
    }
    
    public void slideBelt(){
        
        String[] beltTemp = new String[L];
        
        if(conveyerBelt[L-1]!=null){
            beltOutput.add(conveyerBelt[L-1]);
        }        
        for(int i=1; i<L; i++){
            beltTemp[i] = conveyerBelt[i-1];
        }
        System.arraycopy(beltTemp, 0, conveyerBelt, 0, L);
        init();
        
    }
    
    public int[] getBeltState(){
        return conveyerState;
    }
    
}
