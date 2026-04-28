package TAD_vetor;

public class vetorCircular implements vetor {
    
    private int[] circular;
    private int tam, capacidade, inicio;

    public vetorCircular(int capacidade){
        
        this.capacidade = capacidade;
        this.tam = 0;
        this.inicio = 0;
        this.circular = new int[capacidade];
    }

    public Object elemAtRank(int rank){
        if(rank < 0 || rank >= this.tam) throw new IndexOutOfBoundsException("Rank inválido!");
        return circular[(this.inicio + rank) % this.capacidade];
    }

    public Object replaceAtRank(int rank, Object o);
    public void insertAtRank(int rank, Object o);
    public Object removeAtRank(int rank);

    public int size(){
        return this.tam;
    }

    public boolean isEmpty(){
        return this.size() == 0;
    }

}
