public class Projectile extends Entity{
    private int damege;
    public Projectile(){
        
    }

    public Projectile(int damege) {
        this.damege = damege;
    }

    public Projectile(int damege, int x, int y, int speed) {
        super(x, y, speed);
        this.damege = damege;
    }
    public void update(){
        y += speed;
    }

    public int getDamege() {
        return damege;
    }
    
}
