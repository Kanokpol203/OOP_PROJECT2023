public abstract class Entity {
    private int x;
    private int y;
    private boolean visible;
    private int score;
    
    public Entity() {
    }
    
    public Entity(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public Entity(int x, int y, int score) {
        this.x = x;
        this.y = y;
        this.score = score;
    }
    
    public void setScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }


    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    public abstract void update();
    
}
