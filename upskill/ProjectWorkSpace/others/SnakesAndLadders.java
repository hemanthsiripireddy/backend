import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class SnakesAndLadders{
    public static void main(String[] args) {

        int[][] board =  {
    {-1, 83, -1, 46, -1, -1, -1, -1, 40, -1},
    {-1, 29, -1, -1, -1, 51, -1, 18, -1, -1},
    {-1, 35, 31, 51, -1, 6, -1, 40, -1, -1},
    {-1, -1, -1, 28, -1, 36, -1, -1, -1, -1},
    {-1, -1, -1, -1, 44, -1, -1, 84, -1, -1},
    {-1, -1, -1, 31, -1, 98, 27, 94, 74, -1},
    {4, -1, -1, 46, 3, 14, 7, -1, 84, 67},
    {-1, -1, -1, -1, 2, 72, -1, -1, 86, -1},
    {-1, 32, -1, -1, -1, -1, -1, -1, -1, 19},
    {-1, -1, -1, -1, -1, 72, 46, -1, 92, 6}
};


        SnakeSolution sol=new SnakeSolution();
        int res=sol.snakesAndLadders(board);
        System.out.println(res);

        
    }

}

class SnakeSolution {
    Map<Integer,int[]>map;
    Map<Integer,Integer>dp;
    Set<Integer>used;
    public int snakesAndLadders(int[][] board) {

        int n=board.length;
        int max=1;
        map=new HashMap<>();
        dp=new HashMap<>();
        used=new HashSet<>();
        int i=n-1;
        while(max<=n*n){
            if(max<=n*n){
                for(int j=0;j<n;j++){
                     map.put(max++,new int[]{i,j});
                }
            }
            i--;
            if(max<=n*n){
                for(int j=n-1;j>=0;j--){
                     map.put(max++,new int[]{i,j});
                }
            }
            i--;
        }

        return move(1,n,board);

        //return 4;
        
    }

    public int move(int cur,int n,int[][] board){

        if(cur==n*n) return 0;

        if(dp.containsKey(cur)) return dp.get(cur);

        if(used.contains(cur)) return -1;

        used.add(cur);

        int res=Integer.MAX_VALUE;

        for(int i=cur+1;i<=Math.min(cur+6,n*n);i++){
            int[] in=map.get(i);
            int x=in[0];
            int y=in[1];
            int next=i;
            int lOrs=board[x][y];
            if(lOrs!=-1 ){
                next=lOrs;
            }
        
            int re=move(next,n,board);
            if(re!=-1)
            res=Math.min(res,1+re);

        }
        if(res==Integer.MAX_VALUE) res=-1;
        dp.put(cur,res);
        return res;

    }
}