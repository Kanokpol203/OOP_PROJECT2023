public class Screen_Size {
    private int og_tile_size = 30;
    private int scale = 5;
    private int tilesize = og_tile_size * scale;
    private int GUI_SIZE = og_tile_size * scale;
    private int screen_col = 5;
    private int screen_row = 3;
    private int width = tilesize * screen_col;
    private int game_height = tilesize * screen_row;
    private int height = tilesize * screen_row + GUI_SIZE;

    public void setScale(int scale) {
        this.scale = scale;
    }

    public void setTilesize(int tilesize) {
        this.tilesize = tilesize;
    }

    public void setScreen_col(int screen_col) {
        this.screen_col = screen_col;
    }

    public void setScreen_row(int screen_row) {
        this.screen_row = screen_row;
    }

    public int getOg_tile_size() {
        return og_tile_size;
    }

    public int getScale() {
        return scale;
    }

    public int getTilesize() {
        return tilesize;
    }

    public int getGUI_SIZE() {
        return GUI_SIZE;
    }

    public int getScreen_col() {
        return screen_col;
    }

    public int getScreen_row() {
        return screen_row;
    }

    public int getWidth() {
        return width;
    }

    public int getGame_height() {
        return game_height;
    }

    public int getHeight() {
        return height;
    }
    
    
    
}
