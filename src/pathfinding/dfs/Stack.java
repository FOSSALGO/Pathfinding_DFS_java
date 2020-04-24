package pathfinding.dfs;
public class Stack<T extends Object> { 
    private int MAX;
    private T[] data;
    private int top;
     
    public Stack(int size) {
        this.MAX= size;
        this.data = (T[]) new Object[this.MAX];
        this.top = -1;
    }
    
    public Stack() {
        this.MAX = 1000;
        this.data = (T[]) new Object[this.MAX];
        this.top = -1;
    }
 
    public void push(T entry){
        if(this.isFull()){
            this.increaseStackCapacity();
        }
        //System.out.println("Adding: "+entry);
        this.data[++top] = entry;
    }
 
    public T pop() throws Exception {
        if(this.isEmpty()){
            throw new Exception("Stack is empty. Can not remove element.");
        }
        T entry = this.data[top--];
        return entry;
    }
     
    public T peek() {
        return data[top];
    }
 
    private void increaseStackCapacity(){
         
        @SuppressWarnings("unchecked")
        T[] newStack = (T[]) new Object[this.MAX*2];
        for(int i=0;i<this.MAX;i++){
            newStack[i] = this.data[i];
        }
        this.data = newStack;
        this.MAX = this.MAX*2;
    }
     
    public boolean isEmpty() {
        return (top == -1);
    }
 
    public boolean isFull() {
        return (top == this.MAX - 1);
    }
    
    public int getTop(){
        return top;
    }
    
    public T[] getElements(){
        T[] newData = (T[]) new Object[this.top];
        System.arraycopy(this.data, 0, newData, 0, this.top);
        return newData;
    }
    
}