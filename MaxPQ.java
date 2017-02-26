public interface MaxPQ<T extends Comparable<T>>{
  public void insert(T key);
  public void delMax();
  public boolean isEmpty();
  public int size();
  public String toString();
}