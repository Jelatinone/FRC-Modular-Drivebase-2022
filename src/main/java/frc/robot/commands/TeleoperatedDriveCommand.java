//Root Package
package frc.robot.commands;

//Local
import frc.robot.subsystems.SwerveSubsystem;
//Libraries
import edu.wpi.first.wpilibj2.command.CommandBase;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
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
    //Motor Assignment
    WPI_TalonSRX K_Drive_Motor = Parent_Subsystem.getKDrive()[(JoystickR_X > 0)? (1): ((JoystickR_X < 0)? (0): (0))];
    WPI_TalonSRX K_Rotational_Motor = Parent_Subsystem.getKRotate()[(JoystickR_X > 0)? (1): ((JoystickR_X < 0)? (0): (0))];
    WPI_TalonSRX[] N_Drive_Motors = new WPI_TalonSRX[3];
    WPI_TalonSRX[] N_Rotational_Motors = new WPI_TalonSRX[3];
    //Retrieval of N[D]
    for(int i = 0, j = 0; i < Parent_Subsystem.getNDrives().length; i++)
    {
      for(WPI_TalonSRX N_Drive_Motor: Parent_Subsystem.getNDrives()[i])
      {
        if(!(Objects.equals(N_Drive_Motor,K_Drive_Motor))){N_Drive_Motors[j] = N_Drive_Motor;j++;}
      }
    }
    //Retrieval of N[R]
    for(int i = 0, j = 0; i < Parent_Subsystem.getNRotates().length; i++)
    {
      for(WPI_TalonSRX N_Rotate_Motor: Parent_Subsystem.getNRotates()[i])
      {
        if(!(Objects.equals(N_Rotate_Motor,K_Rotational_Motor))){N_Rotational_Motors[j] = N_Rotate_Motor;j++;}
      }
    }
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

