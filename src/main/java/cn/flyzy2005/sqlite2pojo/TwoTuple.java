package cn.flyzy2005.sqlite2pojo;

/**
 * Created by Fly on 2017/9/25.
 */
public class TwoTuple<T, V> {
    public final T first;
    public final V second;

    public TwoTuple(T first, V second) {
        this.first = first;
        this.second = second;
    }
}
