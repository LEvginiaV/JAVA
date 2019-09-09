public class homework_2 {

    public static void main (String[] args) {

        Point p1 = new Point(3,4);
        Point p2 = new Point (-3,-4);

        //использование функции
        System.out.println("Работа функции:");
        System.out.println(" -расстояние между точками A(" + p1.a + "; " + p1.b + ") и B(" + p2.a + "; " + p2.b + ") равно " + distance(p1, p2)) ;
        //использование метода
        System.out.println("Работа метода:");
        System.out.println(" -расстояние между точками A(" + p1.a + "; " + p1.b + ") и B(" + p2.a + "; " + p2.b + ") равно " + p1.distance(p2)) ;
    }

    public static double distance(Point p1, Point p2) {
        return Math.sqrt(Math.pow(p1.a - p2.a, 2) + Math.pow(p1.b - p2.b, 2));
    }
}
