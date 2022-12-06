//Root Package
package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
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
    //Set Neutral Mode
    D_FL.setNeutralMode(NeutralMode.Brake);
    D_FR.setNeutralMode(NeutralMode.Brake);
    D_BL.setNeutralMode(NeutralMode.Brake);
    D_BR.setNeutralMode(NeutralMode.Brake);
    R_FL.setNeutralMode(NeutralMode.Brake);
    R_FR.setNeutralMode(NeutralMode.Brake);
    R_BL.setNeutralMode(NeutralMode.Brake);
    R_BR.setNeutralMode(NeutralMode.Brake);
    //Gyroscope
    M_Gyro = Gyro;
    //Controller
    //Rotational Face
    R_Face = 1;
    //Group Lists
    Rotational_Groups = new WPI_TalonSRX[] [] {{R_FL,R_FR},{R_FL,R_BL},{R_BL,R_BR},{R_FR,R_BR}};
    Drive_Groups = new WPI_TalonSRX[] [] {{D_FL, D_FR},{D_FL, D_BL},{D_BL,D_BR},{D_BR, D_FR}};
  }
  //Decrement
  public void DecrementRotationalFace(){if(Objects.equals(R_Face,0)) {R_Face = 3;} else {R_Face--;}}
  //Increment
  public void IncrementRotationalFace(){if(Objects.equals(R_Face,3)) {R_Face = 0;} else {R_Face++;}}
  //Periodic Subsystem
  @Override
  public void periodic() 
  {
    K_Drive = Drive_Groups[(((int)Math.round(M_Gyro.getCompassHeading()/90) + R_Face) > Rotational_Groups.length)? (0): ((int)Math.round(M_Gyro.getCompassHeading()/90))];
    K_Rotational = Rotational_Groups[(((int)Math.round(M_Gyro.getCompassHeading()/90) + R_Face) > Rotational_Groups.length)? (0): ((int)Math.round(M_Gyro.getCompassHeading()/90))];
    N_Drives = new WPI_TalonSRX[(Drive_Groups.length-1)];
    N_Rotationals = new WPI_TalonSRX[(Rotational_Groups.length-1)];
    for(int i = 0, j = 0; i < (Drive.length) && j < (N_Drives.length); i++)
    {
      if(!(Objects.equals(Drive[i],K_Drive[0]))){N_Drives[j] = Drive[i];j++;}
      else if(!(Objects.equals(Drive[i],K_Drive[1]))){N_Drives[j] = Drive[i];j++;}
    }
    for(int i = 0, j = 0; i < (Rotational.length) && j < (N_Rotationals.length); i++)
    {
      if(!(Objects.equals(Rotational[i],K_Rotational[0]))){N_Rotationals[j] = Rotational[i];j++;}
      else if(!(Objects.equals(Rotational[i],K_Rotational[1]))){N_Rotationals[j] = Rotational[i];j++;}
    }
  }
  //Simulation Periodic
  @Override
  public void simulationPeriodic() {}
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
  public double getCurrentHeading() {return M_Gyro.getCompassHeading();}
}
