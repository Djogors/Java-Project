package backend;

public class Departamento {
    
    private final RepositorioProfessor listaProfessores = new RepositorioProfessor();
    private final RepositorioCurso listaCursos = new RepositorioCurso();
    private final RepositorioAluno listaAlunos = new RepositorioAluno();
    private final RepositorioUC listaUCs = new RepositorioUC();
    
    public RepositorioProfessor getProfessores(){
        return listaProfessores;
    }
    
    public RepositorioCurso getCursos(){
        return listaCursos;
    }
    public RepositorioAluno getAlunos(){
        return listaAlunos;
    }
    public RepositorioUC getUCs(){
        return listaUCs;
    }
}
