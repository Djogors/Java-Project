package backend;
    
import java.time.LocalDateTime;

public class Sumario {
    private String titulo;
    private String tipo;
    private String texto;
    private LocalDateTime dataHora;
    private RepositorioPresenca presencas;
    
    public Sumario(){}
    
    public Sumario(String novoTitulo, String novoTipo, String novoTexto, 
            LocalDateTime novaDataHora, RepositorioPresenca novasPresencas){
        titulo = novoTitulo;
        tipo = novoTipo;
        texto = novoTexto;
        dataHora = novaDataHora;
        presencas = novasPresencas;
    }
    
    public String getTitulo(){
        return titulo;
    }
    
    public void setTitulo(String novoTitulo){
        titulo = novoTitulo;
    }
    
    public String getTipo(){
        return tipo;
    }
    
    public void setTipo(String novoTipo){
        tipo = novoTipo;
    }
    
    public String getTexto(){
        return texto;
    }
    
    public void setTexto(String novoTexto){
        texto = novoTexto;
    }
    
    public LocalDateTime getDataHora(){
        return dataHora;
    }
            
    public void setDataHora(LocalDateTime novaDataHora){
        dataHora = novaDataHora;
    }
    
    public RepositorioPresenca getPresencas(){
        return presencas;
    }
    
    public void setPresencas(RepositorioPresenca novasPresencas) {
        presencas = novasPresencas;
    }
    
    @Override
    public String toString(){
        return "Titulo=" + titulo + ",Tipo=" + tipo + ",Texto=" + texto +
                ",DataHora=" + dataHora + ",Alunos presentes=" + presencas.toString();
    }
}
