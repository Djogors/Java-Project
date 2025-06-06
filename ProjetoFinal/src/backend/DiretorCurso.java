package backend;

import java.time.LocalDate;

public class DiretorCurso extends Professor{
    
    private Curso curso;
    
    public DiretorCurso(){}
    
    public DiretorCurso(String novoUsername, String novaPassword,
            String novoNome, int novoNum, 
            LocalDate novaData, RepositorioUC novasUcs, Curso novoCurso){
        super(novoUsername, novaPassword, novoNome, novoNum, novaData, novasUcs);
        curso = novoCurso;
    }
    
    public Curso getCurso(){
        return curso;
    }
    
    public void setCurso(Curso novoCurso){
        curso = novoCurso;
    }
}
