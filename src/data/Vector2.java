package data;

public class Vector2 {
    public int x;
    public int y;

    Vector2(int x, int y){
        this.x = x;
        this.y = y;
    }
    Vector2(int x){
        this.x = x;
        this.y = x;
    }
    Vector2(){
        x = 0;
        y = 0;
    }

}
