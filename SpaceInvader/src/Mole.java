public class Mole extends Entity implements Hitable{
    private boolean visible;

    public Mole(int x, int y) {
        super(x, y);
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }
    
    @Override
    public void whack() {
        visible = false;
    }

    @Override
    public void update() {
        if(visible){
            visible = false;
        }else{
            visible = true;
        }
    }
    
}
