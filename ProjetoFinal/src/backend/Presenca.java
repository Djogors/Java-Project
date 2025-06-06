package backend;

public class Presenca {
    
    private Aluno aluno;
    private boolean presente;
    
    public Presenca(){}
    
    public Presenca(Aluno novoAluno, boolean novoPresente){
        aluno = novoAluno;
        presente = novoPresente;
    }
    
    public Aluno getAluno(){
        return aluno;
    }
    
    public void setAluno(Aluno novoAluno){
        aluno = novoAluno;
    }
    
    public boolean getPresente(){
        return presente;
    }
    
    public void setPresente(boolean novoPresente){
        presente = novoPresente;
    }
    
    @Override
    public String toString() {
        return "Aluno=" + aluno.getNum() + ",Presente=" + presente;
    }
    
    public String toStringAluno(){
        if(presente == true) return "presente";
        else return "não presente";
    }
}
