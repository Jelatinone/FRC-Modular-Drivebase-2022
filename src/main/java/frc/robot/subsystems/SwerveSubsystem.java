//Root Package
package frc.robot.subsystems;

//Libraries
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

//SwerveSubSystem
public class SwerveSubsystem extends SubsystemBase
{
  //Instance Variables

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
  //Primitives
  private int R_Face;

  //Constructors
  public SwerveSubsystem()
  {
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
    //Movement Rotational
    K_Drive = new MotorControllerGroup(D_FL, D_FR);
    K_Rotational = new MotorControllerGroup(R_FL,R_FR);
    //Rotational Face
    R_Face = 1;
  }
  //Decrement
  public void DecrementRotationalFace(){if(R_Face > 0) {R_Face--;} else {R_Face = 4;}}
  //Increment
  public void IncrementRotationalFace(){if(R_Face < 4) {R_Face++;} else {R_Face = 0;}}

  //Periodic Subsystem
  @Override
  public void periodic() {}

  //Simulation Periodic
  @Override
  public void simulationPeriodic() {}
}
