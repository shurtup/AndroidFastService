package br.com.refsoft.refsoft.adapter;

/**
 * Created by Gabriel on 17/09/2015.
 */

import java.util.ArrayList;
import java.util.List;

import br.com.refsoft.refsoft.R;


/**
 * Created by Ricardo Lecheta on 24/01/2015.
 */
public class NavDrawerMenuItem {
    // Título: R.string.xxx
    public int title;
    // Figura: R.drawable.xxx
    public int img;
    // Para colocar um fundo cinza quando a linha está selecionada
    public boolean selected;
    public NavDrawerMenuItem(int title, int img) {
        this.title = title;
        this.img = img;
    }
    // Cria a lista com os itens de menu
    public static List<NavDrawerMenuItem> getList() {
        List<NavDrawerMenuItem> list = new ArrayList<NavDrawerMenuItem>();
        list.add(new NavDrawerMenuItem(R.string.cleanTrash, R.drawable.ic_star_rate_black_18dp));
        list.add(new NavDrawerMenuItem(R.string.profile, R.drawable.ic_insert_emoticon_black_24dp));
        list.add(new NavDrawerMenuItem(R.string.cadastrar, R.drawable.ic_control_point_black_24dp));
        list.add(new NavDrawerMenuItem(R.string.listarReporte, R.drawable.ic_view_list_black_24dp));
        list.add(new NavDrawerMenuItem(R.string.alterarSenha, R.drawable.ic_edit_black_24dp));
        list.add(new NavDrawerMenuItem(R.string.localizacao, R.drawable.ic_loupe_black_24dp));
        return list;
    }
}