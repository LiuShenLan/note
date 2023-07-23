import java.io.IOException;

public class StackApplication2 {
    private String input;
    private String output;

    public StackApplication2(String in) {
        input = in;
    }

    public String doRev() {
        int stackSize = input.length();
        Stack theStack = new Stack(stackSize);
        for(int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);
            theStack.push(ch);
        }
        output = "";
        while(!theStack.isEmpty()) {
            char ch = theStack.pop();
            output = output + ch;
        }
        return output;
    }

    public static void main(String[] args) throws IOException{
        String input = "www.github.com";
        String output;
        StackApplication2 theReverser = new StackApplication2(input);
        output = theReverser.doRev();
        System.out.println("反转前: " + input);
        System.out.println("反转后: " + output);
    }

    class Stack {
        private int maxSize;
        private char[] stackArray;
        private int top;
    
        public Stack(int s) {
            maxSize = s;
            stackArray = new char[maxSize];
            top = -1;
        }
    
        public void push(char j) {
            top++;
            stackArray[top] = j;
        }
    
        public char pop() {
            top--;
            return stackArray[top + 1];
        }
    
        public char peek() {
            return stackArray[top];
        }
    
        public boolean isEmpty() {
            return top == -1;
        }
    }
}