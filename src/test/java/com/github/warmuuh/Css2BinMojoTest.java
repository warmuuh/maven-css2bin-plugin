//package com.github.warmuuh;
//
//
//import org.apache.maven.plugin.testing.MojoRule;
//import org.apache.maven.plugin.testing.WithoutMojo;
//
//import org.junit.Rule;
//import static org.junit.Assert.*;
//import org.junit.Test;
//import java.io.File;
//
//public class Css2BinMojoTest
//{
//    @Rule
//    public MojoRule rule = new MojoRule()
//    {
//        @Override
//        protected void before() throws Throwable
//        {
//        }
//
//        @Override
//        protected void after()
//        {
//        }
//    };
//
//    /**
//     * @throws Exception if any
//     */
//    @Test
//    public void testSomething()
//            throws Exception
//    {
//        File pom = new File( "target/test-classes/project-to-test/" );
//        assertNotNull( pom );
//        assertTrue( pom.exists() );
//
//        Css2BinMojo css2BinMojo = (Css2BinMojo) rule.lookupConfiguredMojo( pom, "touch" );
//        assertNotNull(css2BinMojo);
//        css2BinMojo.execute();
//
//        File outputDirectory = ( File ) rule.getVariableValueFromObject(css2BinMojo, "outputDirectory" );
//        assertNotNull( outputDirectory );
//        assertTrue( outputDirectory.exists() );
//
//        File touch = new File( outputDirectory, "touch.txt" );
//        assertTrue( touch.exists() );
//
//    }
//
//    /** Do not need the MojoRule. */
//    @WithoutMojo
//    @Test
//    public void testSomethingWhichDoesNotNeedTheMojoAndProbablyShouldBeExtractedIntoANewClassOfItsOwn()
//    {
//        assertTrue( true );
//    }
//
//}
//
