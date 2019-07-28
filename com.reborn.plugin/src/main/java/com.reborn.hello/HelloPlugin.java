package com.reborn.hello;

import com.alibaba.fastjson.JSON;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;

import java.util.List;

@Mojo(name = "hello")
public class HelloPlugin extends AbstractMojo {


    /**
     * <code>
     *     <configuration>
     *      <sigle>this is parameter</sigle>
     *      <multi>
     *          <param>first</param>
     *          <param>second</param>
     *      </multi>
     *     </configuration>
     * </code>
     *
     *
     * 默认参数名和使用段配置是相同的
     * */
    @Parameter
    private String sigle;

    @Parameter
    private List<String> multi;

    @Parameter(defaultValue = "${project}")
    private MavenProject project;

    @Parameter( property = "maven.source.classifier", defaultValue = "sources" )
    private String classifier;

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        getLog().info("this is reborn hello");
        getLog().info("parameter:" + sigle);
        getLog().info("parameters:" + JSON.toJSONString(multi));

        getLog().info("project:-----");
        getLog().info(JSON.toJSONString(project));
        getLog().info("project:-----");

        getLog().info("classifier:" + classifier);


    }
}
