package backend;

import java.io.Serializable;
import java.time.LocalDate;

public class Professor extends Utilizador implements Serializable{

    private String nome;
    private int num;
    private LocalDate inicioFuncoes;
    private RepositorioUC ucs;
    
    public Professor(){}
    
    public Professor(String novoUsername, String novaPassword,
            String novoNome, int novoNum, 
            LocalDate novaData, RepositorioUC novasUcs){ 
        super(novoUsername, novaPassword);
        nome = novoNome;
        num = novoNum;
        inicioFuncoes = novaData;
        ucs = novasUcs;
    }
    
    public String getNome(){
        return nome;
    }
    
    public void setNome(String novoNome){
        nome = novoNome;
    }
    
    public int getNum(){
        return num;
    }
    
    public void setNum(int novoNum){
        num = novoNum;
    }
    
    public LocalDate getData(){
        return inicioFuncoes;
    }
    
    public void setData(LocalDate novaData){
        inicioFuncoes = novaData;
    }
    
    public RepositorioUC getUcs(){
        return ucs;
    }
    
    public void setUcs(RepositorioUC novasUcs){
        ucs = novasUcs;
    }

    @Override
    public String toString() {
        return "nome=" + nome + ",num=" + num + ",inicioFuncoes=" +
                inicioFuncoes + ",ucs=" + ucs.toStringName();
    }
}
