package sivantoledo.util;

public final class Pair<L,R> {

  public final L left;
  public final R right;

  public Pair(L left, R right) {
    this.left = left;
    this.right = right;
  }

  @Override
  public int hashCode() { return left.hashCode() ^ right.hashCode(); }

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof Pair)) return false;
    @SuppressWarnings("unchecked")
    Pair<L,R> pairo = (Pair<L,R>) o;
    return this.left.equals(pairo.left) &&
           this.right.equals(pairo.right);
  }

}