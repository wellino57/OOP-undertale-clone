import java.awt.*;

public class Attack extends GameObject implements DamageSystem{

    public int damage;

    public Attack(GamePanel gp) {
        super(gp);
    }

    @Override
    public void update() {

    }

    @Override
    public void draw(Graphics2D g2) {

    }

    @Override
    public void dealDamage(GameObject target, int damage) {
        if(target instanceof Player){
            ((Player) target).health -= damage;
        }
    }
}
