import java.util.ArrayList;
import java.util.List;
public class InsertInterval {

   
    public static void main(String[] args) {
         int[][] arr = {
                {1, 5}
                
        };
        int[] newInterval = {0, 0};
        InsertInterval obj = new InsertInterval();
        SolutionInsertInterval sol = new SolutionInsertInterval();
        int[][] result = sol.insert(arr, newInterval);
        for (int[] interval : result) {
            System.out.println("[" + interval[0] + ", " + interval[1] + "]");
        }
    }

}

class SolutionInsertInterval {
     public int[][] insert(int[][] intervals, int[] newInterval) {

        int n=intervals.length;
        int x=newInterval[0];
        int y=newInterval[1];
        boolean merge=false;
        List<int[]>list=new ArrayList<>();
        int nx=-1;
        for(int i=0;i<n;i++){

            int x1=intervals[i][0];
            int y1=intervals[i][1];

            
            if(!merge){
           
                    if(nx==-1&&x<x1&&y<x1){

                        list.add(new int[]{x,y});
                        list.add(new int[]{x1,y1});
                        merge=true;


                    }else if(x>=x1&&y<=y1){
                         list.add(new int[]{x1,y1});
                        merge=true;
                    }
                    
                    else if(nx==-1&&x <=y1){
                        nx=Math.min(x,x1);
                    }else if(nx!=-1){
                        if(y<x1){
                            list.add(new int[]{nx,y});
                            list.add(new int[]{x1,y1});
                            merge=true;

                        }else if(y<=y1){

                            list.add(new int[]{nx,y1});
                            merge =true;

                        }
                    }else{

                        list.add(new int[]{x1,y1});

                    }

            }
            else{
                list.add(new int[]{x1,y1});
                
            }
        }
        if(merge==false){
            if(nx!=-1){
                int y1=intervals[n-1][1];
                list.add(new int[]{nx,Math.max(y1,y)});
            }else{
                list.add(new int[]{x,y});
            }
        }
        int size=list.size();
        int[][]res =new int[size][2];
        for(int i=0;i<size;i++){
            int[] cur=list.get(i);
            res[i]=cur;
        }
        return res;
        
    }
}