package TAD_vetor;

public class vetorCircular implements vetor {
    
    private Object[] circular;
    private int tam, capacidade, inicio;

    public vetorCircular(int capacidade){
        
        this.capacidade = capacidade;
        this.tam = 0;
        this.inicio = 0;
        this.circular = new Object[capacidade];
    }

    public Object elemAtRank(int rank){
        if(rank < 0 || rank >= this.tam) throw new IndexOutOfBoundsException("Rank inválido!");
        return this.circular[(this.inicio + rank) % this.capacidade];
    }

    public Object replaceAtRank(int rank, Object o){
        if(rank < 0 || rank >= this.tam) throw new IndexOutOfBoundsException("Rank inválido!");

        Object retorno = this.elemAtRank(rank);

        this.circular[(this.inicio + rank) % this.capacidade] = o;

        return retorno;
    }

    public void insertAtRank(int rank, Object o){
        if(rank < 0 || rank >= this.tam) throw new IndexOutOfBoundsException("Rank inválido!");
        if(this.size()==this.capacidade) throw new RuntimeException("Vetor cheio");


        if(rank<(this.size()/2)){

            int novoInicio = (this.inicio - 1 + this.capacidade) % this.capacidade;
            for(int i = 0; i<rank; i++) circular[(this.inicio + i - 1) % this.capacidade] = circular[(this.inicio + i ) % this.capacidade];
            this.inicio = novoInicio;
        } else{
            for(int i = this.size(); i>rank; i--) this.circular[(this.inicio+i) % this.capacidade] = this.circular[(this.inicio + i - 1) % this.capacidade];
        }

        circular[(this.inicio + rank) % this.capacidade] = o;
        this.tam++;
    }
    
    public Object removeAtRank(int rank);

    public int size(){
        return this.tam;
    }

    public boolean isEmpty(){
        return this.size() == 0;
    }

}
