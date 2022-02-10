def call( Map _args ) {

	node {
		stage( "Checkout" ) {
			checkout scm
		}
		stage( "Deploy to TEST" ) {
			Deployer( "test" )
		}
	
		if( BRANCH_NAME == "master" ) {
			stage( "Deploy to PRODUCTION" ) {
				Deployer( "production" )
			}
		}
	}
}
