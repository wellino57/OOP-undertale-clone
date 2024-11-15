import java.awt.*;

public class BoundingBox extends GameObject {

    int width, height;
    int x, y;
    int wall;
    int targetWidth, targetHeight;

    public BoundingBox(GamePanel gp, int width, int height, int wall) {
        super(gp);
        this.width = width;
        this.height = height;
        this.wall = wall;

        this.targetWidth = width;
        this.targetHeight = height;

        x = (GameWindow.screenWidth - width)/2;
        y = (GameWindow.screenHeight - height)/2;
    }

    public void update() {
        if(width != targetWidth) {
            if(width > targetWidth) { width--; }
            else { width++; }
            System.out.println("Different target width");
        }
        if(height != targetHeight) {
            if(height > targetHeight) { height--; }
            else { height++; }
            System.out.println("Different target height");
        }
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

    public int getTargetWidth() {return targetWidth;}

    public int getTargetHeight() {return targetHeight;}

    public void setTargetWidth(int targetWidth) {this.targetWidth = targetWidth;}

    public void setTargetHeight(int targetHeight) {this.targetHeight = targetHeight;}
}
