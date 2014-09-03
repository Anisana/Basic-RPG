package core;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;

public class CharacterExclusionStrategy implements ExclusionStrategy {
	private final Class<?> excludedClass;

	public CharacterExclusionStrategy(Class<?> clazz) {
		this.excludedClass = clazz;
	}

	@Override
	public boolean shouldSkipClass(Class<?> arg0) {
		return excludedClass.equals(arg0);
	}

	@Override
	public boolean shouldSkipField(FieldAttributes arg0) {
		return excludedClass.equals(arg0);
	}

}
