import com.homeaway.devtools.jenkins.testing.JenkinsPipelineSpecification

public class DefaultPipelineSpec extends JenkinsPipelineSpecification {

	      def DefaultPipeline = null

      	public static class DummyException extends RuntimeException {
		             public DummyException(String _message) { super( _message ); }
	      }

	      def setup() {
                DefaultPipeline = loadPipelineScriptForTest("vars/DefaultPipeline.groovy")
                DefaultPipeline.getBinding().setVariable( "scm", null )
                getPipelineMock("libraryResource")(_) >> {
                  return "Dummy Message"
		            }
	      }
	      def "Attempts to deploy MASTER branch to PRODUCTION" () {
		            setup:
			                  DefaultPipeline.getBinding().setVariable( "BRANCH_NAME", "master" )
		            when:
			                  DefaultPipeline()
		            then:
		                  	1 * getPipelineMock("Deployer.call")("production")
      	}

	      def "Does NOT attempt to deploy non-MASTER branch PRODUCTION" () {
		            setup:
			                  DefaultPipeline.getBinding().setVariable( "BRANCH_NAME", "develop" )
		            when:
			                  DefaultPipeline()
		            then:
			                  0 * getPipelineMock("Deployer.call")("production")
	      }
}
