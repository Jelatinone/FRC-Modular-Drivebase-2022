//Root Package
package frc.robot.subsystems;

//Libraries
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.sensors.Pigeon2;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

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
  //Large Groups
  private MotorControllerGroup Rotational;
  private MotorControllerGroup Drive;
  //Rotational Groups
  private MotorControllerGroup F_Rotational;
  private MotorControllerGroup L_Rotational;
  private MotorControllerGroup B_Rotational;
  private MotorControllerGroup R_Rotational;
  private MotorControllerGroup K_Rotational;
  //Drive Groups
  private MotorControllerGroup F_Drive;
  private MotorControllerGroup L_Drive;
  private MotorControllerGroup B_Drive;
  private MotorControllerGroup R_Drive;
  private MotorControllerGroup K_Drive;
  //Gyroscope
  final Pigeon2 M_Gyro;
  //Rotational Face
  private int R_Face;
  //Group Lists
  private MotorControllerGroup[] Rotational_Groups;
  private MotorControllerGroup[] Drive_Groups;
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
    Rotational = new MotorControllerGroup(R_FL,R_FR,R_BL,R_BR);
    Drive = new MotorControllerGroup(D_FL,D_FR,D_BL,D_BR);
    //Rotational Groups
    F_Rotational = new MotorControllerGroup(R_FL,R_FR);
    L_Rotational = new MotorControllerGroup(R_FL,R_BL);
    B_Rotational = new MotorControllerGroup(R_BL,R_BR);
    R_Rotational = new MotorControllerGroup(R_FR,R_BR);
    //Drive Groups
    F_Drive = new MotorControllerGroup(D_FL, D_FR);
    L_Drive = new MotorControllerGroup(D_FL, D_BL);
    B_Drive = new MotorControllerGroup(D_BL,D_BR);
    R_Drive = new MotorControllerGroup(D_BR, D_FR);
    //Additional
    M_Gyro = Gyro;
    //Rotational Face
    R_Face = 1;
    //Group Lists
    Rotational_Groups = new MotorControllerGroup[] {F_Rotational,L_Rotational,B_Rotational,R_Rotational};
    Drive_Groups = new MotorControllerGroup[] {F_Drive,L_Drive,B_Drive,R_Drive};
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
    RotationalWheels(Compass_Heading = M_Gyro.getCompassHeading());
  }

  //Simulation Periodic
  @Override
  public void simulationPeriodic() {}

  //Convert K-Motors
  public void RotationalWheels(double Heading)
  {
    K_Drive = Drive_Groups[(((int)Math.round(Heading/90) + R_Face) > 4)? (0): ((int)Math.round(Heading/90))];
    K_Rotational = Rotational_Groups[(((int)Math.round(Heading/90) + R_Face) > 4)? (0): ((int)Math.round(Heading/90))];
  }

  //ACESSORS
  //Return Current Rotational Face
  public int getRotationalFace() {return R_Face;}
  //Return K[D] MotorControllerGroup
  public MotorControllerGroup getKDrive() {return K_Drive;}
  //Return K[R] MotorControllerGroup
  public MotorControllerGroup getKRotate() {return K_Rotational;}
  //Return Rotaional Group
  public MotorControllerGroup getRotationalGroup() {return Rotational;}
  //Return Drive Group
  public MotorControllerGroup getDriveGroup() {return Drive;}
  //Return Current Compass Heading
  public double getCurrentHeading() {return Compass_Heading;}
}
