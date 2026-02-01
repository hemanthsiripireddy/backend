import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TriangleSolution {
    public static void main(String[] args) {

            SolutionBitAdd sol=new SolutionBitAdd();
            String a="11";
            String b="1";
            String res=sol.addBinary(a, b);
            System.out.println(res);


    }
    
}


class SolutionBitAdd {
    public String addBinary(String a, String b) {
        StringBuilder sb=new StringBuilder();

        int i=a.length()-1,j=b.length()-1;

        int bit=0;

        while(i>=0&&j>=0){
            int aBit=a.charAt(i)-'0';
            int bBit=b.charAt(j)-'0';
            int sum=(aBit+bBit+bit);
            int rem=sum%2;
            sb.append(rem+"");
            bit=sum/2;
            j--;
            i--;
        }
        while(i>=0){
             int aBit=a.charAt(i)-'0';
             int sum=(aBit+bit);
             int rem=sum%2;
            sb.append(rem+"");
            bit=sum/2;
             i--;


        }
         while(j>=0){
             int bBit=b.charAt(j)-'0';
            int sum=(bBit+bit);
            int rem=sum%2;
            sb.append(rem+"");
            bit=sum/2;
             j--;   

        }
         if(bit!=0)
            sb.append(bit+"");
        return sb.reverse().toString();
        
    }
}