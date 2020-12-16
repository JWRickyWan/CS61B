public class Planet{
  public double xxPos;
  public double yyPos;
  public double xxVel;
  public double yyVel;
  public double mass;
  public String imgFileName;
  final double G = 6.67e-11;
  public Planet(double xP, double yP, double xV,
                  double yV, double m, String img){
    xxPos = xP;
    yyPos = yP;
    xxVel = xV;
    yyVel = yV;
    mass  = m;
    imgFileName = img;
  }
  public Planet(Planet p){
    xxPos = p.xxPos;
    yyPos = p.yyPos;
    xxVel = p.xxVel;
    yyVel = p.yyVel;
    mass  = p.mass;
    imgFileName = p.imgFileName;
}
public double calcDistance(Planet p){
    double xDiff = xxPos - p.xxPos;
    double yDiff = yyPos - p.yyPos;
    double diff = Math.sqrt(xDiff*xDiff + yDiff*yDiff);
    return diff;
}
public double calcForceExertedBy(Planet p){
    double dist = this.calcDistance(p);
    double force = (G*p.mass*mass)/(dist*dist);
    return force;

}
public double calcForceExertedByX(Planet p){
    double diff = p.xxPos - xxPos;
    double force = this.calcForceExertedBy(p);
    double dist = this.calcDistance(p);
    double xforce = force*diff/dist;
    return xforce;
}
public double calcForceExertedByY(Planet p){
    double diff = p.yyPos - yyPos;
    double force = this.calcForceExertedBy(p);
    double dist = this.calcDistance(p);
    double yforce = force*diff/dist;
    return yforce;
}
public double calcNetForceExertedByX(Planet [] allPlanets){
    double netForceX = 0;
    for (int i =0 ;i < allPlanets.length; i++){
      if (this.equals(allPlanets[i])){
        continue;
      }
      netForceX += this.calcForceExertedByX(allPlanets[i]);
    }
    return netForceX;
}

public double calcNetForceExertedByY(Planet[] allPlanets){
    double netForceY = 0;
    for (int i =0 ;i < allPlanets.length; i++){
      if (this.equals(allPlanets[i])){
        continue;
      }
      netForceY += this.calcForceExertedByY(allPlanets[i]);
    }
    return netForceY;
}
public void update(double dt, double fX, double fY){
  double accelX = fX/mass;
  double accelY = fY/mass;
  xxVel += accelX*dt;
  yyVel += accelY*dt;
  xxPos += xxVel*dt;
  yyPos += yyVel*dt;

}
public void draw(){
  String imageToDraw = "images/" + imgFileName;
  StdDraw.picture(xxPos,yyPos,imageToDraw);
}

  }
