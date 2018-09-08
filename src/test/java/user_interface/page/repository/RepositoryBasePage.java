package user_interface.page.repository;

import user_interface.component.RepositoryNavigationBar;

public abstract class RepositoryBasePage {

    private RepositoryNavigationBar navigationBar = new RepositoryNavigationBar();

    public RepositoryNavigationBar getNavigationBar() {
        return navigationBar;
    }
}
