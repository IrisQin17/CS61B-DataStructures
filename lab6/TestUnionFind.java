import org.junit.Test;

public class TestUnionFind {

    @Test
    public void test() {
        UnionFind ds = new UnionFind(7);
        ds.union(1,0);
        ds.union(2,1);
        ds.union(3,1);
        ds.union(4,1);
        ds.union(6,5);
        ds.union(4,6);
    }
}
