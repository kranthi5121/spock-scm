def call( _env ) {
	
	if( _env == "test" ) {
		
			sh("hello test")
		
	} else if( _env == "production" ) {
		sh("hello prod")
	}
}
