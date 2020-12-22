package com.github.warmuuh;


import javafx.css.Stylesheet;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * Goal which touches a timestamp file.
 */
@Mojo(name = "generate", defaultPhase = LifecyclePhase.GENERATE_RESOURCES)
public class Css2BinMojo extends AbstractMojo {

    @Parameter(defaultValue = "${project}", readonly = true, required = true)
    private MavenProject project;

	/**
	 * Location of the css files to be processed
	 */
	@Parameter(defaultValue = "${project.basedir}/src/main/css", property = "inputDir", required = false)
	private File inputDirectory;


	/**
	 * Location to where to write generated *.bss files
	 */
	@Parameter(defaultValue = "${project.build.directory}", property = "outputDir", required = false)
	private File outputDirectory;


	public void execute() throws MojoExecutionException {
        try {
            Files.walkFileTree(inputDirectory.toPath(), new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    if (file.toFile().getName().endsWith(".css")) {
                        generateBinaryStyleSheet(file.toFile());
                    }
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException e) {
            throw new MojoExecutionException("failed to generate binary style sheets", e);
        }
    }

	private void generateBinaryStyleSheet(File input) throws IOException {
        Path relativePath = inputDirectory.toPath().relativize(input.toPath());
        File outputFile = outputDirectory.toPath().resolve(relativePath).toFile();
		outputFile = new File(outputFile.toString().replace(".css", ".bss"));
        getLog().info(input + " --> " + outputFile);
        File parentFile = outputFile.getParentFile();
        if (!parentFile.exists() && !parentFile.mkdirs()){
            throw new IOException("Failed to create directory for " + parentFile);
        }

        Stylesheet.convertToBinary(input, outputFile);
    }
}
