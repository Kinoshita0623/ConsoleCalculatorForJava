import java.util.ArrayList;

public class Main {
    public static void main(String[] args){
        Calculator calc = new Calculator();
        //ArrayList<String> s = calc.decode("");
        int ans = calc.calculator("10+20*2-5*2-(5-3)*2");

        System.out.println(ans);



    }
}
