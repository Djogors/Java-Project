package backend;

import java.time.LocalDate;

public class RegenteUC extends Professor{
    
    private UC uc;
    
    public RegenteUC(){}
    
    public RegenteUC(String novoUsername, String novaPassword,
            String novoNome, int novoNum, 
            LocalDate novaData, RepositorioUC novasUcs, UC novaUC){
        super(novoUsername, novaPassword, novoNome, novoNum, novaData, novasUcs);
        uc = novaUC;
    }
    
    public UC getUC(){
        return uc;
    }
    
    public void setUC(UC novaUC){
        uc = novaUC;
    }
}
