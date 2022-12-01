//Root Package
package frc.robot.commands;

//Local
import frc.robot.subsystems.SwerveSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
//Libraries
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
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
  public TeleoperatedDriveCommand(SwerveSubsystem Parent, double Left_X, double Left_Y, double Right_X,Pigeon2 gyro)
  {
    //Define Instances
    //Joystick Deadzone Assignment
    if(Left_X > 0.05){JoystickL_X = Left_X;}else{JoystickL_X = 0.0; Command_Complete = true; this.cancel();}
    if(Left_Y > 0.05){JoystickL_Y = Left_Y;}else{JoystickL_Y = 0.0; Command_Complete = true;this.cancel();}
    if(Right_X > 0.05){JoystickR_X = Right_X;}else{JoystickR_X = 0.0; Command_Complete = true; this.cancel();}
    //Parent Subsystem
    Parent_Subsystem = Parent;
    //Add Command To Parent Subsystem
    addRequirements(Parent_Subsystem);
  }

  //Initialize Command
  @Override
  public void initialize() {}

  //Execute Command
  @Override
  public void execute() 
  {
    //Get Drive and Rotational Motors
    MotorControllerGroup K_Drive = Parent_Subsystem.getKDrive();
    MotorControllerGroup K_Rotational = Parent_Subsystem.getKRotate();
  }
  //End Command
  @Override
  public void end(boolean interrupted) {if(Objects.equals(interrupted,true)){Command_Complete = true;}}
  //Check Command Complete
  @Override
  public boolean isFinished() {return Command_Complete;}
}

