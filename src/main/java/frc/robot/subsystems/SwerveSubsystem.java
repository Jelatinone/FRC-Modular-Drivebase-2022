//Root Package
package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
//Libraries
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.sensors.Pigeon2;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import java.util.Objects;

//SwerveSubSystem
public class SwerveSubsystem extends SubsystemBase
{
  //INSTANCE VARIABLES
  //Drive Motors
  private WPI_TalonFX D_FL;
  private WPI_TalonFX D_FR;
  private WPI_TalonFX D_BL;
  private WPI_TalonFX D_BR;
  //Rotational Motors
  private WPI_TalonFX R_FL;
  private WPI_TalonFX R_FR;
  private WPI_TalonFX R_BL;
  private WPI_TalonFX R_BR;
  //S-Rotational List
  private WPI_TalonFX[] K_Rotational;
  private WPI_TalonFX[] N_Rotationals;
  //S-Drive List
  private WPI_TalonFX[] K_Drive;
  private WPI_TalonFX[] N_Drives;
  //Large List
  private final WPI_TalonFX[] Rotational;
  private final WPI_TalonFX[] Drive;  
  //Group Lists
  private final WPI_TalonFX[] [] Rotational_Groups;
  private final WPI_TalonFX[] [] Drive_Groups;  
  //Gyroscope
  final Pigeon2 M_Gyro;
  //Rotational Face
  private int R_Face;
  //Primary Constructor
  public SwerveSubsystem(Pigeon2 Gyro)
  {
    //Drive Motors
    D_FL = new WPI_TalonFX(0);
    D_FR = new WPI_TalonFX(4);
    D_BL = new WPI_TalonFX(1);
    D_BR = new WPI_TalonFX(5);
    //Rotational Motors
    R_FL = new WPI_TalonFX(2);
    R_FR = new WPI_TalonFX(6);
    R_BL = new WPI_TalonFX(3);
    R_BR = new WPI_TalonFX(7);
    //Set Motors Straight
    R_FL.set(Math.atan(45.0));
    R_BL.set(Math.atan(-45.0));
    R_FR.set(Math.atan(45.0));
    R_BR.set(Math.atan(-45.0));
    //Invert
    R_BL.setInverted(true);
    R_BR.setInverted(true);
    //Large Groups
    Rotational = new WPI_TalonFX[]{R_FL,R_FR,R_BL,R_BR};
    Drive = new WPI_TalonFX[]{D_FL,D_FR,D_BL,D_BR};
    //Set Neutral Brake
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
    //Rotational Face
    R_Face = 0;
    //Group Lists
    Rotational_Groups = new WPI_TalonFX[] [] {{R_FL,R_FR},{R_FL,R_BL},{R_BL,R_BR},{R_FR,R_BR}};
    Drive_Groups = new WPI_TalonFX[] [] {{D_FL, D_FR},{D_FL, D_BL},{D_BL,D_BR},{D_BR, D_FR}};
  }
  //Decrement
  public void DecrementRotationalFace(){if(Objects.equals(R_Face,0)) {R_Face = 3;} else {R_Face--;}}
  //Increment
  public void IncrementRotationalFace(){if(Objects.equals(R_Face,3)) {R_Face = 0;} else {R_Face++;}}
  //Periodic Subsystem
  @Override
  public void periodic() 
  {
    K_Drive = Drive_Groups[(((int)Math.round(M_Gyro.getCompassHeading()/90) + R_Face) > Drive_Groups.length)? (0): ((int)Math.round(M_Gyro.getCompassHeading()/90))];
    K_Rotational = Rotational_Groups[(((int)Math.round(M_Gyro.getCompassHeading()/90) + R_Face) > Rotational_Groups.length)? (0): ((int)Math.round(M_Gyro.getCompassHeading()/90))];
    N_Drives = new WPI_TalonFX[(Drive_Groups.length-1)];
    N_Rotationals = new WPI_TalonFX[(Rotational_Groups.length-1)];
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
  public WPI_TalonFX[] getKDrive() {return K_Drive;}
  //Return K[R] MotorControllerGroup
  public WPI_TalonFX[] getKRotate() {return K_Rotational;}
  //Return Rotaional Group
  public WPI_TalonFX[] getRotationalGroup() {return Rotational;}
  //Return Drive Group
  public WPI_TalonFX[] getDriveGroup() {return Drive;}
  //Return N[D]
  public WPI_TalonFX[] getNRotates() {return N_Rotationals;}
  //Return N[R]
  public WPI_TalonFX[] getNDrives() {return N_Drives;}
  //Return Current Compass Heading
  public double getCurrentHeading() {return M_Gyro.getCompassHeading();}
}
