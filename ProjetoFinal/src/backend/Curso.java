package backend;

import java.io.Serializable;

public class Curso implements Serializable{
    private String designacao;
    private DiretorCurso diretor;
    private RepositorioAluno alunos;
    private RepositorioUC ucs;
    
    public Curso(){}
    
    public Curso(String novaDesignacao, RepositorioAluno novosAlunos, RepositorioUC novasUcs){
        designacao = novaDesignacao;
        diretor = null;
        alunos = novosAlunos;
        ucs = novasUcs;
    }
    
    public String getDesignacao(){
        return designacao;
    }
    
    public void setDesginacao(String novaDesignacao){
        designacao = novaDesignacao;
    }
    
    public DiretorCurso getDiretor(){
        return diretor;
    }
    
    public void setDiretor(DiretorCurso novoDiretor){
        diretor = novoDiretor;
    }
    
    public RepositorioAluno getAlunos(){
        return alunos;
    }
    
    public void setAlunos(RepositorioAluno novosAlunos){
        alunos = novosAlunos;
    }
    
    public RepositorioUC getUcs(){
        return ucs;
    }
    
    public void setUcs(RepositorioUC novasUcs){
        ucs = novasUcs;
    }
    
    @Override
    public String toString(){
        return "designacao=" + designacao + ",diretor=" + diretor.getNum() +
                ",alunos=" + alunos.toStringName() + 
                ",ucs=" + ucs.toStringName();
    }
}
