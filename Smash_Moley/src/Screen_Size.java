
public interface Screen_Size {
    final int OG_TILE_SIZE = 30;
    final int SCALE = 5;
    final int TILESIZE = OG_TILE_SIZE * SCALE;
    final int GUI_SIZE = OG_TILE_SIZE * SCALE;
    final int SCREEN_COL = 5;
    final int SCREEN_ROW = 3;
    final int WIDTH = TILESIZE * SCREEN_COL;
    final int GAME_HEIGHT = TILESIZE * SCREEN_ROW;
    final int HEIGHT = TILESIZE * SCREEN_ROW + GUI_SIZE;
}
