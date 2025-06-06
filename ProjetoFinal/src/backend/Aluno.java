package backend;

import java.io.Serializable;

public class Aluno implements Serializable{
    
    private int num;
    private String nome;
    private Curso curso;
    
    public Aluno(){}
    
    public Aluno (int novoNum, String novoNome, Curso novoCurso){
        num = novoNum;
        nome = novoNome;
        curso = novoCurso;
    }
    
    public int getNum(){
        return num;
    }
    
    public void setNum(int novoNum){
        num = novoNum;
    }
    
    public String getNome(){
        return nome;
    }
    
    public void setNome(String novoNome){
        nome = novoNome;
    }
    
    public Curso getCurso(){
        return curso;
    }
     
    public void setCurso(Curso novoCurso){
        curso = novoCurso;
    }
    
    @Override
    public String toString() {
        return "nome=" + nome + ",num=" + num + ",curso=" 
                + curso.getDesignacao();
    }
    
    public boolean equals(Aluno a){
        if(num == a.getNum()) return true;
        else return false;
    }
}
