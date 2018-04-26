package code.com.segmentfault;

import code.com.segmentfault.fragment.HomeFragment;

/**
 * Author: lihui1
 * Date: 2018/4/26
 * Desc:
 */

public enum MainTab {

    TAB_HOME(0, R.string.main_tab_home, R.drawable.tab_icon_home, HomeFragment.class),
    TAB_FIND(1, R.string.main_tab_find, R.drawable.tab_icon_find, HomeFragment.class),
    TAB_MOVIE(2, R.string.main_tab_movie, R.drawable.tab_icon_movie, HomeFragment.class),
    TAB_ME(3, R.string.main_tab_me, R.drawable.tab_icon_user, HomeFragment.class);

    private int index;

    private int nameRes;

    private int iconRes;

    private Class<?> clazz;

    MainTab(int index, int nameRes, int iconRes, Class<?> clazz) {
        this.index = index;
        this.nameRes = nameRes;
        this.iconRes = iconRes;
        this.clazz = clazz;
    }

    public int getIndex() {
        return index;
    }

    public int getNameRes() {
        return nameRes;
    }

    public int getIconRes() {
        return iconRes;
    }

    public Class<?> getClazz() {
        return clazz;
    }
}
