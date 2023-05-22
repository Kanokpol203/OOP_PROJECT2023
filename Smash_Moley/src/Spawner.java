
import java.util.Random;
import javax.swing.SwingWorker;

public class Spawner extends SwingWorker<Void, Void>{
    GameBoard game;
    long lastspawntime;
    long spawndelay = 500;
    public Spawner(GameBoard game){
        this.game = game;
        lastspawntime = System.currentTimeMillis();
    }
    @Override
    protected Void doInBackground() throws Exception {
        while(!isCancelled()){
            long currentTime = System.currentTimeMillis();
            long elapsedTime = currentTime - lastspawntime;
            if(game.getMode().equals("ez") || game.getMode().equals("nr"))
            {
                if (elapsedTime >= spawndelay && game.moles.size() < 3 && game.bombs.size() < 2) {
                    Random random = new Random();
                    int x, y;
                    boolean overlap;
                    do {
                        overlap = false;
                        x = random.nextInt(game.getScreen().getScreen_col()) * game.getScreen().getTilesize();
                        y = (random.nextInt(game.getScreen().getScreen_row()) + 1) * game.getScreen().getTilesize();
                        for (Mole mole : game.moles) {
                            if (mole.getX() == x && mole.getY() == y) {
                                overlap = true;
                                break;
                            }
                        }
                        if(!overlap){
                            for (Bomb bomb : game.bombs){
                                if (bomb.getX() == x && bomb.getY() == y){
                                    overlap = true;
                                    break;
                                }  
                            }
                        }
                    } while (overlap);

                    if (random.nextInt(3) == 0) {
                        if (game.getMode().equals("nr"))
                        {
                            Bomb bomb = new Bomb(x, y, game);
                            game.bombs.add(bomb);
                            game.getPool().submit(bomb);
                        }
                    } else {
                        Mole mole = new Mole(x, y, game);
                        game.moles.add(mole);
                        game.getPool().submit(mole);
                    }
                    lastspawntime = currentTime;
                }
            }
            else if (game.getMode().equals("hr"))
            {
                if (elapsedTime >= spawndelay && game.moles.size() < 2 && game.bombs.size() < 4 && game.nukes.size() < 2) {
                    Random random = new Random();
                    Random random2 = new Random();
                    int x, y;
                    boolean overlap;
                    do {
                    overlap = false;
                     x = random.nextInt(game.getScreen().getScreen_col()) * game.getScreen().getTilesize();
                     y = (random.nextInt(game.getScreen().getScreen_row()) + 1) * game.getScreen().getTilesize();

                for (Mole mole : game.moles) {
                    if (mole.getX() == x && mole.getY() == y) {
                        overlap = true;
                        break;
                    }
                }

                for (Bomb bomb : game.bombs) {
                    if (bomb.getX() == x && bomb.getY() == y) {
                        overlap = true;
                        break;
                    }
                }

                for (Nuke nuke : game.nukes) {
                    if (nuke.getX() == x && nuke.getY() == y) {
                        overlap = true;
                        break;
                    }
                }
                    } while (overlap);
                    if (random.nextInt(2) == 0) {
                        Bomb bomb = new Bomb(x, y, game);
                        game.bombs.add(bomb);
                        game.pool.submit(bomb);
                    }else if(random2.nextInt(3) == 1){
                        Nuke nuke = new Nuke(x, y, game);
                        game.nukes.add(nuke);
                        game.pool.submit(nuke);
                    } else {
                        Mole mole = new Mole(x, y, game);
                        game.moles.add(mole);
                        game.pool.submit(mole);
                    }
                    lastspawntime = currentTime;
                }
            }
        }
        return null;
    }
    public void stopSpawner(){
        cancel(true);
    }
}
