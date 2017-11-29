/*
 jug a = x litre
 jug b = y liter
 water_supply = infinite
 determine true/false z liters is measurable
 you must have z liters of water contained within one or both buckets by the end
 3 operations:
 1) Fill any of the jugs completely with water
 2) empty any of the jugs
 3) pour water from one jug into another till the other jug is completely full or first jug itself is empty
 x = 3 y = 5 z = 4 : true
 x = 2 y = 6 z = 5 : false
*/
class Solution {
    public static boolean canMeasureWater(int x, int y, int z) {

        // base case
        if (z == x || z == y || z == x + y)
            return true;
        if (x + y < z)
            return false;

        // changing big and small comparison

        return (z%GCD(x,y) == 0);
    }

    public static int GCD(int x, int y){
        while (y != 0){
            int temp = y;
            y = x % y;
            x = temp;
        }
        return x;
    }


}
