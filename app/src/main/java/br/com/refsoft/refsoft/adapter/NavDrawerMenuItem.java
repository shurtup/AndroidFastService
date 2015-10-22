package br.com.refsoft.refsoft.adapter;

/**
 * Created by Gabriel on 17/09/2015.
 */

import java.util.ArrayList;
import java.util.List;

import br.com.refsoft.refsoft.R;

public class NavDrawerMenuItem {
    public int title;
    public int img;
    public boolean selected;

    public NavDrawerMenuItem(int title, int img) {
        this.title = title;
        this.img = img;
    }

    public static List<NavDrawerMenuItem> getList() {
        List<NavDrawerMenuItem> list = new ArrayList<NavDrawerMenuItem>();
        list.add(new NavDrawerMenuItem(R.string.fastService, R.drawable.ic_star_rate_black_18dp));
        list.add(new NavDrawerMenuItem(R.string.profile, R.drawable.ic_insert_emoticon_black_24dp));
        list.add(new NavDrawerMenuItem(R.string.cadastrar, R.drawable.ic_control_point_black_24dp));
        list.add(new NavDrawerMenuItem(R.string.listarReportes, R.drawable.ic_view_list_black_24dp));
        list.add(new NavDrawerMenuItem(R.string.alterarSenha, R.drawable.ic_edit_black_24dp));
        list.add(new NavDrawerMenuItem(R.string.localizacao, R.drawable.ic_loupe_black_24dp));
        return list;
    }
}