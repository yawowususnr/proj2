
public interface BintreeNode {

    public abstract BintreeNode insert(Seminar seminar, BoundingBox box);
    public abstract List<Seminar> search(BoundingBox searchBox, BoundingBox nodeBox);
    public abstract BintreeNode remove(Seminar seminar, BoundingBox box);

        
}
