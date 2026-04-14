package TAD_FILA;

public class no {
    private Object element;
    private no next;

    public void setElement(Object o){
        this.element = o;
    }

    public Object getElement(){
        return this.element;
    }

    public void setNext(no n){
        this.next = n;
    }

    public no getNext(){
        return this.next;
    }
}
