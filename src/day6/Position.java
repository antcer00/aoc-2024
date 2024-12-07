package day6;

public class Position {
    private char content = '.';
    private boolean visited = false;
    private int xPos = 0;
    private int yPos = 0;

    public Position(int xPos, int yPos, char content, boolean visited) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.content = content;
        this.visited = visited;
    }

    public Position(int xPos, int yPos, char content) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.content = content;
    }

    public char getContent() {
        return content;
    }

    public void setContent(char content) {
        this.content = content;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
        this.setContent('X');
    }

    public int getxPos() {
        return xPos;
    }

    public void setxPos(int xPos) {
        this.xPos = xPos;
    }

    public int getyPos() {
        return yPos;
    }

    public void setyPos(int yPos) {
        this.yPos = yPos;
    }

    public boolean isGuard() {
        return  getContent() == '^' || getContent() == '>' || getContent() == 'v' || getContent() == '<';
    }

    public boolean isObstacle() {
        return getContent() == '#';
    }
}
