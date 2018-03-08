package org.team2168.commands.auto.test;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class TestAutoCommandGroupA extends CommandGroup {

    public TestAutoCommandGroupA() {
        addSequential(new TestCommand());
    }
}
