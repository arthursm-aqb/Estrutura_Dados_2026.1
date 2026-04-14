
public class FilaArray implements Fila {

    private int first, last, tam, capacidade;
    private Object[] fila;
    


    public FilaArray(int capacidade){
        this.first = 0;
        this.last = 0;
        this.tam = 0;
        this.capacidade = capacidade;
        fila = new Object[capacidade];
    }

    @Override
    public boolean isEmpty() {
        if(tam==0) return true;
        else return false;
    }

    @Override
    public Object first() throws EFilaVazia {
      
        if(this.isEmpty()) throw new EFilaVazia("A Fila está vazia. Impossível retornar o primeiro elemento da fila!");

        return this.fila[first];

    }

    @Override
    public Object end() throws EFilaVazia {

        if(this.isEmpty()) throw new EFilaVazia("A Fila está vazia. Impossível retornar o último elemento da fila!");

        return this.fila[(this.last - 1 + this.capacidade) % this.capacidade];
    }

    public int size() {
        return this.tam;
    }

    @Override
    public void enqueue(Object o) {

        if(this.size()==this.capacidade){
            int newCapacity = this.capacidade*2;
            Object[] newF = new Object[newCapacity];
            
            int aux = this.first;
            for(int i = 0; i<this.size(); i++){
                newF[i] = this.fila[aux];
                aux = (aux+1) % this.capacidade;
            }

            this.capacidade = newCapacity;
            this.fila = newF;
            this.first = 0;
            this.last = this.size();
        }

        this.fila[this.last] = o;
        this.last = (this.last+1) % this.capacidade;
        this.tam++;

    }

    @Override
    public Object dequeue() throws EFilaVazia {
        
        if(this.isEmpty()) throw new EFilaVazia("A fila está vazia. Impossível remover um objeto dela");

        Object aux = this.first();
        this.first = (this.first+1) % this.capacidade;
        this.tam--;
        return aux;
        
    }

    
    
}
