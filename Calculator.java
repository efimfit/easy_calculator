import java.io.BufferedReader;
import java.io.InputStreamReader;

class Sign {
    String sign;
    public Sign(String s){
        sign = s;
    }

    boolean checkSign(){
        if (sign.length() != 1)
            return true;
        if (sign.indexOf('+') == -1 && sign.indexOf('-') == -1 &&
                sign.indexOf('*') == -1 && sign.indexOf('/') == -1)
            return true;
        return false;
    }

    void display(Number a, Number b) throws Exception{
        int result = 0;

        if (sign.equals("+"))
            result = a.getX() + b.getX();
        else if (sign.equals("-"))
            result = a.getX() - b.getX();
        else if (sign.equals("*"))
            result = a.getX() * b.getX();
        else if (sign.equals("/"))
            result = a.getX() / b.getX();
        if (result < 1 && !a.getIsArabic())
            throw new Exception("rome numbers cant be less than 1");
        if (!a.getIsArabic())
            convertoToRome(result);
        else
            System.out.println(result);
    }

    void convertoToRome(int result){
        while (result > 0){
            if (result >= 50) {
                System.out.print("L");
                result -= 50;
            }
            else if (result >= 40){
                System.out.print("XL");
                result -= 40;
            }
            else if (result >= 10){
                System.out.print("X");
                result -= 10;
            }
            else {
                if (result == 9) {
                    System.out.print("IX");
                    result = 0;
                }
                else if (result >= 5){
                    System.out.print("V");
                    result -= 5;
                }
                else if (result == 4) {
                        System.out.print("IV");
                        result -= 4;
                    }
                else {
                    System.out.print("I");
                    result -= 1;
                }
            }
        }
        System.out.println();
    }
}


class Number {
    private int x;
    private boolean is_arabic = true;

    public Number(String s) throws Exception{
        if (s.startsWith("I") || s.startsWith("V") || s.startsWith("X")) {
            is_arabic = false;
            for (int i = 0; i < s.length(); i++){
                if ((s.charAt(i) != 'I' && s.charAt(i) != 'V' && s.charAt(i) != 'X') || i == 4)
                    throw new Exception();
                if (s.charAt(i) == 'X'){
                    if (x == 1)
                        x = 9;
                    else if (x == 0)
                        x = 10;
                    else
                        throw new Exception();
                }
                if (s.charAt(i) == 'I'){
                    if (x >= 9 || x == 4 || x == 3)
                        throw new Exception();
                    x += 1;
                }
                if (s.charAt(i) == 'V') {
                    if (x == 1)
                        x = 4;
                    else if (x == 0)
                        x = 5;
                    else
                        throw new Exception();
                }
            }
        }
        else
            x = Integer.parseInt(s);
    }

    boolean checkRange(){
        return (x < 1 || x > 10);
    }

    public int getX(){
        return x;
    }
    public boolean getIsArabic(){
        return is_arabic;
    }
}

public class Calculator {
    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            while(true){
                String [] arr = reader.readLine().split(" ");
                if (arr.length != 3)
                    throw new Exception("wrong number of arguments");
                Number a = new Number(arr[0]);
                Number b = new Number(arr[2]);
                Sign sign = new Sign(arr[1]);

                if (sign.checkSign())
                    throw new Exception("wrong sign");
                if (a.getIsArabic() != b.getIsArabic())
                    throw new Exception("different number systems");
                if (a.checkRange() || b.checkRange())
                    throw new Exception("numbers are out of range (1 : 10)");
                sign.display(a, b);
            }
        }
        catch (NumberFormatException e) {
            System.out.println("wrong variable type (not INTEGER)");
        }
        catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("wrong number of arguments");
        }
       catch (Exception e) {
           System.out.println(e.getMessage());
       }
        System.exit(1);
    }
}