class Partition{
    static Node partitionIt(Node n, int partInt){
        //If null LinkedList, return null
        if (n == null)
            return null;
        //If only have one element, return the Node
        if (n.next == null)
            return n;

        Node ptrL = n;
        Node ptrR = n;

        while (n != null){
            Node curr = n.next;
            if (n.data < partInt){
                n.next = ptrL;
                ptrL = n;
            }
            else{
                ptrR.next = n;
                ptrR = n;
            }
            n = curr;
        }
        // Need to clear the end of the node as null to avoid cycling
        ptrR.next = null;
        return ptrL;
    }


    static class Node{
        int data;
        Node next;

        Node(int data){
            this.data = data;
            next = null;
        }
    }

    public static void main(String[] args){
        Node n = new Node(3);
        n.next = new Node(5);
        n.next.next = new Node(8);
        n.next.next.next = new Node(5);
        n.next.next.next.next = new Node(10);
        n.next.next.next.next.next = new Node(2);
        n.next.next.next.next.next.next = new Node(1);

        Node answ = partitionIt(n, 5);
        while(answ != null){
            System.out.print(answ.data + " ");
            answ = answ.next;
        }
    }
}

