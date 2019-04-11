package com.marsh.welcome;

import java.nio.file.Path;

public class TargetResource {
	private Path target;
	
	public TargetResource(Path path) {
		this.target = path;
	}

	public Path getTargetResource() {
		return target;
	}
}
