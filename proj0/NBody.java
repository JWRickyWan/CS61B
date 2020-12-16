public class NBody{

  public static double readRadius(String fileName){
    In in = new In("./data/planets.txt");
    int num = in.readInt();
    double radius = in.readDouble();
    return radius;
  }

  public static Planet[] readPlanets(String fileName){
    In in = new In("./data/planets.txt");
    int num = in.readInt();
    double radius = in.readDouble();
    int i = 0;
    Planet[] allPlanets = new Planet[5];
    while(i<=4){
      double xPos = in.readDouble();
      double yPos = in.readDouble();
      double xVel = in.readDouble();
      double yVel = in.readDouble();
      double mass = in.readDouble();
      String imageName = in.readString();
      allPlanets[i] = new Planet(xPos,yPos,xVel,yVel,mass,imageName);
      i +=1;


    }
    return allPlanets;
  }
  public static void main(String[] args) {
    StdDraw.enableDoubleBuffering();
    StdAudio.play("./audio/2001.mid");
    double T = Double.valueOf(args[0]);
    double dt = Double.valueOf(args[1]);
    String filename = args[2];
    Planet[] allPlanets = readPlanets(filename);
    String imageToDraw = "images/starfield.jpg";
    double radius = readRadius(filename);
    double time = 0;
    StdDraw.setScale(-radius, radius);
    StdDraw.clear();
    StdDraw.picture(0,0,imageToDraw);
    for(int i=0;i<allPlanets.length;i++){
      String image = "images/" + allPlanets[i].imgFileName;
      StdDraw.picture(allPlanets[i].xxPos,allPlanets[i].yyPos,image);

    }
    StdDraw.show();
    while (time <= T){
      double[] xForces = new double[5];
      double[] yForces = new double[5];
      for(int i =0; i<5;i++){
        xForces[i] = allPlanets[i].calcNetForceExertedByX(allPlanets);
        yForces[i] = allPlanets[i].calcNetForceExertedByY(allPlanets);
      }
      for(int i =0; i<5;i++){
        allPlanets[i].update(dt,xForces[i],yForces[i]);
        StdDraw.picture(0,0,imageToDraw);
      }
        for(int i=0;i<allPlanets.length;i++){
          String image = "images/" + allPlanets[i].imgFileName;
          StdDraw.picture(allPlanets[i].xxPos,allPlanets[i].yyPos,image);

        }
        StdDraw.show();
        StdDraw.pause(10);
        time += dt;
      }
      StdOut.printf("%d\n", allPlanets.length);
      StdOut.printf("%.2e\n", radius);
      for (int i = 0; i < allPlanets.length; i++) {
        StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
        allPlanets[i].xxPos, allPlanets[i].yyPos, allPlanets[i].xxVel,
        allPlanets[i].yyVel, allPlanets[i].mass, allPlanets[i].imgFileName);
}
    }

  }
