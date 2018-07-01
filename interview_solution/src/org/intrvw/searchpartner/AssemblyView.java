package org.intrvw.searchpartner;

public class AssemblyView {
    
    // For each time point we plot the components taken by the employees and the belt.
    // E.g. two components (A,B); belt of length L=3
    //
    // piece 1       A A A   <--- first  'hand' of employees 1,2,3 (on the left of the belt)
    // piece 2       - - -   <--- second 'hand' of employees 1,2,3 (on the left of the belt)
    //  BELT         A = P   <--- positions 1,2,3 of the belt
    // piece 1       B A A   <--- first  'hand' of employees 1,2,3 (on the right of the belt)
    // piece 2       - - -   <--- second 'hand' of employees 1,2,3 (on the right of the belt)
    //
    // For each time there are two plots:
    // 1) as soon as the belt has slided
    // 2) after employees have performed their actions
        
    private Conveyer   mybelt;
    private Employee myemployee;
    
    private int L;
    private int nC;
    
    public AssemblyView(int L, int nC){
        
        this.L  = L;
        this.nC = nC;
        
    }
    
    public void test(Conveyer B, Employee W){
        
        mybelt   = B;
        myemployee = W;
        
        // ---------------------------------------------------------------------
     
        for(int i=0; i<nC; i++){
            System.out.print("piece " + (i+1) + "       ");
            for(int j=0; j<L; j++){
                if(W.eL[j][i]==null){
                    System.out.print("-" + " ");
                } else{
                    System.out.print(W.eL[j][i] + " ");
                }                    
            }
            System.out.println();
        }
        
        System.out.print(" BELT         ");
        for(int i=0; i<L; i++){
            if(B.conveyerBelt[i]==null){
                System.out.print("=" + " ");
            } else {
                System.out.print(B.conveyerBelt[i] + " ");
            }            
        }
        System.out.println();
        
        for(int i=0; i<nC; i++){
            System.out.print("piece " + (i+1) + "       ");
            for(int j=0; j<L; j++){
                if(W.eR[j][i]==null){
                    System.out.print("-" + " ");
                } else{
                    System.out.print(W.eR[j][i] + " ");
                } 
            }
            System.out.println();
        }
        System.out.println();
        
    }
    
}
