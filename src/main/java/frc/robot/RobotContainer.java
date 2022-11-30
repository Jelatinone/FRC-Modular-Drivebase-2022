package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.commands.DriveCommand;
import frc.robot.commands.DriveCommand;
import frc.robot.subsystems.SwerveSubsystem;
import edu.wpi.first.wpilibj2.command.Command;

public class RobotContainer
{
  private final SwerveSubsystem m_exampleSubsystem = new SwerveSubsystem();
  private final DriveCommand m_autoCommand = new DriveCommand(m_exampleSubsystem);

  public RobotContainer() {
    configureButtonBindings();
  }
  private void configureButtonBindings() {}

  /**
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    return m_autoCommand;
  }
}