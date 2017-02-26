/*Sukwhan Youn
 */

import java.util.*;
import java.lang.*;

public class BinaryMaxHeap<T extends Comparable<T>> implements MaxPQ<T> 
 { 
   
  private BinaryTree<T> root; // the root node of the heap, which is of BinaryTree class. 
  private int n = 0; // the number of nodes in the heap. 
  private List<BinaryTree<T>> heap = new ArrayList<BinaryTree<T>>(); 
 
  public BinaryMaxHeap(){ 
 
  }  
   
  public void insert(T key){  
    BinaryTree<T> inserted = new BinaryTree<T>(key); 
    heap.add(inserted); 
    n++; 
    swimUp(n - 1); 
 
  } 
   
  public void swimUp(int insertIndex){ 
     
    if(insertIndex != 0){ 
       
      int parentIndex = getParentIndex(insertIndex); 
      BinaryTree<T> insertNode = heap.get(insertIndex); 
      BinaryTree<T> parentNode = heap.get(parentIndex); 
      BinaryTree<T> parentParentNode = heap.get(getParentIndex(parentIndex)); 
       
      int insertIndexVal = (Integer)insertNode.getData(); 
      int parentIndexVal = (Integer)parentNode.getData(); 
       
      BinaryTree<T> holder = heap.get(parentIndex);       
       
      if(insertIndexVal > parentIndexVal){ 
        heap.set(parentIndex, insertNode); 
        heap.set(insertIndex, holder); 
         
        if(insertIndex == getLeftIndex(parentIndex)){ 
          parentParentNode.setLeft(heap.get(insertIndex)); 
          parentNode.setLeft(holder);   
        }else if(insertIndex == getRightIndex(parentIndex)){ 
          parentParentNode.setRight(heap.get(insertIndex)); 
          parentNode.setRight(holder);   
        }         
        swimUp(parentIndex); 
      }            
    }     
  } 
   
 
  public Boolean less(BinaryTree<T> key1, BinaryTree<T> key2){ 
    int determinant = key1.getData().compareTo(key2.getData()); 
      
    if(determinant == 0 || determinant == -1){ 
      return true; 
    } 
    return false; 
  } 
   
  public int getParentIndex(Integer index){ 
     
     
    Double indexVal = index.doubleValue(); 
    Double returnedValue = 0.0;  
     
    if(indexVal > 2){ 
       
      if(indexVal % 2 == 0){ 
        returnedValue = (indexVal / 2) - 1; 
      }else if(indexVal % 2 != 0){ 
        returnedValue = (indexVal / 2); 
      } 
    } 
     
    int a = returnedValue.intValue(); 
     
    return a; 
  } 
     
  public int getLeftIndex(int indexVal){ 
    if(n == 0){ 
      return 0; 
    }else if(indexVal == 0){ 
      return 1; 
    }else{ 
      return (indexVal * 2); 
    }  
  } 
     
  public int getRightIndex(int indexVal){ 
    if(n == 0){ 
      return 0; 
    }else if(indexVal == 0){ 
      return 2; 
    }else{ 
      return (indexVal * 2) + 1; 
    }  
  } 
   
  public T delMax(){ 
     
    int maxPos = 0; 
    BinaryTree<T> holder = heap.get(maxPos); 
    T removed = holder.getData(); 
    heap.set(maxPos, heap.get(n - 1)); 
    heap.set(n - 1, holder);  
    n--; 
 
    sinkDown(maxPos); 
 
    return removed; 
  }  
   
  public void sinkDown(int maxIndex){ 
     
    BinaryTree<T> toBeMoved = heap.get(maxIndex); 
    int toBeMovedValue = (Integer)toBeMoved.getData(); 
    System.out.println((Integer)toBeMoved.getData()); 
    
    if(maxIndex < n - 2){ 
      if(!toBeMoved.isLeaf()){ 
       
        if(toBeMoved != heap.get(n - 3)){ 
       
          int leftChildIndex = getLeftIndex(maxIndex); 
          int rightChildIndex = getRightIndex(maxIndex); 
          int leftChildValue = (Integer)heap.get(leftChildIndex).getData(); 
          int rightChildValue = (Integer)heap.get(rightChildIndex).getData(); 
     
          System.out.println(leftChildIndex); 
          System.out.println(rightChildIndex); 
          System.out.println(leftChildValue); 
          System.out.println(rightChildValue); 
     
          if(toBeMovedValue < leftChildValue && leftChildValue > rightChildValue ){ 
            BinaryTree<T> tmpLeft = heap.get(maxIndex); 
            heap.set(maxIndex, heap.get(leftChildIndex)); 
            heap.set(leftChildIndex, tmpLeft); 
            sinkDown(leftChildIndex); 
         
          } 
          else if(toBeMovedValue < rightChildValue && leftChildValue < rightChildValue){ 
            BinaryTree<T> tmpRight = heap.get(maxIndex); 
            heap.set(maxIndex, heap.get(rightChildIndex)); 
            heap.set(rightChildIndex, tmpRight);     
            sinkDown(rightChildIndex); 
          } 
       
        } 
         
      } 
    } 
  } 
   
   
  public boolean isEmpty(){ 
    return n == 0; 
  } 
   
  public int size(){ 
    return n; 
  } 
   
  public String toString(){ 
     
    String sum = ""; 
     
    if(heap.get(0).getData() != null){ 
       sum = "" + heap.get(0).getData(); 
    } 
     
    for(int i = 1; i < n; i++){ 
      if(heap.get(i) != null){ 
       
      sum = sum + ", " + heap.get(i).getData(); 
    } 
    }                                         
      
    return sum; 
  } 
   
  public static void main(String[] args){ 
    BinaryMaxHeap<Integer> testHeap = new BinaryMaxHeap<Integer>(); 
    testHeap.insert(1); 
    testHeap.insert(3); 
    testHeap.insert(2); 
    testHeap.insert(6); 
    testHeap.insert(4); 
    testHeap.insert(5); 
    testHeap.insert(7); 
    testHeap.insert(9);     
    testHeap.insert(211);     
    testHeap.insert(511);     
    testHeap.delMax(); 
    
     
    System.out.println(testHeap.toString()); 
    
  } 
}
