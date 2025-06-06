package backend;

import java.io.Serializable;

public class UC implements Serializable{
    private String nome;
    private RegenteUC regente;
    private RepositorioProfessor equipa;
    private RepositorioSumario sumarios;
    
    public UC(){}
    
    public UC(String novoNome, RepositorioProfessor novaEquipa, RepositorioSumario novosSumarios){
        nome = novoNome;
        regente = null;
        equipa = novaEquipa;
        sumarios = novosSumarios;
    }
    
    public String getNome(){
        return nome;
    }
    
    public void setNome(String novoNome){
        nome = novoNome;
    }
    
    public RegenteUC getRegente(){
        return regente;
    }
    
    public void setRegente(RegenteUC novoRegente){
        regente = novoRegente;
    }
    
    public RepositorioProfessor getEquipa(){
        return equipa;
    }
    
    public void setEquipa(RepositorioProfessor novaEquipa){
        equipa = novaEquipa;
    }
    
    public RepositorioSumario getSumarios(){
        return sumarios;
    }
    
    public void setSumarios(RepositorioSumario novosSumarios) {
        sumarios = novosSumarios;
    }
    
    public void inserirSumario(Sumario sum){
        sumarios.inserir(sum);
    }
    
    @Override
    public String toString(){
        return "nome=" + nome + ",regente=" + regente.getNum() + ",equipa=" + 
                equipa.toStringName() + ",sumarios=" + sumarios.toStringName();
    }
    
    public boolean equals(UC uc2){
        return this.nome.equals(uc2.getNome());
    }
}
