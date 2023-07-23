import java.io.IOException;

public class StackApplication {
    private Stack theStack;
    private String input;
    private String output = "";

    public StackApplication(String in) {
        input = in;
        int stackSize = input.length();
        theStack = new Stack(stackSize);
    }

    public String doTrans() {
        for(int j = 0; j < input.length(); j++) {
            char ch = input.charAt(j);
            switch(ch) {
                case '+':
                case '-':
                    gotOper(ch, 1);
                    break;
                case '*':
                case '/':
                    gotOper(ch, 2);
                    break;
                case '(':
                    theStack.push(ch);
                    break;
                case ')':
                    gotParen(ch);
                    break;
                default:
                    output = output + ch;
                    break;
            }
        }
        while (!theStack.isEmpty())
            output = output + theStack.pop();
        System.out.println(output);
        return output;
    }

    public void gotOper(char opThis, int prec1) {
        while (!theStack.isEmpty()) {
            char opTop = theStack.pop();
            if (opTop == '(') {
                theStack.push(opTop);
                break;
            }
            else {
                int prec2;
                if (opTop == '+' || opTop == '-')
                    prec2 = 1;
                else
                    prec2 = 2;
                if(prec2 < prec1) {
                    theStack.push(opTop);
                    break;
                }
                else
                    output = output + opTop;
            }
        }
        theStack.push(opThis);
    }

    public void gotParen(char ch) {
        while(!theStack.isEmpty()) {
            char chx = theStack.pop();
            if (chx == '(')
                break;
            else
                output = output + chx;
        }
    }

    public static void main(String[] args) throws IOException{
        String input = "1+2*4/5-7+3/6";
        String output;
        StackApplication theTrans = new StackApplication(input);
        output = theTrans.doTrans();
        System.out.println("Postfix is " + output + '\n');
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