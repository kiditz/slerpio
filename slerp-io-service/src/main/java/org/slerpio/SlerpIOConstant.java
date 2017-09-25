package org.slerpio;

import java.io.File;

import org.slerp.core.CoreException;

public class SlerpIOConstant {
	static public String AUTHORITY_TEACHER = "TEACHER";
	static public String AUTHORITY_STUDENT = "STUDENT";

	static public File BASE_DIR = new File(System.getProperty("user.home"), ".slerp");
	static {
		if (!BASE_DIR.getParentFile().isDirectory())
			if (!BASE_DIR.mkdirs())
				throw new CoreException(
						Exception.FAILED_TO_WRITE_DIRECTORY.concat(BASE_DIR.getParentFile().getAbsolutePath()));
		if (!BASE_DIR.isDirectory())

			if (!BASE_DIR.mkdirs())
				throw new CoreException(Exception.FAILED_TO_WRITE_DIRECTORY.concat(BASE_DIR.getAbsolutePath()));
	}

	public static final class Exception {
		static public String FAILED_TO_WRITE_DIRECTORY = "failed to write directory @:";
		static public String FAILED_TO_WRITE_FILE = "failed to write file @:";
		static public final String SCHOOL_NOT_FOUND = "org.slerpio.entity.School.notFound";
		static public final String PROFILE_NOT_FOUND = "org.slerpio.entity.Profile.notFound";
	}
}
