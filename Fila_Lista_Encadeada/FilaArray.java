package TAD_FILA;

public class FilaArray implements Fila {

    private int tam;
    private no top;
    private no end;

    public FilaArray(){
        this.tam = 0;
    }

    @Override
    public boolean isEmpty() {
       
        if(tam == 0) return true;
        return false;

    }

    @Override
    public Object first() throws EFilaVazia {
        if(this.isEmpty()) throw new EFilaVazia("A fila está vazia. Impossível de retornar o primeiro elemento.");
        
        return top.getElement();
    }

    @Override
    public Object end() throws EFilaVazia{
        if(this.isEmpty()) throw new EFilaVazia("A fila está vazia. Impossível de retornar o último elemento.");

        return end.getElement();
    }

    @Override
    public void enqueue(Object o) {

        if(this.isEmpty()){
            no novo = new no(); 
            this.novo.setElement(o);
            this.top = novo;
            this.end = novo;
            this.tam++;

        } else{
            no next = new no();
            next.setElement(o);
            this.end.setNext(next);
            this.end = next;
            this.tam++;

        }
        
        
    }

    @Override
    public Object dequeue() throws EFilaVazia {

        if(this.isEmpty()) throw new EFilaVazia("A fila está vazia. Impossível de remover um elemento dela!");

        Object send = this.top.getElement();
        
        if(tam==1){
            this.top = null;
            this.end = this.top;
        } else{
            no aux = this.top.getNext();
            this.top.setNext(null);
            this.top = aux;
        }

        tam--;
        return send;
    }
    
}
