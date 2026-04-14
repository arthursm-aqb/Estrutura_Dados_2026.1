
public class FilaArray implements Fila {

    private int first, last, tam, capacidade;
    private Object[] fila;
    private boolean reverso;
    


    public FilaArray(int capacidade){
        this.first = 0;
        this.last = 0;
        this.tam = 0;
        this.capacidade = capacidade;
        this.fila = new Object[capacidade];
        this.reverso = false;
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

        int coef = this.reverso ? -1 : 1;

        return this.fila[(this.last - coef + this.capacidade) % this.capacidade];
    }

    public int size() {
        return this.tam;
    }

    @Override
    public void enqueue(Object o) {

        int coef = this.reverso ? -1 : 1;

        if(this.size()==this.capacidade){
            int newCapacity = this.capacidade*2;
            Object[] newF = new Object[newCapacity];
            
            int aux = this.first;
            for(int i = 0; i<this.size(); i++){
                newF[i] = this.fila[aux];
                aux = (aux+coef+this.capacidade) % this.capacidade;
            }

            this.capacidade = newCapacity;
            this.fila = newF;
            this.first = 0;
            this.last = this.size();
            this.reverso = false;
            coef = 1;
        }

        this.fila[this.last] = o;
        this.last = (this.last+coef+this.capacidade) % this.capacidade;
        this.tam++;

    }

    @Override
    public Object dequeue() throws EFilaVazia {
        
        if(this.isEmpty()) throw new EFilaVazia("A fila está vazia. Impossível remover um objeto dela");

        int coef = this.reverso ? -1 : 1;
        Object send = this.first();
        this.first = (this.first+coef+this.capacidade) % this.capacidade;
        this.tam--;

        if(this.capacidade>1 && this.tam<=(this.capacidade/3)){
            int newCapacity = capacidade/2;
            Object[] newF = new Object[newCapacity];
            
            int aux = this.first;
            for(int i = 0; i<this.size(); i++){
                newF[i] = this.fila[aux];
                aux = (aux+coef+this.capacidade) % this.capacidade;
            }

            this.capacidade = newCapacity;
            this.fila = newF;
            this.first = 0;
            this.last = this.size();
            this.reverso = false;
        }

        return send;
        
    }

    public void reverse(){
        int coef = this.reverso ? -1 : 1;
        int aux = (this.first-coef + this.capacidade) % this.capacidade;
        this.first = (this.last - coef + this.capacidade) % this.capacidade;
        this.last = aux;
        this.reverso = !this.reverso;
    }
    
}
