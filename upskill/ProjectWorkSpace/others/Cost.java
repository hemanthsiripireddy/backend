import java.math.BigInteger;
public class Cost{
    public static void main(String[] args) {
        SolutionDiv sol=new SolutionDiv(); 
        int res=sol.minAllOneMultiple(5);
        System.out.println(res);
    }
}

class SolutionDiv {
    public int minAllOneMultiple(int k) {

       

        BigInteger big = BigInteger.ONE;


        for(int i=0;i<10000;i++){

            boolean div=big.mod(BigInteger.valueOf(k))
                       .equals(BigInteger.ZERO);

            if(div){

                return   big.toString().length();
                
            }
            big = big.multiply(BigInteger.TEN).add(BigInteger.ONE);
            
        }

        return -1;

        
    }

    // public int findNoOfDigits(long num){
    //     int res=0;

    //     while(num!=0){
    //         res++;
    //         num=num/10;
    //     }

    //     return res;
    // }
}