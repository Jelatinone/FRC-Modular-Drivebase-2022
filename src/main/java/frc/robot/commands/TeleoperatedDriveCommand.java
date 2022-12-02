//Root Package
package frc.robot.commands;

//Local
import frc.robot.subsystems.SwerveSubsystem;
//Libraries
import edu.wpi.first.wpilibj2.command.CommandBase;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import java.util.function.DoubleSupplier;
import java.util.Objects;

import com.ctre.phoenix.sensors.Pigeon2;

public class TeleoperatedDriveCommand extends CommandBase
{
  //Suppress Warnings
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})


  //Instance Variables
  private final double JoystickL_X;
  private final double JoystickL_Y;
  private final double JoystickR_X;
  private final SwerveSubsystem Parent_Subsystem;
  private boolean Command_Complete = false;


  //Constructors
  public TeleoperatedDriveCommand(SwerveSubsystem Parent, DoubleSupplier Left_X, DoubleSupplier Left_Y, DoubleSupplier Right_X,Pigeon2 gyro)
  {
    //Define Instances
    //Joystick Assignment
    if(Left_X.getAsDouble() > 0.05){JoystickL_X = Left_X.getAsDouble();}else{JoystickL_X = 0.0;}
    if(Left_Y.getAsDouble() > 0.05){JoystickL_Y = Left_Y.getAsDouble();}else{JoystickL_Y = 0.0;}
    if(Right_X.getAsDouble() > 0.05){JoystickR_X = Right_X.getAsDouble();}else{JoystickR_X = 0.0;}
    //Parent Subsystem
    Parent_Subsystem = Parent;
    //Add Command To Parent Subsystem
    addRequirements(Parent_Subsystem);;
  }

  //Initialize Command
  @Override
  public void initialize() {}

  //Execute Command
  @Override
  public void execute() 
  {
    //Motor Assignment
    WPI_TalonSRX K_Drive_Motor = Parent_Subsystem.getKDrive()[(JoystickR_X > 0)? (1): ((JoystickR_X < 0)? (0): (0))];
    WPI_TalonSRX K_Rotational_Motor = Parent_Subsystem.getKRotate()[(JoystickR_X > 0)? (1): ((JoystickR_X < 0)? (0): (0))];
    WPI_TalonSRX[] N_Drive_Motors = Parent_Subsystem.getNDrives();
    WPI_TalonSRX[] N_Rotational_Motors = Parent_Subsystem.getNRotates();
    //Execution
    K_Drive_Motor.set(Math.pow(JoystickL_Y,2));
    K_Rotational_Motor.set(Math.atan((180/(JoystickR_X * 100))));
    for(WPI_TalonSRX N_Drive:N_Drive_Motors){N_Drive.set(Math.pow(JoystickL_Y,2));}
    for(WPI_TalonSRX N_Rotates:N_Rotational_Motors){N_Rotates.set(Math.atan(JoystickL_Y/JoystickL_X));}
  }
  //End Command
  @Override
  public void end(boolean interrupted) {if(Objects.equals(interrupted,true)){Command_Complete = true;}}

  //Check Command Complete
  @Override
  public boolean isFinished() {return Command_Complete;}
}

