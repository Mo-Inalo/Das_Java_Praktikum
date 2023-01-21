interface Similarity<T extends Similarity<T>>
{
    boolean isSimilarTo(T s);
}
