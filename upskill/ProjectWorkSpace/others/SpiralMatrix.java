import java.util.ArrayList;
import java.util.List;

public class SpiralMatrix {
    public static void main(String[] args) {
                int[][] arr = {
            {1, 2, 3, 4},
            {5, 6, 7, 8},
            {9, 10, 11, 12}
        };
        SolutionSpiral sm = new SolutionSpiral();
        List<Integer> result = sm.spiralOrder(arr);
        System.out.println(result);
    }
   
}
class SolutionSpiral {
    public List<Integer> spiralOrder(int[][] matrix) {

        List<Integer>res=new ArrayList<>();
        int m=matrix.length;
        int n=matrix[0].length;
        int[][] vis=new int[m][n];
        int size=m*n;
        int i=0,j=0;
        while(res.size()<size){

            while(res.size()<size){
                if(vis[i][j]==1){
                    i++;
                    j--;
                    break;
                }
                if(j==n-1){
                     vis[i][j]=1;
                     res.add(matrix[i][j]);
                     i++;
                     break;

                }
                
                vis[i][j]=1;
                res.add(matrix[i][j++]);
                
                

            }

            while(res.size()<size){

                if(vis[i][j]==1 ){
                    j--;
                    i--;
                    break;
                }

                if(i==m-1){
                    vis[i][j]=1;
                     res.add(matrix[i][j]);
                     j--;
                     break;

                }
               
                
                vis[i][j]=1;
                res.add(matrix[i++][j]);  
                
            }

            while(res.size()<size){
                 if(vis[i][j]==1 ){
                    j++;
                    i--;
                    break;
                }
                if(j==0){
                    vis[i][j]=1;
                     res.add(matrix[i][j]);
                     i--;
                     break;

                }
               
                vis[i][j]=1;
                res.add(matrix[i][j--]);  
                
            }
             while(res.size()<size){
                 if(vis[i][j]==1 ){
                    i++;
                    j++;
                    break;
                }
               if(i==0){

                 vis[i][j]=1;
                 res.add(matrix[i][j]);
                 j++;
                 break;

               }
               
                vis[i][j]=1;
                res.add(matrix[i--][j]); 
                
            }


        }

        return res;
        
    }
}