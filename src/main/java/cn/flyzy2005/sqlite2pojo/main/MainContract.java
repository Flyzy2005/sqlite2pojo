package cn.flyzy2005.sqlite2pojo.main;

/**
 * Created by Fly on 2017/9/25.
 */
public interface MainContract {
    interface View {
        void success();

        void error(Exception e);
    }

    interface Presenter {
        void generatorPojo();
    }
}
