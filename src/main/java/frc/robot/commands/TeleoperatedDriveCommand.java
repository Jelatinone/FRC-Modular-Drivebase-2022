//Root Package
package frc.robot.commands;

//Local
import frc.robot.subsystems.SwerveSubsystem;
//Libraries
import edu.wpi.first.wpilibj2.command.CommandBase;
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
  private final Pigeon2 M_gyro;
  private final SwerveSubsystem Parent_Subsystem;
  private boolean Command_Complete = false;


  //Constructors
  public TeleoperatedDriveCommand(SwerveSubsystem Parent, double Left_X, double Left_Y, double Right_X,Pigeon2 gyro)
  {
    //Define Instances
    //Joysticks
    JoystickL_X = Left_X;
    JoystickL_Y = Left_Y;
    JoystickR_X = Right_X;
    //Parent Subsystem
    Parent_Subsystem = Parent;
    //Gyroscope
    M_gyro = gyro;
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
    //Receive Compas Heading
    M_gyro.getCompassHeading();
  }
  //End Command
  @Override
  public void end(boolean interrupted) {if(Objects.equals(interrupted,true)){Command_Complete = true;}}
  //Check Command Complete
  @Override
  public boolean isFinished() {return Command_Complete;}
}

