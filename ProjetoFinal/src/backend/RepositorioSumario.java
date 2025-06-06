package backend;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RepositorioSumario implements Serializable{
    
    private List<Sumario> lista;
    
    //Construtor1
    public RepositorioSumario(){
        lista = new ArrayList<>();
    }
    
    //Construtor2
    public RepositorioSumario(int capacidade){
        lista = new ArrayList<>(capacidade);
    }
    
    //Construtor3
    public RepositorioSumario(List<Sumario> sumarios){
        lista = new ArrayList<>(sumarios);
    }
    
    //Inserir um só sumario
    public void inserir(Sumario sumario) {
        lista.add(sumario);
    }
    
    //Inserir um conjunto de sumarios
    public void inserir(List<Sumario> sumarios) {
        lista.addAll(sumarios);
    }
    
    //Remover um sumario
    public void remover(Sumario s){
        lista.remove(s);
    }
    
    //Numero de sumarios
    public int getNumeroSumarios(){
        return lista.size();
    }
    
    //Retorna lista de sumarios
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("[\n");
        for(Sumario s : lista){
            sb.append("{");
            sb.append(s.toString());
            sb.append("}\n");
        }
        sb.append("]");
        return sb.toString();
    }
    
    //Retorna uma String no formato [{TituloSum1}{TituloSum2}...{TituloSumn}]
    public String toStringName(){
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for(Sumario s : lista){
            sb.append("{");
            sb.append(s.getTitulo());
            sb.append("}");
        }
        sb.append("]");
        return sb.toString();
    }
    
    public String toStringTipoUC(String tipo){
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for(Sumario s : lista){
            if(s.getTipo().equals(tipo)){
                sb.append("{");
                sb.append(s.toString());
                sb.append("}");
            }
        }
        sb.append("]");
        return sb.toString();
    }
    
    public String listarAssiduidade(int num){
        Presenca p;
        StringBuilder sb = new StringBuilder();
        for(Sumario s : lista){
            sb.append("dia:").append(s.getDataHora()).append(",");
            p = s.getPresencas().findAlunoNum(num);
            sb.append(p.toStringAluno()).append("\n");
        }
        return sb.toString();
    }
    
    public Sumario find(String t){
        for(Sumario s : lista){
            if(s.getTitulo().equals(t)) return s;
        }
        return null;
    }
}
