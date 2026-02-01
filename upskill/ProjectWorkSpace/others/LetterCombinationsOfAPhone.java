import java.util.ArrayList;
import java.util.List;
public class LetterCombinationsOfAPhone{
    public static void main(String[] args) {
        DigitClass dc=new DigitClass();
        List<String>res=dc.letterCombinations("23");
        System.out.println(res);
        
    }
}

class DigitClass {
    public List<String> letterCombinations(String digits) {

        return backtrack(0,digits);


        
    }

    public List<String> backtrack(int n,String digits){

        if(n==digits.length()){
          List<String> res = new ArrayList<>(List.of(""));
          return res;
            
        }

        List<String>res=new ArrayList<>();

        int num=digits.charAt(n)-'0';

        char[] ch=getChar(num);

        for(int i=0;i<ch.length;i++){

            List<String>cur=backtrack(n+1,digits);
            for(String it:cur){
                res.add(ch[i]+""+it);
            }




        }
        return res;


    }

    public char[] getChar(int n) {
    switch (n) {
        case 2:
            return new char[] {'a', 'b', 'c'};
        case 3:
            return new char[] {'d', 'e', 'f'};
        case 4:
            return new char[] {'g', 'h', 'i'};
        case 5:
            return new char[] {'j', 'k', 'l'};
        case 6:
            return new char[] {'m', 'n', 'o'};
        case 7:
            return new char[] {'p', 'q', 'r', 's'};
        case 8:
            return new char[] {'t', 'u', 'v'};
        case 9:
            return new char[] {'w', 'x', 'y', 'z'};
        default:
            return new char[0]; // or throw exception
    }
}

}