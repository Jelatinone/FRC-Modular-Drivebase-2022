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
  final WPI_TalonSRX D_FL;
  final WPI_TalonSRX D_FR;
  final WPI_TalonSRX D_BL;
  final WPI_TalonSRX D_BR;
  //Rotational Motors
  final WPI_TalonSRX R_FL;
  final WPI_TalonSRX R_FR;
  final WPI_TalonSRX R_BL;
  final WPI_TalonSRX R_BR;
  //Large Groups
  final MotorControllerGroup Rotational;
  final MotorControllerGroup Drive;
  //Rotational Groups
  final MotorControllerGroup F_Rotational;
  final MotorControllerGroup L_Rotational;
  final MotorControllerGroup B_Rotational;
  final MotorControllerGroup R_Rotational;
  final MotorControllerGroup K_Rotational;
  //Drive Groups
  final MotorControllerGroup F_Drive;
  final MotorControllerGroup L_Drive;
  final MotorControllerGroup B_Drive;
  final MotorControllerGroup R_Drive;
  final MotorControllerGroup K_Drive;

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
  }

  //Periodic Subsystem
  @Override
  public void periodic() {}

  //Simulation Periodic
  @Override
  public void simulationPeriodic() {}
}
