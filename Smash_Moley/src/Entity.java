public abstract class Entity {
    private int x;
    private int y;

    public Entity() {
    }
    
    public Entity(int x, int y) {
        this.x = x;
        this.y = y;
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
