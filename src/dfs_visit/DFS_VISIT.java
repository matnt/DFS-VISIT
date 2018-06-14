/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dfs_visit;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 *
 * @author Mat Nguyen
 */
public class DFS_VISIT {
    Set<Integer> V;
    Map<Integer, Set<Integer>> A;    
    int n;
    int m;
    char[] color; // lưu màu để xác định đỉnh đã được tới thăm, xử lý hay chưa
    int[] p; // lưu vết đường đi
    int t;
    int[] d;
    int[] f;
    int count; // số thành phần liên thông
    int[] icc; //icc[v] = 2; đỉnh v thuộc thành phần liên thông thứ 2
   
    public DFS_VISIT(){
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        DFS_VISIT dfs = new DFS_VISIT();
        dfs.input();
        dfs.DFS();
        dfs.printResult2();
    }
    
    public void input(){
        Scanner in = new Scanner(System.in);
        n = in.nextInt();
        V = new HashSet<Integer>();
        A = new HashMap<Integer, Set<Integer>>();
        color = new char[n + 1];
        p = new int[n + 1];
        d = new int[n + 1];
        f = new int[n+ 1];
        icc = new int[n + 1];
        t = 0;
        count = 0;
        for(int i = 0; i < n; i++){
            int v = in.nextInt();
            V.add(v);
            A.put(v, new HashSet<Integer>());
            
        }
        m = in.nextInt();
        
        for(int i = 0; i < m; i++){
            int u = in.nextInt();
            int v = in.nextInt();
            // u kề với v
            // v kề với u
            A.get(u).add(v);
            A.get(v).add(u);
        }
        
        
    }
    public void printData(){
        for(int v: V){
            System.out.print("node " + v + ": ");
            for(int u: A.get(v))
                System.out.print(u + " ");
            System.out.println();
        }
    }
    public void printResult(){
        for(int i: V){
            System.out.print(d[i] + " ");
        }
        System.out.println("");
        for(int i: V){
            System.out.print(f[i] + " ");
        }
        System.out.println("");
        for(int i: V){
            System.out.print(p[i] + " ");
        }
        System.out.println("");
    }
    public void printResult2(){
        System.out.println("Số thành phần liên thông: " + count);
        for(int i = 0; i < count; i++){
            System.out.print("Thành phần liên thông thứ " + (i+ 1) +" là: ");
            for(int v: V){
                if(icc[v] == i + 1){
                    System.out.print(v + " ");
                }
            }
            System.out.println("");
        }
    }
    
    public void DFS(){
        count = 0;
        for(int v: V){
            color[v] = 'W';
            p[v] = -1;
        }
        for(int v: V){
            if(color[v] == 'W'){
                count++;
                DFS_visit(v);
            }
        }
    }
    
    public void DFS_visit(int u){
        t++;
        d[u] = t;
        color[u] = 'G';
        for(int v: A.get(u)){
            if(color[v] == 'W'){
                p[v] = u; // lưu vết đường đi
                DFS_visit(v);
            }
        } 
        color[u] = 'B';
        t++;
        f[u] = t;
        icc[u] = count;
//        int count_v = 0;
//        for(int v: A.get(u)){
//            if(color[v] == 'B'){
//                count_v++;
//                
//            }
//            if(count_v == A.get(u).size())
//                count++;
//        }
        
    }
    
    
}
