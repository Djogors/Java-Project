package backend;

import java.util.ArrayList;
import java.util.Collection;

public class RepositorioPresenca {
    
    private Collection<Presenca> lista;
    
    //Construtor1
    public RepositorioPresenca() {
        lista = new ArrayList<>();
    }
    
    //Construtor2
    public RepositorioPresenca(int capacidade) {
        lista = new ArrayList<>(capacidade);
    }
    
    //Construtor3
    public RepositorioPresenca(Collection<Presenca> presencas) {
        lista = new ArrayList<>(presencas);
    }
    
    //Inserir uma só presenca
    public void inserir(Presenca presenca) {
        lista.add(presenca);
    }
    
    //Inserir várias presencas
    public void inserir(Collection<Presenca> presencas) {
        lista.addAll(presencas);
    }
    
    //Numero presencas
    public int getNumeroPresencas() {
        return lista.size();
    }
    
    //Retorna a lista de presencas
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for(Presenca p : lista){
            if(p.getPresente() == true){
                sb.append("{");
                sb.append(p.getAluno().getNum());
                sb.append("}");
            }
        }
        sb.append("]");
        return sb.toString();
    }
    
    //Retorna uma String no formato [{nomeAluno1}{nomeAluno2}...{nomeAlunoN}]
    public String toStringName(){
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for(Presenca p : lista){
            sb.append("{");
            sb.append(p.toString());
            sb.append("}");
        }
        sb.append("]");
        return sb.toString();
    }
    
    public Presenca findAlunoNum(int num){
        for(Presenca p : lista){
            if(p.getAluno().getNum() == num)
                return p;
        }
        return null;
    }
}
