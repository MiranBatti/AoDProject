package gui;

import java.awt.*;

public class Tile extends Component {

    private static final int SIZE = 15;

    private int index;

    private boolean upWall;
    private boolean downWall;
    private boolean leftWall;
    private boolean rightWall;

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public boolean isUpWall() {
        return upWall;
    }

    public void setUpWall(boolean upWall) {
        this.upWall = upWall;
    }

    public boolean isDownWall() {
        return downWall;
    }

    public void setDownWall(boolean downWall) {
        this.downWall = downWall;
    }

    public boolean isLeftWall() {
        return leftWall;
    }

    public void setLeftWall(boolean leftWall) {
        this.leftWall = leftWall;
    }

    public boolean isRightWall() {
        return rightWall;
    }

    public void setRightWall(boolean rightWall) {
        this.rightWall = rightWall;
    }
}
