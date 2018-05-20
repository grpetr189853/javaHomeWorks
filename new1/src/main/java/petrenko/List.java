package petrenko;
//Реверсирует односвязный список и выводит в консоль
/**
 * Created by grpetr189853 on 01.02.2017.
 */
public class List {
    public Node head;
    public Node tail;
    public void add(int data){
        Node a = new Node();
        a.setElement(data);
        if(head==null){
            head=a;
            tail=a;
        }else{
            a.setNext(head);
            head=a;
        }
    }
    public void printList(Node t){
        while(t!=null){
            System.out.println(t.getElement());
            t=t.getNext();
        }
    }
    public Node reverseList(Node t){
        Node first=null;
        Node second=head;
        t=head;
        while(t!=null){

            t=t.getNext();
            second.setNext(first);
            first=second;
            second=t;
        }
        t=first;
        return t;
    }
    public static void main(String[] args) {
        List l=new List();
        l.add(10);
        l.add(15);
        l.add(17);
        l.add(30);
        l.add(45);
        l.add(50);
        l.add(52);
        l.add(55);
        l.add(57);
        l.add(60);
        System.out.println("Вывод списка в нормальном порядке");
        l.printList(l.head);
        System.out.println("Вывод списка в обратном порядке");
        l.printList(l.reverseList(l.head));
    }
}
