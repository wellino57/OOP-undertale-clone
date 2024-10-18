import java.awt.*;

public class BoundingBox extends GameObject {

    int width, height;
    int x, y;
    int wall;

    public BoundingBox(GamePanel gp, int width, int height, int wall) {
        super(gp);
        this.width = width;
        this.height = height;
        this.wall = wall;

        x = (GameWindow.screenWidth - width)/2;
        y = (GameWindow.screenHeight - height)/2;
    }

    public void update() {

    }

    public void draw(Graphics2D g2) {
        g2.setColor(Color.white);
        g2.fillRect(x-wall, y-wall, width+2*wall, height+2*wall);
        g2.setColor(Color.black);
        g2.fillRect(x, y, width, height);
    }

    public int getTopBound() {
        return y;
    }

    public int getBottomBound() {
        return y + height;
    }

    public int getLeftBound() {
        return x;
    }

    public int getRightBound() {
        return x+width;
    }

    public int getWidth() {return width;}

    public int getHeight() {return height;}
}
