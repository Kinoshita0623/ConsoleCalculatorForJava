import javax.swing.plaf.synth.SynthTextAreaUI;
import java.util.ArrayList;
import java.util.Stack;

public class Calculator {

    public int calculator(String formula){
        ArrayList<String> formulaList = decode(formula);
        ArrayList<String> poland = reversePoland(formulaList);

        System.out.println(poland);

        return execution(poland);
    }

    public ArrayList<String> decode(String text){

        ArrayList<String> formula = new ArrayList<>();
        String number = "";
        for(int i = 0; i < text.length(); i++){

            Character c = text.charAt(i);

            if(Character.isDigit(c)){
                number += c.toString();
            }else if(!Character.isWhitespace(c) && !number.isEmpty()){   //空白でないことが条件
                formula.add(number);
                //System.out.println("14行目のコード"+number);
                formula.add(c.toString());
                number = "";

            }else if(!Character.isWhitespace(c)){
                formula.add(c.toString());
            }
        }

        if(!number.isEmpty()){
            formula.add(number);
        }
        return formula;
    }


    private ArrayList<String> reversePoland(ArrayList<String> rawList){
        Stack<String> stack = new Stack<>();
        ArrayList<String> list = new ArrayList<>();


        for(int i = 0; i < rawList.size(); i ++){
            String text = rawList.get(i);

            //System.out.println(text);

            boolean number= isNumber(text);
            //System.out.println(isNumber);
            if(number){
                list.add(text);
                //System.out.println("数値をリストに追加");
            }/*else if(text.equals("(") || text.equals(")")){


               if(text.equals("(")){
                    brackets = true;
                }else if(text.equals(")")){
                    brackets = false;
                }


            }*/else if(!stack.empty() && orderOperator(text) > orderOperator(stack.peek()) ){

                stack.push(text);
                //System.out.println("プッシュ１:" + orderOperator(text));
            }else if(!stack.empty() && orderOperator(text) <= orderOperator(stack.peek())){
                //System.out.println("数値を取り出し" + stack.peek());
                //同等以下なのでそれ以上になるまで書き出す
                while(!stack.empty()&&orderOperator(stack.peek()) >= orderOperator(text)){
                    list.add(stack.pop());
                    //System.out.println("ポップリストに追加");
                }
                stack.push(text);
            }else if(stack.empty()){
                stack.push(text);
                //System.out.println("プッシュ２");
            }

        }
        //System.out.println(!stack.empty());
        while(!stack.empty()){
            list.add(stack.pop());
        }
        //System.out.println(rawList);
        return list;
    }

    private boolean isNumber(String text){
        boolean isNumber = false;
        for(int n = 0; n < text.length(); n++){
            Character c = text.charAt(n);
            if(Character.isDigit(c)){
                isNumber = true;
                break;
            }else{
                isNumber = false;
                break;
            }
        }
        return isNumber;
    }

    private int orderOperator(String operator){
        switch(operator){
            case "(":
                //System.out.println("カッコ");
                break;
            case ")":
                //System.out.println("カッコ閉じ");
                break;
            case "*":
                return 2;
                //System.out.println("かける");
                //break;
            case"/":
                return 2;
                //System.out.println("わる");
                //break;
            case "+":
                return 1;
                //System.out.println("たす");
                //break;
            case "-":
                return 1;
                //System.out.println("ひく");
            default:
                return 0;

        }
        return -1;

    }

    private int intOperator(String op, int[] numberList){
        switch(op){
            case "+":
                return numberList[0] + numberList[1];
            case "-":
                return numberList[0] - numberList[1];
            case "*":
                return numberList[0] * numberList[1];
            case "/":
                return numberList[0] / numberList[1];
        }
        throw new IllegalArgumentException("Operatorエラー");


    }

    private int execution(ArrayList<String> list){

        Stack<Integer> stack = new Stack<>();

        for(int i = 0; i < list.size(); i ++){
            String s = list.get(i);

            if(orderOperator(s) != 0){
                System.out.println("演算子ではなかった！！");
                int[] numbers = new int[2];
                //ArrayList<Integer> numbers = new ArrayList<>();
                for(int n = 1; n >= 0 && !stack.empty(); n --){
                    //numbers[n] = Integer.parseInt(stack.pop());
                    //numbers.add(n,stack.pop());
                    System.out.println(n);
                    numbers[n] = stack.pop();

                }
                System.out.println("中間演算式" + numbers);
                int ans = intOperator(s, numbers);
                System.out.println("中間演算結果" + ans);
                stack.push(ans);
            }else if(!s.isEmpty()){
                stack.push(Integer.parseInt(s));
            }
        }
        return stack.pop();
    }





}
