package cn.flyzy2005.sqlite2pojo.main;

/**
 * Created by Fly on 2017/9/25.
 */
public class MainView implements MainContract.View {
    private static MainContract.Presenter presenter;

    public MainView() {
        presenter = new MainPresenter(this);
    }

    public static void main(String[] args) {
        new MainView();
        System.out.println("Start working...");
        presenter.generatorPojo();
    }

    public void success() {
        System.out.println("success!");
    }

    public void error(Exception e) {
        System.out.println("fail!");
        e.printStackTrace();
    }
}
