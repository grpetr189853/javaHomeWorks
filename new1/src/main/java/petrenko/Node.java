package petrenko;

/**
 * Created by grpetr189853 on 01.02.2017.
 */
public class Node {
    //содержимое текущего элемента списка
    private int element;
    //указатель на следующий элемент списка
    private Node next;

    public Node(){

    }

    public Node(int element, Node next){
        this.next=next;
        this.element=element;
    }
    //вывод содержимого текущего элемента
    public int getElement(){
        return element;
    }
    //установка содержимого для текущего элемента списка
    public void setElement(int e){
        element = e;
    }
    //получение указателя на следующий элемент списка
    public Node getNext() {
        return next;
    }
    //установка следующего элемента списка
    public void setNext(Node n) {
        next = n;
    }
}
