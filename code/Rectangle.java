/*
Rectangle class:
Defines a simple Rectangle with a position for the top left corner,
and a width/height to represent the size of the Rectangle.
*/

public class Rectangle {

    protected Position position;//The top left corner of the Rectangle.
    protected int width;//Width of the Rectangle.
    protected int height;//Height of the Rectangle.

    //Constructor for new Rectangle with provided properties.
    public Rectangle(Position position, int width, int height) {
        this.position = position;
        this.width = width;
        this.height = height;
    }

    public Position getCentre() {
        return new Position(position.x + width/2, position.y + height/2);//Gets centre coordinates of the rectangle.
    }

    //Tests the Rectangle is intersecting with some otherRectangle.
    public boolean isIntersecting(Rectangle otherRectangle) {

        //otherRectangle - other position to compare against for a collision.
        //break if any of the following are true because it means they don't intersect

        if(position.y + height < otherRectangle.position.y) {
            return false;
        }
        if(position.y > otherRectangle.position.y + otherRectangle.height) {
            return false;
        }
        if(position.x + width < otherRectangle.position.x) {
            return false;
        }
        if(position.x > otherRectangle.position.x + otherRectangle.width) {
            return false;
        }

        //True if this Rectangle is intersecting the otherRectangle.
        return true;
    }
}