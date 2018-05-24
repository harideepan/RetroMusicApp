package code.name.monkey.retromusic.ui.adapter;

import code.name.monkey.retromusic.R;

/**
 * @author Hemanth S (h4h13).
 */
public class ViewType {

  public interface ViewAs {

    String VIEW_AS_GRID = "square";
    String VIEW_AS_CIRCLE = "circle";
    String VIEW_AS_COLOR = "color";
  }

  public interface ViewAsLayout {

    int VIEW_AS_GRID = R.layout.item_grid;
    int VIEW_AS_CIRCLE = R.layout.item_grid_circle;
    int VIEW_AS_COLOR = R.layout.item_color;
  }
}
