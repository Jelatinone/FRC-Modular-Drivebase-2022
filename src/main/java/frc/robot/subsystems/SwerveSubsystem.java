//Root Package
package frc.robot.subsystems;

//Libraries
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.sensors.Pigeon2;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import java.util.Objects;

//SwerveSubSystem
public class SwerveSubsystem extends SubsystemBase
{
  //INSTANCE VARIABLES
  //Drive Motors
  private WPI_TalonSRX D_FL;
  private WPI_TalonSRX D_FR;
  private WPI_TalonSRX D_BL;
  private WPI_TalonSRX D_BR;
  //Rotational Motors
  private WPI_TalonSRX R_FL;
  private WPI_TalonSRX R_FR;
  private WPI_TalonSRX R_BL;
  private WPI_TalonSRX R_BR;
  //Large List
  private WPI_TalonSRX[] Rotational;
  private WPI_TalonSRX[] Drive;
  //S-Rotational List
  private WPI_TalonSRX[] K_Rotational;
  private WPI_TalonSRX[] N_Rotationals;
  //S-Drive List
  private WPI_TalonSRX[] K_Drive;
  private WPI_TalonSRX[] N_Drives;
  //Gyroscope
  final Pigeon2 M_Gyro;
  //Rotational Face
  private int R_Face;
  //Group Lists
  private WPI_TalonSRX[] [] Rotational_Groups;
  private WPI_TalonSRX[] [] Drive_Groups;
  //Compass Heading
  private static double Compass_Heading;
  //Constructors
  public SwerveSubsystem(Pigeon2 Gyro)
  {
    //Define Instances
    //Drive Motors
    D_FL = new WPI_TalonSRX(0);
    D_FR = new WPI_TalonSRX(4);
    D_BL = new WPI_TalonSRX(1);
    D_BR = new WPI_TalonSRX(5);
    //Rotational Motors
    R_FL = new WPI_TalonSRX(2);
    R_FR = new WPI_TalonSRX(6);
    R_BL = new WPI_TalonSRX(3);
    R_BR = new WPI_TalonSRX(7);
    //Large Groups
    Rotational = new WPI_TalonSRX[]{R_FL,R_FR,R_BL,R_BR};
    Drive = new WPI_TalonSRX[]{D_FL,D_FR,D_BL,D_BR};
    //Gyroscope
    M_Gyro = Gyro;
    //Controller
    //Rotational Face
    R_Face = 1;
    //Group Lists
    Rotational_Groups = new WPI_TalonSRX[] [] {{R_FL,R_FR},{R_FL,R_BL},{R_BL,R_BR},{R_FR,R_BR}};
    Drive_Groups = new WPI_TalonSRX[] [] {{D_FL, D_FR},{D_FL, D_BL},{D_BL,D_BR},{D_BR, D_FR}};
    //Compass Heading
    Compass_Heading = M_Gyro.getCompassHeading();
  }
  //Decrement
  public void DecrementRotationalFace(){if(R_Face > 0) {R_Face--;} else {R_Face = 4;}}
  //Increment
  public void IncrementRotationalFace(){if(R_Face < 4) {R_Face++;} else {R_Face = 0;}}

  //Periodic Subsystem
  @Override
  public void periodic() 
  {
    //Update Heading and Rotational Wheels
    this.RotationalWheels(Compass_Heading = M_Gyro.getCompassHeading());
    
  }

  //Simulation Periodic
  @Override
  public void simulationPeriodic() {}

  //Convert K-Motors, N-Motors
  public void RotationalWheels(double Heading)
  {
    int index = (((int)Math.round(Heading/90) + R_Face) > Rotational_Groups.length)? (0): ((int)Math.round(Heading/90));
    K_Drive = Drive_Groups[index];
    K_Rotational = Rotational_Groups[index];
    N_Drives = new WPI_TalonSRX[(Drive_Groups.length-1)];
    N_Rotationals = new WPI_TalonSRX[(Drive_Groups.length-1)];
    for(int i = 0, j = 0; i < (Drive_Groups.length); i++)
      if(!(Objects.equals(i,index))){N_Drives[j] = Drive[i];N_Rotationals[j] = Rotational[i];j++;}
  }
  //ACESSORS
  //Return Current Rotational Face
  public int getRotationalFace() {return R_Face;}
  //Return K[D] MotorControllerGroup
  public WPI_TalonSRX[] getKDrive() {return K_Drive;}
  //Return K[R] MotorControllerGroup
  public WPI_TalonSRX[] getKRotate() {return K_Rotational;}
  //Return Rotaional Group
  public WPI_TalonSRX[] getRotationalGroup() {return Rotational;}
  //Return Drive Group
  public WPI_TalonSRX[] getDriveGroup() {return Drive;}
  //Return N[D]
  public WPI_TalonSRX[] getNRotates() {return N_Rotationals;}
  //Return N[R]
  public WPI_TalonSRX[] getNDrives() {return N_Drives;}
  //Return Current Compass Heading
  public double getCurrentHeading() {return Compass_Heading;}
}
