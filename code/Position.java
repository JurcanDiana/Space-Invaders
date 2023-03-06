/*
Position class:
Used to represent a single position x,y.
*/

public class Position {

    public static final Position DOWN = new Position(0,1);//Down moving unit vector.
    public static final Position UP = new Position(0,-1);//Up moving unit vector.

    public static final Position LEFT = new Position(-1,0);//Left moving unit vector.
    public static final Position RIGHT = new Position(1,0);//Right moving unit vector.

    public int x;//X coordinate.
    public int y;//Y coordinate.

    //Sets the value of Position.
    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    //Copy constructor to create a new Position using the values in another.
    public Position(Position positionToCopy) {
        this.x = positionToCopy.x;
        this.y = positionToCopy.y;
    }

    //Sets the Position to the specified x and y coordinate.
    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    //Updates this position by adding the values from the otherPosition.
    public void add(Position otherPosition) {
        this.x += otherPosition.x;
        this.y += otherPosition.y;
    }

    //Multiplies both components of the position by an amount.
    public void multiply(int amount) {
        x *= amount;
        y *= amount;
    }

    //Compares the Position object against another object.
    //o - object to compare this Position against.
    @Override
    public boolean equals(Object o) {

        //Object to compare this Position against.
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;//Any non-Position object will return false.
        }
        Position position = (Position) o;

        return x == position.x && y == position.y;//Otherwise compares x and y for equality.
        //True if the object o is equal to this position for both x and y.
    }

    //Gets a string version of the Position.
    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";//return a string in the form (x, y)
    }
}