class StackofPlates{

    //Implement a data structure of Set of Stacks
    //push() and pop() should behave identically to a single stack
    //
    //Follow up: Implement a function popAt(intindex) wihch performs a pop operation on a specific sub-stack
    int size;
    int stackSize;
    StackX aStack, bStack, cStack;

    StackofPlates(int maxSize){
        stackSize = maxSize / 3 + 1;
        aStack = new StackX(stackSize);
        bStack = new StackX(stackSize);
        cStack = new StackX(stackSize);
    }

    int pop(){
        if (!cStack.isEmpty()){
            size--;
            return cStack.pop();
        }
        else if (!bStack.isEmpty()){
            size--;
            return bStack.pop();
        }
        else if (!aStack.isEmpty()){
            size--;
            return aStack.pop();
        }
        else
            System.out.println("No element to pop");
        
        return -1;
    }

    void push(int n){
        if (!aStack.isFull()){
            aStack.push(n);
            size++;
        }
        else if (!bStack.isFull()){
            size++;
            bStack.push(n);
        }
        else if (!cStack.isFull()){
            size++;
            cStack.push(n);
        }
        else
            System.out.println("Stack Overflow!");
    }

}
    
class StackX{
    int[] stackArray;
    int size;
    int maxSize;

    StackX(int size){
        stackArray = new int[maxSize];
        size = 0;
    }

    public int pop(){
        if (!isEmpty())
            return stackArray[size--];
    }

    public void push(int data){
        stackArray[size++] = data;
    }

    public boolean isEmpty(){
        return (size == 0);
    }

    public boolean isFull(){
        return (size == maxSize);
    }

    public int size(){
        return size;
    }
}
